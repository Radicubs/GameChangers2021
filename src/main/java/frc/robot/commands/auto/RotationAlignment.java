package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotationAlignment extends Command {
  public RotationAlignment() {

    requires(Robot.driveTrain);
  }

  private boolean rotationFinished = false;

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    // Drive!
    // Robot.driveBase.drive(-0.1, 0.1);
    double x = Robot.limeLight.getTable().getEntry("tx").getDouble(0.0);
    System.out.println(Robot.limeLight.getTable().getEntry("tx").getDouble(0.0));
    if (Math.abs(x) > 8) {
      Robot.driveTrain.drive(x / 50, x / 50, x / 50, x / 50);
    } else {
      rotationFinished = true;
    }
  }

  @Override
  protected boolean isFinished() {
    // Make this return true when this Command no longer needs to run execute()
    return rotationFinished;
  }

  @Override
  protected void end() {
    Robot.driveTrain.drive(0,0,0,0);
    rotationFinished = false;
  }

  @Override
  protected void interrupted() {
    super.interrupted();
  }
}
