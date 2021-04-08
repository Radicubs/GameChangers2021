package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavCHard extends CommandGroup {
    private final double speed = 0.4;
    private final double DISTANCE_MULTIPLIER = 1.0;

    public AutoNavCHard() {
        requires(Robot.driveTrain);
        addSequential(new Drive(0, speed, 0), 2 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(-Math.PI / 2, speed, 0), 2 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 2, speed, 0), 2 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(0, speed, 0), DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 2, speed, 0), 2 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(0, speed, 0), 2 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(-Math.PI / 2, speed, 0), 4 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 2, speed, 0), 4 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(0, speed, 0), 3 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(-Math.PI / 2, speed, 0), 4 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(Math.PI / 2, speed, 0), 2 * DISTANCE_MULTIPLIER);
        addSequential(new Drive(0, speed, 0), 3 * DISTANCE_MULTIPLIER);
    }
}
