package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavA extends CommandGroup {
    private final double speed = 0.25;
    private final int DISTANCE_MULTIPLIER = 1;

    public AutoNavA() {
        requires(Robot.driveTrain);
        addSequential(new Drive(Math.PI / 2, speed, 0), 3 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI, speed, 0), 1 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(3 * Math.PI / 2, speed, 0), 1 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(0, speed, 0), 1 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 6, speed, 0), 4 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(0, speed, 0), 0.5 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(3 * Math.PI / 2, speed, 0), 1 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(5 * Math.PI / 6, speed, 0), 2.5 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 2, speed, 0), 1 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(0, speed, 0), 1.3 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(3 * Math.PI / 2, speed, 0), 1.3 * DISTANCE_MULTIPLIER);
    }
}
