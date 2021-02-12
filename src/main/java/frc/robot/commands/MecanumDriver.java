package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MecanumDriver extends Command {

  public MecanumDriver() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    double y = Robot.oi.controller.getRawAxis(RobotMap.LEFT_Y_AXIS);
    double x = Robot.oi.controller.getRawAxis(RobotMap.LEFT_X_AXIS);

    double angle = Math.atan2(x, y);
    angle = angle != Double.NaN ? angle : 0;
    double magnitude = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
    magnitude = magnitude < 1 ? magnitude : 1;

    double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude = turn;
    double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude = turn;

    double normalizeFactor = Math.max(Math.abs(speedRBLF), Math.abs(speedRFLB));
    speedRBLF /= normalizeFactor;
    speedRFLB /= normalizeFactor;

    Robot.driveTrain.drive(speedRFLB, -speedRBLF, speedRBLF, -speedRFLB);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
