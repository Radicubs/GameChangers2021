package frc.robot.commands.init;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RunShooter extends Command {

  private static double speed;
  private static double shooterState = 0.0;
  private static String dashboardState = "ON";

  public RunShooter(double speed) {
    requires(Robot.shooter);
    this.speed = speed;
  }

  @Override
  protected void execute() {
    // speed *= intakeState;
    Robot.shooter.shootBall(-speed * shooterState);
    SmartDashboard.putString("Intake State", dashboardState);
  }

  @Override
  protected boolean isFinished() {
    // Make this return true when this Command no longer needs to run execute()
    return false;
  }

  public static void shooterOnOff() {
    if (shooterState == 1.0) {
      shooterState = 0.0;
      dashboardState = "OFF";
    } else {
      shooterState = 1.0;
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
