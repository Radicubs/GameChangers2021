package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnAndShoot extends CommandGroup {

    public TurnAndShoot() {
        addParallel(new AutoShooter(), 3.0);
        addSequential(new RotationAlignment());
        addSequential(new EnableShooter(), 2.5);
    }
}
