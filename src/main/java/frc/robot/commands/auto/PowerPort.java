package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PowerPort extends CommandGroup {
    private final double DRIVE_TIME = 2.7;

    public PowerPort() {
        addParallel(new WarmUpShooter());
        addSequential(new Drive(true), 5);

        addSequential(new TurnAndShoot());
        addSequential(new Drive(0, -0.4, 0), DRIVE_TIME);

    }
}
