package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavBHard extends CommandGroup {
    private final double speed = 0.4;

    public AutoNavBHard() {
        requires(Robot.driveTrain);
        addSequential(new Drive(Math.PI / 2, speed, 0), 1);
        addSequential(new Drive(0, speed, 0), 1);
        addSequential(new Drive(Math.PI / 2, -speed, 0), 1);
        addSequential(new Drive(0, -speed, 0), 1);
    }
}
