package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Drive extends Command {
    private double angle, magnitude, turn;

    public Drive(double angle, double magnitude, double turn) {
        requires(Robot.driveTrain);
        this.angle = angle;
        this.magnitude = -magnitude;
        this.turn = turn;
    }

    @Override
    public void initialize() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void execute() {
        double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
        double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;

        Robot.driveTrain.drive(speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn, -speedRFLB + turn);
    }

    @Override
    public void end() {
        Robot.driveTrain.drive(0, 0, 0, 0);
    }

    @Override
    public void interrupted() {
        super.interrupted();
    }
}
