package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavCHard extends CommandGroup {
    private final double speed = 0.4;
    private final double DISTANCE_MULTIPLIER = .60;

    public AutoNavCHard() {
        requires(Robot.driveTrain);
        // Forward
        addSequential(new Drive(0, speed, 0), 1.8 * DISTANCE_MULTIPLIER);
        // Left to knock
        addSequential(new Drive(Math.PI / 2, speed, 0), 1.8 * DISTANCE_MULTIPLIER * 1.37);
        // Right -- Come back from knock
        addSequential(new Drive(-Math.PI / 2, speed + 0.2, 0), 1.8 * DISTANCE_MULTIPLIER * 1.37);
        // Forward
        addSequential(new Drive(0, speed, 0), DISTANCE_MULTIPLIER * 1.15);
        // Outside
        addSequential(new Drive(-Math.PI / 2, speed, 0), 1.9 * DISTANCE_MULTIPLIER * 1.37);
        // Forward once outside
        addSequential(new Drive(0, speed + 0.2, 0), DISTANCE_MULTIPLIER * 1.75);
        // Inside 3 chunks
        addSequential(new Drive(Math.PI / 2, speed + 0.3, 0), 2.5 * DISTANCE_MULTIPLIER * 1.37);
        // Coming out 3 chunks
        addSequential(new Drive(-Math.PI / 2, speed, 0), 4.7 * DISTANCE_MULTIPLIER * 1.37);
        // Going forward 2 chunks
        addSequential(new Drive(0, speed + 0.30, 0), 2.15 * DISTANCE_MULTIPLIER);
        // Left to knock
        addSequential(new Drive(Math.PI / 2, speed + 0.3, 0), 2.85 * DISTANCE_MULTIPLIER * 1.37);
        // Wait for 0.2 seconds
        addSequential(new Drive(0, 0, 0), 0.2);
        // Right to come back
        addSequential(new Drive(-Math.PI / 2 + Math.PI / 12, speed, 0), 1.65 * DISTANCE_MULTIPLIER * 1.37);
        // Straight to exit
        addSequential(new Drive(0, speed + 0.4, 0), 0.8 * DISTANCE_MULTIPLIER);

        // addSequential(new Drive(Math.PI / 2, speed, 0), 4 * DISTANCE_MULTIPLIER *
        // 1.37);
        // addSequential(new Drive(-Math.PI / 2, speed, 0), 2 * DISTANCE_MULTIPLIER *
        // 1.37);
        // addSequential(new Drive(0, speed, 0), 3 * DISTANCE_MULTIPLIER);
    }
}
