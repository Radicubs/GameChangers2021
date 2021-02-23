package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class IntakeState extends InstantCommand {
  @Override
  protected void initialize() {
    RunIntake.intakeOnOff();
  }
}
