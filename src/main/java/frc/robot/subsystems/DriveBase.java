package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot.*;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class DriveBase extends Subsystem {

  // Have to initialize motors here

  // Right Motors
  private TalonFX rightMotorFront;
  private TalonFX rightMotorBack;

  // Left Motors
  private TalonFX leftMotorFront;
  private TalonFX leftMotorBack;
  // public final MecanumDrive drive;

  private double K = 1;
  private int maxIntegralAccumulator = 1000;
  private SpeedControllerGroup rightFront, rightBack, leftFront, leftBack;

  double pValue = 0.06;
  double iValue = 0.02;
  double dValue = 0.8;
  double fValue = 0.05;
  int PIDLoopRate = 10;

  public DriveBase() {

    // motors
    rightMotorFront = new TalonFX(RobotMap.RIGHT_FALCON_FRONT);
    rightMotorBack = new TalonFX(RobotMap.RIGHT_FALCON_BACK);

    leftMotorFront = new TalonFX(RobotMap.LEFT_FALCON_FRONT);
    leftMotorBack = new TalonFX(RobotMap.LEFT_FALCON_BACK);

    rightMotorFront.configFactoryDefault();
    rightMotorBack.configFactoryDefault();
    leftMotorFront.configFactoryDefault();
    leftMotorBack.configFactoryDefault();

    // Might interfere with PID

    leftMotorFront.setNeutralMode(NeutralMode.Brake);
    leftMotorBack.setNeutralMode(NeutralMode.Brake);
    rightMotorFront.setNeutralMode(NeutralMode.Brake);
    rightMotorBack.setNeutralMode(NeutralMode.Brake);

    rightMotorFront.config_kP(0, pValue);
    rightMotorFront.config_kI(0, iValue);
    rightMotorFront.config_kD(0, dValue);
    rightMotorFront.config_kF(0, fValue);
    rightMotorBack.config_kP(0, pValue);
    rightMotorBack.config_kI(0, iValue);
    rightMotorBack.config_kD(0, dValue);
    rightMotorBack.config_kF(0, fValue);
    leftMotorFront.config_kP(0, pValue);
    leftMotorFront.config_kI(0, iValue);
    leftMotorFront.config_kD(0, dValue);
    leftMotorFront.config_kF(0, fValue);
    leftMotorBack.config_kP(0, pValue);
    leftMotorBack.config_kI(0, iValue);
    leftMotorBack.config_kD(0, dValue);
    leftMotorBack.config_kF(0, fValue);

    rightMotorBack.configMaxIntegralAccumulator(0, maxIntegralAccumulator);
    rightMotorFront.configMaxIntegralAccumulator(0, maxIntegralAccumulator);
    leftMotorFront.configMaxIntegralAccumulator(0, maxIntegralAccumulator);
    leftMotorBack.configMaxIntegralAccumulator(0, maxIntegralAccumulator);

    rightMotorBack.configAllowableClosedloopError(0, 0);
    rightMotorFront.configAllowableClosedloopError(0, 0);
    leftMotorFront.configAllowableClosedloopError(0, 0);
    leftMotorBack.configAllowableClosedloopError(0, 0);

    rightMotorBack.configClosedLoopPeriod(0, PIDLoopRate);
    rightMotorFront.configClosedLoopPeriod(0, PIDLoopRate);
    leftMotorFront.configClosedLoopPeriod(0, PIDLoopRate);
    leftMotorBack.configClosedLoopPeriod(0, PIDLoopRate);
  }

  public void drive(double speedRF, double speedRB, double speedLF, double speedLB) {
    rightMotorFront.set(ControlMode.PercentOutput, K * speedRF);
    rightMotorBack.set(ControlMode.PercentOutput, K * speedRB);

    leftMotorFront.set(ControlMode.PercentOutput, K * speedLF);
    leftMotorBack.set(ControlMode.PercentOutput, K * speedLB);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MecanumDriver());
  }
}
