package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EnableShooter extends Command {

  public EnableShooter() {
    requires(Robot.elevator);
    requires(Robot.index);
    requires(Robot.shooter);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // Robot.elevator.elevatorUp(0.8);
    Robot.index.indexIn(0.8);
    Robot.shooter.shootBall(0.8);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    // Robot.elevator.elevatorUp(0);
    Robot.index.indexIn(0);
    Robot.shooter.shootBall(0);
  }

  @Override
  protected void interrupted() {
    super.interrupted();
  }
}
