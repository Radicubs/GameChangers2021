package frc.robot.commands.init;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RunIndex extends Command {

  private static double speed;
  private static double indexState = 0.0;
  private static String dashboardState = "ON";

  public RunIndex(double speed) {
    requires(Robot.index);
    this.speed = speed;
  }

  @Override
  protected void execute() {
    // speed *= intakeState;
    Robot.index.indexIn(speed * indexState);
    SmartDashboard.putString("Intake State", dashboardState);
  }

  @Override
  protected boolean isFinished() {
    // Make this return true when this Command no longer needs to run execute()
    return false;
  }

  public static void indexOnOff() {
    if (indexState == 1.0) {
      indexState = 0.0;
      dashboardState = "OFF";
    } else {
      indexState = 1.0;
      dashboardState = "ON";
    }
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    super.interrupted();
  }
}
