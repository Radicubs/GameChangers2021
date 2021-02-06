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
    double x = Robot.oi.controller.getRawAxis(RobotMap.LEFT_X_AXIS);
    double y = Robot.oi.controller.getRawAxis(RobotMap.LEFT_Y_AXIS);

    double angle = Math.atan(y / x);
    angle = angle == Double.NaN ? angle : 0;
    double magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / Math.sqrt(2);

    double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
    double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;
    Robot.driveTrain.drive(speedRFLB, speedRBLF);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
