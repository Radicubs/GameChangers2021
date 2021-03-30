package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.init.*;

public class Index extends Subsystem {
  private CANSparkMax indexMotorOne;
  private CANSparkMax indexMotorTwo;

  private double speed = 0.25;

  public Index() {

    // constructor
    indexMotorOne = new CANSparkMax(RobotMap.INDEX_ONE, MotorType.kBrushless);
    indexMotorTwo = new CANSparkMax(RobotMap.INDEX_TWO, MotorType.kBrushless);

    // Set Motors to default and neutral
    indexMotorOne.restoreFactoryDefaults();
    indexMotorTwo.restoreFactoryDefaults();
  }

  public void indexIn(double speed) {
    indexMotorOne.set(speed);
    indexMotorTwo.set(-speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RunIndex(speed));
  }
}
