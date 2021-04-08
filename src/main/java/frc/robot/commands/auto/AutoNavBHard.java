package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavBHard extends CommandGroup {
    private final double speed = 0.4;
    private final double DISTANCE_MULTIPLIER = 1.0;

    public AutoNavBHard() {
        requires(Robot.driveTrain);
        addSequential(new Drive(0, speed, 0), DISTANCE_MULTIPLIER);
        addSequential(new Drive(-Math.PI / 4, speed, 0), 2 * DISTANCE_MULTIPLIER * Math.sqrt(2));
        addSequential(new Drive(0, speed, 0), 4 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 4, speed, 0), 2 * DISTANCE_MULTIPLIER * Math.sqrt(2));
        addSequential(new Drive(0, speed, 0), 1 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 4, speed, 0), 2 * DISTANCE_MULTIPLIER * Math.sqrt(2));

        addSequential(new Drive(Math.PI / 4, -speed, 0), 2 * DISTANCE_MULTIPLIER * Math.sqrt(2));
        addSequential(new Drive(0, -speed, 0), 1 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 4, -speed, 0), 2 * DISTANCE_MULTIPLIER * Math.sqrt(2));
        addSequential(new Drive(0, -speed, 0), 4 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(-Math.PI / 4, -speed, 0), 2 * DISTANCE_MULTIPLIER * Math.sqrt(2));
        addSequential(new Drive(0, -speed, 0), DISTANCE_MULTIPLIER);
    }
}
