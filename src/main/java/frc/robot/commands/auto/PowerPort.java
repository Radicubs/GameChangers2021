package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PowerPort extends CommandGroup {
    private final double DRIVE_TIME = 2.3;

    public PowerPort() {
        addSequential(new Drive(0, 0.7, 0), DRIVE_TIME);
        addSequential(new TurnAndShoot());
        addSequential(new Drive(0, -0.7, 0), DRIVE_TIME);

    }
}
