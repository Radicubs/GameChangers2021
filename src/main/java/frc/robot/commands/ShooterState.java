package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ShooterState extends InstantCommand {
  @Override
  protected void initialize() {
    RunShooter.shooterOnOff();
  }
}
