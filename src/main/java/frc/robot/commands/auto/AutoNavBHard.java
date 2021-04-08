package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavBHard extends CommandGroup {
    private final double speed = 0.4;
    private final double DISTANCE_MULTIPLIER = 0.6;

    public AutoNavBHard() {
        requires(Robot.driveTrain);
        // addSequential(new Drive(0, speed, 0), DISTANCE_MULTIPLIER);
        // addSequential(new Drive(-Math.PI / 4, speed, 0), 2 * DISTANCE_MULTIPLIER *
        // Math.sqrt(2));
        // addSequential(new Drive(0, speed, 0), 4 * DISTANCE_MULTIPLIER);
        // addSequential(new Drive(Math.PI / 4, speed, 0), 2 * DISTANCE_MULTIPLIER *
        // Math.sqrt(2));
        // addSequential(new Drive(0, speed, 0), 1 * DISTANCE_MULTIPLIER);
        // addSequential(new Drive(Math.PI / 4, speed, 0), 2 * DISTANCE_MULTIPLIER *
        // Math.sqrt(2));

        // addSequential(new Drive(Math.PI / 4, -speed, 0), 2 * DISTANCE_MULTIPLIER *
        // Math.sqrt(2));
        // addSequential(new Drive(0, -speed, 0), 1 * DISTANCE_MULTIPLIER);
        // addSequential(new Drive(Math.PI / 4, -speed, 0), 2 * DISTANCE_MULTIPLIER *
        // Math.sqrt(2));
        // addSequential(new Drive(0, -speed, 0), 4 * DISTANCE_MULTIPLIER);
        // addSequential(new Drive(-Math.PI / 4, -speed, 0), 2 * DISTANCE_MULTIPLIER *
        // Math.sqrt(2));
        // addSequential(new Drive(0, -speed, 0), DISTANCE_MULTIPLIER);

        // forward
        addSequential(new Drive(0, speed, 0), 1.75 * DISTANCE_MULTIPLIER);
        // left
        addSequential(new Drive(Math.PI / 2, speed, 0), 1.75 * DISTANCE_MULTIPLIER * 1.38);
        // forward
        addSequential(new Drive(0, speed + 0.4, 0), 2.85 * DISTANCE_MULTIPLIER);
        // right
        addSequential(new Drive(-Math.PI / 2, speed, 0), 2.55 * DISTANCE_MULTIPLIER * 1.38);

        // forward - loop
        addSequential(new Drive(0, speed, 0), 2.75 * DISTANCE_MULTIPLIER);
        // left - loops
        addSequential(new Drive(Math.PI / 2, speed + .2, 0), 1.4 * DISTANCE_MULTIPLIER * 1.38);
        // back - loop
        addSequential(new Drive(0, -speed, 0), 2.5 * DISTANCE_MULTIPLIER);
        // right - loop
        addSequential(new Drive(-Math.PI / 2, speed + .2, 0), 1.3 * DISTANCE_MULTIPLIER * 1.38);

        // back
        addSequential(new Drive(0, -speed - 0.4, 0), 2.85 * DISTANCE_MULTIPLIER);
        // left
        addSequential(new Drive(Math.PI / 2, speed * 1.2, 0), 2.70 * DISTANCE_MULTIPLIER * 1.38);
        // backward
        addSequential(new Drive(0, -speed * 1.2, 0), 1.75 * DISTANCE_MULTIPLIER);

    }
}
