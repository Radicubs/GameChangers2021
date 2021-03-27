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
  public static OI oi;

  private String autoSelected;;
  private final SendableChooser<String> autoChooser = new SendableChooser<>();
  private static final String autoNavA = "AutoNav A";
  private static final String autoNavB = "AutoNav B";

  private NetworkTable table;
  private NetworkTableInstance inst;
  private NetworkTableEntry pathEntry;
  private NetworkTableEntry colorEntry;

  private Command autonomous;

  public static AHRS ahrs;

  @Override
  public void robotInit() {
    autoChooser.setDefaultOption("AutoNav A", autoNavA);
    autoChooser.addOption("AutoNav B", autoNavB);
    SmartDashboard.putData("Auto Choices", autoChooser);
    SmartDashboard.updateValues();

    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("galacticsearch");

    table.addEntryListener(
        "color",
        (table, key, entry, value, flags) -> {
          System.out.println("Color changed value: " + value.getValue());
          // add hook for Galactic Search command
        },
        EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    pathEntry = table.getEntry("path");
    colorEntry = table.getEntry("color");

    driveTrain = new DriveBase();
    intake = new Intake();
    index = new Index();
    try {
      ahrs = new AHRS(SerialPort.Port.kUSB);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    // Initialize OI Last
    oi = new OI();
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    String path = pathEntry.getString("AR");
    autoSelected = (String) autoChooser.getSelected();
    System.out.println("Auto selected: " + autoSelected);
    switch (autoSelected) {
      case autoNavA:
        autonomous = new AutoNavA();
        break;
      case autoNavB:
        autonomous = new AutoNavB();
        break;
      default:
        break;
    }
    autonomous = new MecanumAuto("AutoNavA");
    if (autonomous != null) {
      autonomous.start();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
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
  public void teleopInit() {}

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
