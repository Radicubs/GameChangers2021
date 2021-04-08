package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavAHard extends CommandGroup {
    private final double speed = 0.25;
    private final int DISTANCE_MULTIPLIER = 1;

    public AutoNavAHard() {
        requires(Robot.driveTrain);
        // forward
        addSequential(new Drive(0, speed * 2, 0), 2.15 * DISTANCE_MULTIPLIER);
        // right
        addSequential(new Drive(-Math.PI / 2, speed * 2, 0), 1.35 * DISTANCE_MULTIPLIER);
        // back
        addSequential(new Drive(0, -speed * 2, 0), 0.90 * DISTANCE_MULTIPLIER);
        // left
        addSequential(new Drive(Math.PI / 2, speed * 2.4, 0), 1.30 * DISTANCE_MULTIPLIER);
        // forward to second
        addSequential(new Drive(0, speed * 3, 0), 2 * DISTANCE_MULTIPLIER);
        // left
        addSequential(new Drive(Math.PI / 2, speed * 2, 0), 1.35 * DISTANCE_MULTIPLIER);
        // back
        addSequential(new Drive(0, -speed * 2, 0), 1.15 * DISTANCE_MULTIPLIER);
        // right
        addSequential(new Drive(-Math.PI / 2, speed * 2, 0), 1.45 * DISTANCE_MULTIPLIER);
        // Diagnol
        addSequential(new Drive(-Math.PI / 2.75, speed * 2, 0), 1.55 * DISTANCE_MULTIPLIER);
        // Straight
        addSequential(new Drive(0, speed * 2, 0), 1.50 * DISTANCE_MULTIPLIER);
        // left
        addSequential(new Drive(Math.PI / 2, speed * 2, 0), 1.6 * DISTANCE_MULTIPLIER);
        // back
        addSequential(new Drive(0, -speed * 4, 0), 4 * DISTANCE_MULTIPLIER);

        /*
         * addSequential(new Drive(-5 * Math.PI / 6, speed, 0), 4 *
         * DISTANCE_MULTIPLIER); addSequential(new Drive(3 * Math.PI / 2, speed, 0), 0.5
         * * DISTANCE_MULTIPLIER); addSequential(new Drive(Math.PI / 2, speed, 0), 1 *
         * DISTANCE_MULTIPLIER); addSequential(new Drive(1 * Math.PI / 3, speed, 0), 2.5
         * * DISTANCE_MULTIPLIER); addSequential(new Drive(0, speed, 0), 1 *
         * DISTANCE_MULTIPLIER); addSequential(new Drive(3 * Math.PI / 2, speed, 0), 1.3
         * * DISTANCE_MULTIPLIER); addSequential(new Drive(Math.PI, speed, 0), 1.3 *
         * DISTANCE_MULTIPLIER);
         */
    }
}
