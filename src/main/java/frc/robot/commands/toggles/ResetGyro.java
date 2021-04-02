package frc.robot.commands.toggles;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ResetGyro extends InstantCommand {
  @Override
  protected void initialize() {
    Robot.init_angle = Robot.ahrs.getAngle();
  }
}