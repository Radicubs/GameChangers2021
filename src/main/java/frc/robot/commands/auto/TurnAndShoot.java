package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnAndShoot extends CommandGroup {

    public TurnAndShoot() {
        // addSequential(new RotationAlignment());
        addSequential(new EnableShooter(), 2.5);
    }
}
