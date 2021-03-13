package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MecanumDriver extends Command {

  private static double driveMode = 0.5;

  public MecanumDriver() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    try {
      double y = Robot.oi.controller.getRawAxis(RobotMap.LEFT_Y_AXIS);
      double x = Robot.oi.controller.getRawAxis(RobotMap.LEFT_X_AXIS);
      double turn = Robot.oi.controller.getRawAxis(RobotMap.RIGHT_X_AXIS);

      // // clip to -1, 1
      // if (y < -1) { y = -1; }
      // else if (y > 1) { y = 1; }
      // else if (y < 0.01 && y > -0.01) { y = 0; }

      // if (x < -1) { x = -1; }
      // else if (x > 1) { x = 1; }
      // else if (x < 0.01 && x > -0.01) { x = 0; }

      // if (turn < -1) { turn = -1; }
      // else if (turn > 1) { turn = 1; }
      // else if (turn < 0.01 && turn > -0.01) { turn = 0; }

      y *= driveMode;
      x *= driveMode;
      turn *= driveMode;

      double angle = Math.atan2(x, y);
      angle = angle != Double.NaN ? angle : 0;
      double magnitude = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
      magnitude = magnitude < 1 ? magnitude : 1;

      double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
      double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;

      Robot.driveTrain.drive(
          speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn, -speedRFLB + turn);
    } catch (Exception e) {
      System.out.println("Got exception: " + e);
    }
  }

  public static void changeDriveMode() {
    if (driveMode == 0.5) {
      driveMode = 1;
    } else {
      driveMode = 0.5;
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
