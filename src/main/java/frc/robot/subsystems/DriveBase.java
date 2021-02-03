package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot.*;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;

public class DriveBase extends Subsystem {

  // Have to initialize motors here

  // Right Motors
  private WPI_TalonFX rightMotorFront;
  private WPI_TalonFX rightMotorBack;

  // Left Motors
  private WPI_TalonFX leftMotorFront;
  private WPI_TalonFX leftMotorBack;
  //public final MecanumDrive drive;

  private SpeedControllerGroup rightFront, rightBack, leftFront, leftBack;

  public DriveBase() {

    // motors
    rightMotorFront = new WPI_TalonFX(RobotMap.RIGHT_FALCON_FRONT);
    rightMotorBack = new WPI_TalonFX(RobotMap.RIGHT_FALCON_BACK);

    leftMotorFront = new WPI_TalonFX(RobotMap.LEFT_FALCON_FRONT);
    leftMotorBack = new WPI_TalonFX(RobotMap.LEFT_FALCON_BACK);

    rightMotorFront.configFactoryDefault();
    rightMotorBack.configFactoryDefault();
    leftMotorFront.configFactoryDefault();
    leftMotorBack.configFactoryDefault();
    leftMotorFront.setNeutralMode(NeutralMode.Brake);
    leftMotorBack.setNeutralMode(NeutralMode.Brake);
    rightMotorFront.setNeutralMode(NeutralMode.Brake);
    rightMotorBack.setNeutralMode(NeutralMode.Brake);
  }

  public void drive(double x, double y, double turn) {
    double angle = Math.atan(y/x);
    double magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / Math.sqrt(2);

    double speedRBLF = -Math.sin(angle - (Math.PI / 4));
    rightMotorBack.set(ControlMode.PercentOutput, speedRBLF * magnitude);
    leftMotorFront.set(ControlMode.PercentOutput, speedRBLF * magnitude);

    double speedRFLB = -Math.sin(angle + (Math.PI / 4));
    rightMotorFront.set(ControlMode.PercentOutput, speedRFLB * magnitude);
    leftMotorBack.set(ControlMode.PercentOutput, speedRFLB * magnitude);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MecanumDriver());
  }

}