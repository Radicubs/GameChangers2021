package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.util.AngleHandler;

public class MecanumDriver extends Command {

  private static double driveMode = 0.5;
  private AngleHandler angleHandler;

  public MecanumDriver() {
    requires(Robot.driveTrain);
    angleHandler = new AngleHandler(Robot.ahrs.getAngle());
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
      // turn = 0;
      // // fix this drive code later if (Math.abs(y) > 0.01 || Math.abs(x) > 0.01) {
      // double angle_from_forward = Math.PI - (Math.PI / 2 - Math.atan2(y + 0.00001,
      // x + 0.00001));
      // if (angle_from_forward > Math.PI) {
      // angle_from_forward = -(angle_from_forward % Math.PI) - Math.PI / 2;
      // }
      // angleHandler.setTarget(angle_from_forward);
      // if (!angleHandler.isFinished()) {
      // if (angle_from_forward < 0) {
      // turn = 0.1;
      // } else if (angle_from_forward > 0) {
      // turn = -0.1;
      // }
      // }

      double magnitude = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
      magnitude = magnitude < 1 ? magnitude : 1;

      double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
      double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;

      Robot.driveTrain.drive(speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn, -speedRFLB + turn);
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
