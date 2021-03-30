package frc.robot.commands.init;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RunElevator extends Command {

  private static double speed;
  private static double elevatorState = 0.0;
  private static String dashboardState = "ON";

  public RunElevator(double speed) {
    requires(Robot.elevator);
    this.speed = speed;
  }

  @Override
  protected void execute() {
    // speed *= intakeState;
    Robot.elevator.elevatorUp(speed * elevatorState);
    SmartDashboard.putString("Intake State", dashboardState);
  }

  @Override
  protected boolean isFinished() {
    // Make this return true when this Command no longer needs to run execute()
    return false;
  }

  public static void elevatorOnOff() {
    if (elevatorState == 1.0) {
      elevatorState = 0.0;
      dashboardState = "OFF";
    } else {
      elevatorState = 1.0;
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
