package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot.*;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class DriveBase extends Subsystem {

  // Have to initialize motors here

  // Right Motors
  private WPI_TalonFX rightMotorFront;
  private WPI_TalonFX rightMotorBack;

  // Left Motors
  private WPI_TalonFX leftMotorFront;
  private WPI_TalonFX leftMotorBack;
  // public final MecanumDrive drive;

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

  public void drive(double speedRF, double speedRB, double speedLF, double speedLB) {
    rightMotorFront.set(ControlMode.PercentOutput, speedRF);
    rightMotorBack.set(ControlMode.PercentOutput, speedRB);

    leftMotorFront.set(ControlMode.PercentOutput, speedLF);
    leftMotorBack.set(ControlMode.PercentOutput, speedLB);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MecanumDriver());
  }
}
