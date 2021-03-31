package frc.robot.commands.init;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunColorSensor extends Command {

    public RunColorSensor() {
        requires(Robot.colorSensor);
        requires(Robot.elevator);
    }

    // Time to Run command (use it for distance as well)

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (Robot.colorSensor.foundBall()) {
            Robot.elevator.elevatorUp(.4);
        } else {
            Robot.elevator.elevatorUp(0);
        }
    }

    @Override
    protected boolean isFinished() {
        // Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}