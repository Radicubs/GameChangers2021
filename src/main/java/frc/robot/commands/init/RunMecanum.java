package frc.robot.commands.init;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class RunMecanum extends Command {

  private static double driveMode = 0.3;

  public RunMecanum() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    try {
      double y = Robot.oi.controller.getRawAxis(RobotMap.LEFT_Y_AXIS);
      double x = Robot.oi.controller.getRawAxis(RobotMap.LEFT_X_AXIS);
      double turn = Robot.oi.controller.getRawAxis(RobotMap.RIGHT_X_AXIS);

      y *= driveMode;
      x *= driveMode;
      turn *= driveMode / 2;

      double angle = Math.atan2(x, y);
      angle = angle != Double.NaN ? angle : 0;

      angle += ((Robot.ahrs.getAngle() - Robot.init_angle) / (180)) * Math.PI;

      double magnitude = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
      magnitude = magnitude < 1 ? magnitude : 1;

      double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
      double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;

      /*
       * if ((Math.abs(speedRFLB - speedRBLF) / ((Math.abs(speedRBLF) +
       * Math.abs(speedRFLB)) / 1.5)) < 1 && (Math.abs(speedRFLB - speedRBLF) /
       * ((Math.abs(speedRBLF) + Math.abs(speedRFLB)) / 1.5)) > 0) { double factor =
       * 0.6 (1 - (Math.abs(speedRFLB - speedRBLF) / ((Math.abs(speedRBLF) +
       * Math.abs(speedRFLB)) / 1.5))) + 1; // System.out.println("Factor " + factor);
       * speedRFLB *= factor; speedRBLF *= factor; }
       */

      Robot.driveTrain.drive(speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn, -speedRFLB + turn);
    } catch (Exception e) {
      System.out.println("Got exception: " + e);
    }
  }

  public static void changeDriveMode() {
    if (driveMode == 0.3) {
      driveMode = 0.9;
    } else {
      driveMode = 0.3;
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
