package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoNavA extends CommandGroup {
    public AutoNavA() {
        requires(Robot.driveTrain);
        addSequential(new Drive(Math.PI / 2, speed, 0), 1);
        addSequential(new Drive(0, speed, 0), 1);
        addSequential(new Drive(Math.PI / 2, -speed, 0), 1);
        addSequential(new Drive(0, -speed, 0), 1);
    }
}
