package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;
import frc.robot.Robot;

public class TurnAndShoot extends CommandGroup {

    public TurnAndShoot() {
        addSequential(new RotationAlignment());
        addSequential(new EnableShooter(), 3.0);
    }
}
