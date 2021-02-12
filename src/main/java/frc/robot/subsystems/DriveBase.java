package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
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

  private SpeedControllerGroup rightFront, rightBack, leftFront, leftBack;

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
    leftMotorFront.setNeutralMode(NeutralMode.Brake);
    leftMotorBack.setNeutralMode(NeutralMode.Brake);
    rightMotorFront.setNeutralMode(NeutralMode.Brake);
    rightMotorBack.setNeutralMode(NeutralMode.Brake);
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
