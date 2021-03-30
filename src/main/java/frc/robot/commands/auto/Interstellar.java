package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;
import frc.robot.Robot;

public class Interstellar extends CommandGroup {

    public final double ZONE_TIME = 1.0;
    
    public Interstellar() {
        addSequential(new Drive(0, 1, 0), ZONE_TIME);
        addSequential(new RotationAlignment());
        addSequential(new EnableShooter(), 3.0);
        addSequential(new Drive(0, -1, 0), ZONE_TIME);
        addSequential(new Wait(), 5.0);

        addSequential(new Drive(0, 1, 0), 2 * ZONE_TIME);
        addSequential(new RotationAlignment());
        addSequential(new EnableShooter(), 3.0);
        addSequential(new Drive(0, -1, 0), 2 * ZONE_TIME);
        addSequential(new Wait(), 5.0);

        addSequential(new Drive(0, 1, 0), 3 * ZONE_TIME);
        addSequential(new RotationAlignment());
        addSequential(new EnableShooter(), 3.0);
        addSequential(new Drive(0, -1, 0), 3 * ZONE_TIME);
        addSequential(new Wait(), 5.0);
    }
}
