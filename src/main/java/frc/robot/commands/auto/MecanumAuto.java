package frc.robot.commands.auto;

import java.util.LinkedList;
import java.util.Queue;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.CoordinatePair;

public class MecanumAuto extends Command {

    private Queue<CoordinatePair> points;

    public MecanumAuto() {
        requires(Robot.driveTrain);
        points = new LinkedList<CoordinatePair>();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        try {
            double y = points.peek().getY();
            double x = points.remove().getX();
            double turn = 0;

            double angle = Math.atan2(x, y);
            angle = angle != Double.NaN ? angle : 0;
            double magnitude = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
            magnitude = magnitude < 1 ? magnitude : 1;

            double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
            double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;

            Robot.driveTrain.drive(speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn, -speedRFLB + turn);
        } catch (Exception e) {
            System.out.println("Got exception: " + e);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
