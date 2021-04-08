package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WarmUpShooter extends Command {

  public WarmUpShooter() {
    requires(Robot.shooter);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.shooter.shootBall(0.60 * -11500 / 0.55);
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
