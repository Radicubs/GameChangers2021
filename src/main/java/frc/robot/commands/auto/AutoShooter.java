package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoShooter extends Command {

  public AutoShooter() {
    requires(Robot.shooter);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.shooter.shootBall(-0.8);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    super.interrupted();
  }
}
