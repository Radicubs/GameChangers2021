package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.init.*;

public class Elevator extends Subsystem {
  private CANSparkMax elevatorMotorFront;
  private CANSparkMax elevatorMotorBack;

  private double speed = 0.25;

  public Elevator() {

    // constructor
    elevatorMotorFront = new CANSparkMax(RobotMap.ELEVATOR_FRONT, MotorType.kBrushless);
    elevatorMotorBack = new CANSparkMax(RobotMap.ELEVATOR_BACK, MotorType.kBrushless);

    // Set Motors to default and neutral
    elevatorMotorBack.restoreFactoryDefaults();
    elevatorMotorFront.restoreFactoryDefaults();
  }

  public void elevatorUp(double speed) {
    System.out.println(speed);
    elevatorMotorBack.set(speed);
    elevatorMotorFront.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RunElevator(this.speed));
  }
}
