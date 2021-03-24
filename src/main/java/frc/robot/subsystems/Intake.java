package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RunIntake;

public class Intake extends Subsystem {
  private CANSparkMax intakeMotor;
  private double speed = 0.75;

  public Intake() {

    // constructor
    intakeMotor = new CANSparkMax(RobotMap.INTAKE, MotorType.kBrushless);

    // Set Motors to default and neutral
    intakeMotor.restoreFactoryDefaults();
  }

  public void takeIn(double speed) {
    intakeMotor.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RunIntake(speed));
  }
}
