package frc.robot.commands.toggles;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.init.*;

public class IndexToggle extends InstantCommand {
  @Override
  protected void initialize() {
    RunIndex.indexOnOff();
  }
}
