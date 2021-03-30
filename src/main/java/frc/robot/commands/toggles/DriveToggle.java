package frc.robot.commands.toggles;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.init.RunMecanum;

public class DriveToggle extends InstantCommand {
  @Override
  protected void initialize() {
    RunMecanum.changeDriveMode();
  }
}
