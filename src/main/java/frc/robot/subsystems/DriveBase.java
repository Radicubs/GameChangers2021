package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
  private WPI_TalonSRX rightMotorFront;
  private WPI_VictorSPX rightMotorBack;

  // Left Motors
  private WPI_TalonSRX leftMotorFront;
  private WPI_VictorSPX leftMotorBack;
  public final MecanumDrive drive;

  private SpeedControllerGroup rightFront, rightBack, leftFront, leftBack;

  public DriveBase() {

    // motors
    rightMotorFront = new WPI_TalonSRX(RobotMap.RIGHT_FALCON_FRONT);
    rightMotorBack = new WPI_VictorSPX(RobotMap.RIGHT_FALCON_BACK);

    leftMotorFront = new WPI_TalonSRX(RobotMap.LEFT_FALCON_FRONT);
    leftMotorBack = new WPI_VictorSPX(RobotMap.LEFT_FALCON_BACK);

    // Speed Controllers
    rightFront = new SpeedControllerGroup(rightMotorFront);
    rightBack = new SpeedControllerGroup(rightMotorBack);
    leftFront = new SpeedControllerGroup(leftMotorFront);
    leftBack = new SpeedControllerGroup(leftMotorBack);

    rightMotorFront.configFactoryDefault();
    rightMotorBack.configFactoryDefault();
    leftMotorFront.setNeutralMode(NeutralMode.Brake);
    leftMotorBack.setNeutralMode(NeutralMode.Brake);
    rightMotorFront.setNeutralMode(NeutralMode.Brake);
    rightMotorBack.setNeutralMode(NeutralMode.Brake);

    // Mecanum Drive Math
    double speedRF, speedRB, speedLF, speedLB;
    double forward = -Robot.oi.controller.getY();
    double right = Robot.oi.controller.getX();
    double clockwise = Robot.oi.controller.getZ();

    // Proportion from PID (Used for Turning)
    double K = .01;
    clockwise = K * clockwise;

    // Inverse Kinematics
    speedRF = forward + clockwise + right;
    speedLF = forward - clockwise - right;
    speedLB = forward + clockwise - right;
    speedRB = forward - clockwise + right;

    // Speed Controllers
    leftFront.set(speedLF);
    leftBack.set(speedLB);
    rightFront.set(speedRF);
    rightBack.set(speedRB);

    drive = new MecanumDrive(leftFront, leftBack, rightFront, rightBack);
  }

  public void drive() {
    drive.driveCartesian(Robot.oi.controller.getX(), Robot.oi.controller.getY(), Robot.oi.controller.getZ(), 0);
    Timer.delay(0.01);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MecanumDriver());
  }

}