package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RunIntake extends Command {

  private static double speed;
  private static boolean intakeState = true;
  private static String dashboardState = "ON";

  public RunIntake(double speed) {
    requires(Robot.intake);
    this.speed = speed;
  }

  @Override
  protected void execute() {
    // speed *= intakeState;
    Robot.intake.takeIn(speed * intakeState);
    SmartDashboard.putString("Intake State", dashboardState);
  }

  @Override
  protected boolean isFinished() {
    // Make this return true when this Command no longer needs to run execute()
    return false;
  }

  public static void intakeOnOff() {
    if (intakeState) {
      intakeState = false;
      dashboardState = "OFF";
    } else {
      intakeState = true;
      dashboardState = "ON";
    }
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {
    super.interrupted();
  }
}
