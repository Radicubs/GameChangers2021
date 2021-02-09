package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends Subsystem {
  private CANSparkMax intakeMotor;
  private double speed = 0.275;

  public Intake() {

    // constructor
    intakeMotor = new CANSparkMax(1, MotorType.kBrushless);

    // Set Motors to default and neutral
    intakeMotor.restoreFactoryDefault();
    intakeMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void takeIn() {
    intakeMotor.set(ControlMode.PercentOutput, this.speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RunIntake(speed));
  }
}
