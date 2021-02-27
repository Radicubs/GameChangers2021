// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.commands.auto.*;

public class Robot extends TimedRobot {
  public static DriveBase driveTrain;
  public static Intake intake;
  public static OI oi;

  private String autoSelected;;
  private final SendableChooser<String> autoChooser = new SendableChooser<>();
  private static final String autoNavA = "AutoNav A";
  private static final String autoNavB = "AutoNav B";

  private Command autonomous;

  @Override
  public void robotInit() {
    autoChooser.setDefaultOption("AutoNav A", autoNavA);
    autoChooser.addOption("AutoNav B", autoNavB);
    SmartDashboard.putData("Auto Choices", autoChooser);
    SmartDashboard.updateValues();

    driveTrain = new DriveBase();
    intake = new Intake();
    // Initialize OI Last
    oi = new OI();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
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
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    System.out.println(oi.controller.getRawAxis(RobotMap.RIGHT_X_AXIS));
    System.out.println(oi.controller.getRawAxis(RobotMap.RIGHT_Y_AXIS));
  }
}
