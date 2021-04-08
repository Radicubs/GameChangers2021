package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EnableShooter extends Command {

  public EnableShooter() {
    requires(Robot.elevator);
    requires(Robot.index);
    requires(Robot.shooter);
    requires(Robot.limeLight);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.index.indexIn(0.8);
    Robot.shooter.shootBall(0.60 * (Robot.limeLight.calculateDistance() * -11500 / 65) / 0.55);
    Robot.elevator.elevatorUp(1.0);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.index.indexIn(0);
    Robot.shooter.shootBall(0);
    Robot.elevator.elevatorUp(0);
  }

  @Override
  protected void interrupted() {
    super.interrupted();
  }
}
