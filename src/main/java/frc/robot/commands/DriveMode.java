package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class DriveMode extends InstantCommand {
  @Override
  protected void initialize() {
    MecanumDriver.changeDriveMode();
  }
}
