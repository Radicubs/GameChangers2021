package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MecanumDriver extends Command {

    public MecanumDriver() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.driveTrain.drive(Robot.oi.controller.getRawAxis(RobotMap.LEFT_X_AXIS), 
            Robot.oi.controller.getRawAxis(RobotMap.LEFT_Y_AXIS), 
            Robot.oi.controller.getRawAxis(RobotMap.RIGHT_X_AXIS));
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}