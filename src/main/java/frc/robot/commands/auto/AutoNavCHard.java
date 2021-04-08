package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavCHard extends CommandGroup {
    private final double speed = 0.4;
    private final double DISTANCE_MULTIPLIER = .55;

    public AutoNavCHard() {
        requires(Robot.driveTrain);
        // Forward
        addSequential(new Drive(0, speed, 0), 1.7 * DISTANCE_MULTIPLIER);
        // Left to knock
        addSequential(new Drive(Math.PI / 2, speed, 0), 1.8 * DISTANCE_MULTIPLIER * 1.37);
        // Wait for 0.4 seconds
        addSequential(new Drive(0, 0, 0), 0.4);
        // Right -- Come back from knock
        addSequential(new Drive(-Math.PI / 2, speed + 0.2, 0), 1.35 * DISTANCE_MULTIPLIER * 1.37);
        // Forward interior
        addSequential(new Drive(0, speed, 0), DISTANCE_MULTIPLIER * 1.35);
        addSequential(new Drive(0, 0, 0), 0.2);
        // Outside
        addSequential(new Drive(-Math.PI / 2, speed + 0.25, 0), 1.1 * DISTANCE_MULTIPLIER * 1.39);
        // Forward once outside
        addSequential(new Drive(0, speed + 0.30, 0), DISTANCE_MULTIPLIER * 1.50);
        // Wait for 0.2 seconds
        addSequential(new Drive(0, 0, 0), 0.2);
        // 2ND Inside 3 chunks
        addSequential(new Drive(Math.PI / 2, speed + 0.35, 0), 2.20 * DISTANCE_MULTIPLIER * 1.37);
        // Coming out 3 chunks
        addSequential(new Drive(-Math.PI / 2, speed, 0), 4.75 * DISTANCE_MULTIPLIER * 1.37);
        // Going forward 2 chunks
        addSequential(new Drive(0, speed + 0.30, 0), 1.85 * DISTANCE_MULTIPLIER);
        // Left to knock
        addSequential(new Drive(Math.PI / 2, speed + 0.3, 0), 2.85 * DISTANCE_MULTIPLIER * 1.37);
        // Wait for 0.2 seconds
        addSequential(new Drive(0, 0, 0), 0.2);
        // Right to come back
        addSequential(new Drive(-Math.PI / 2, speed, 0), 1.95 * DISTANCE_MULTIPLIER * 1.37);
        // Straight to exit
        addSequential(new Drive(0, speed + 0.4, 0), 0.8 * DISTANCE_MULTIPLIER);

        // addSequential(new Drive(Math.PI / 2, speed, 0), 4 * DISTANCE_MULTIPLIER *
        // 1.37);
        // addSequential(new Drive(-Math.PI / 2, speed, 0), 2 * DISTANCE_MULTIPLIER *
        // 1.37);
        // addSequential(new Drive(0, speed, 0), 3 * DISTANCE_MULTIPLIER);
    }
}
