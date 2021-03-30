package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ElevatorState extends InstantCommand {
    @Override
    protected void initialize() {
        RunElevator.elevatorOnOf();
    }
}