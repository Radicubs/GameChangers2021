package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class IndexState extends InstantCommand {
  @Override
  protected void initialize() {
    RunIndex.indexOnOff();
  }
}
