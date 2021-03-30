package frc.robot.commands.toggles;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.init.*;

public class IntakeToggle extends InstantCommand {
  @Override
  protected void initialize() {
    RunIntake.intakeOnOff();
  }
}
