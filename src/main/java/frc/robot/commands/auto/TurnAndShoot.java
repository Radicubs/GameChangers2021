package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnAndShoot extends CommandGroup {

    public TurnAndShoot() {
        addParallel(new RotationAlignment());
        addSequential(new AutoShooter(), 2.5);
        addSequential(new EnableShooter(), 2.5);
    }
}
