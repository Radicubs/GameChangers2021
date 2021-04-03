// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.*;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static DriveBase driveTrain;
  public static Intake intake;
  public static Index index;
  public static Elevator elevator;
  public static Shooter shooter;
  public static Limelight limeLight;
  public static ColorSensor colorSensor;
  public static OI oi;

  private String autoSelected;;
  private final SendableChooser<String> autoChooser = new SendableChooser<>();
  private static final String autoNavA = "AutoNav A";
  private static final String autoNavB = "AutoNav B";
  public static AHRS ahrs;
  public static double init_angle = 0;

  private NetworkTable table;
  private NetworkTableInstance inst;
  private String pathEntry;
  private String colorEntry;

  private Command autonomous;

  @Override
  public void robotInit() {
    autoChooser.setDefaultOption("AutoNav A", autoNavA);
    autoChooser.addOption("AutoNav B", autoNavB);
    autoChooser.addOption("Galactic Search", "Galactic Search");
    SmartDashboard.putData("Auto Choices", autoChooser);
    SmartDashboard.updateValues();

    UsbCamera usbCamera = new UsbCamera("USB Camera 0", 0);
    usbCamera.setResolution(1280, 720);
    usbCamera.setFPS(30);
    MjpegServer mjpegServer = new MjpegServer("radicubs", 1181);
    mjpegServer.setSource(usbCamera);
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("galacticsearch");

    table.addEntryListener(
        "color",
        (table, key, entry, value, flags) -> {
          System.out.println("Color changed value: " + value.getValue());
          // add hook for Galactic Search command
        },
        EntryListenerFlags.kNew);

    inst = NetworkTableInstance.getDefault();

    driveTrain = new DriveBase();
    elevator = new Elevator();
    intake = new Intake();
    index = new Index();
    try {
      ahrs = new AHRS(SerialPort.Port.kUSB);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    shooter = new Shooter();
    limeLight = new Limelight();
    colorSensor = new ColorSensor();
    // Initialize OI Last
    oi = new OI();
  }

  @Override
  public void robotPeriodic() {
    // System.out.println("Angle");
    // System.out.println((ahrs.getAngle() - init_angle) % 360);
  }

  @Override
  public void autonomousInit() {
    init_angle = 0;
    ahrs.setAngleAdjustment(-ahrs.getAngle());
    init_angle = ahrs.getAngle();
    // String path = pathEntry.getString("AR");
    /*
    autoSelected = (String) autoChooser.getSelected();
    System.out.println("Auto selected: " + autoSelected);
    switch (autoSelected) {
      case "AutoNav A":
        autonomous = new AutoNavA();
        break;
      case "AutoNav B":
        autonomous = new AutoNavB();
        break;
      case "Galactic Search":
        table = inst.getTable("galacticsearch");
        table.addEntryListener(
            "color",
            (table, key, entry, value, flags) -> {
              pathEntry = table.getEntry("path").getString("");
              colorEntry = table.getEntry("color").getString("");

              autonomous = new GalacticSearch(pathEntry, colorEntry);
              // autonomous = new GalacticSearch("A", "blue");
              autonomous.start();
            },
            EntryListenerFlags.kImmediate | EntryListenerFlags.kNew);

        // pathEntry = table.getEntry("path").getString("");
        // colorEntry = table.getEntry("color").getString("");

        // autonomous = new GalacticSearch(pathEntry, colorEntry);
        autonomous = new GalacticSearch("A", "blue");
        autonomous.start();
        break;
    } */
    System.out.println("meow");
    autonomous = new GalacticSearch("A", "blue");
    autonomous.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    // switch (m_autoSelected) {
    // case kCustomAuto:
    // // Put custom auto code here
    // break;
    // case kDefaultAuto:
    // default:
    // // Put default auto code here
    // break;
    // }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    init_angle = 0;
    ahrs.setAngleAdjustment(-ahrs.getAngle());
    init_angle = ahrs.getAngle();

    if (autonomous != null) {
      autonomous.cancel();
    }
    autonomous = null;
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    UsbCamera usbCamera = new UsbCamera("USB Camera 0", 0);
    usbCamera.setResolution(1280, 720);
    usbCamera.setFPS(30);
    MjpegServer mjpegServer = new MjpegServer("radicubs", 1181);
    mjpegServer.setSource(usbCamera);
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    System.out.println(oi.controller.getRawAxis(RobotMap.RIGHT_X_AXIS));
    System.out.println(oi.controller.getRawAxis(RobotMap.RIGHT_Y_AXIS));
  }
}
