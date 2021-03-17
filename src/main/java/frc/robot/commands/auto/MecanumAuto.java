package frc.robot.commands.auto;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.BezierFunction;
import frc.robot.util.BezierPoint;
import frc.robot.util.CoordinatePair;

public class MecanumAuto extends Command {

    private Queue<CoordinatePair> points;
    private CoordinatePair previousPair;
    private CoordinatePair currentPair;
    private final double POINT_COUNT = 100.0;
    private final double STRETCH_FACTOR = 1;
    private String path;

    public MecanumAuto(String path) {
        requires(Robot.driveTrain);
        this.path = path;
    }
        
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        points = getPoints();
    }

    private Queue<CoordinatePair> getPoints() {

        Queue<CoordinatePair> list = new LinkedList<CoordinatePair>();
        for (double t = 0; t < 1; t += 1.0 / POINT_COUNT) {
            list.add(getFunctionVal(t));
        }
        return (Queue<CoordinatePair>) list;
    }

    private CoordinatePair getFunctionVal(double t) {
        double x = 0;
        double y = 0;

        switch (this.path) {
        case "AutoNavA":
            BezierPoint navACp1 = new BezierPoint(41.5, -9.5);
            BezierPoint navACp2 = new BezierPoint(-27.3, 68);
            BezierPoint navACp3 = new BezierPoint(34, -69);
            BezierPoint navACp4 = new BezierPoint(6.7, 8.3);
            BezierPoint navACp5 = new BezierPoint(-29.8, -9.5);
            BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4, navACp5 };

            BezierFunction function = new BezierFunction(pointsArr);
            x = function.getPos(t).getX();
            y = function.getPos(t).getY();
            break;
        case "AutoNavB":
            x = t;
            y = t;
            break;
        case "AutoNavC":
            break;

        }
        System.out.println("t: " + t);
        return new CoordinatePair(x, y);
    }

    @Override
    protected void execute() {
        try {
            currentPair = points.remove();
            double turn = 0;
            if (previousPair == null) {
                previousPair = points.remove();
            }
            System.out.println(currentPair);

            double x = currentPair.getX() - previousPair.getX();
            double y = (currentPair.getY() - previousPair.getY());
            double angle = Math.atan2(x, y);
            angle = angle != Double.NaN ? angle : 0;

            // Normalize Values
            x = Math.cos(angle);
            y = Math.sin(angle);

            double magnitude = 0.3;

            double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
            double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;

            Robot.driveTrain.drive(speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn, -speedRFLB + turn);

            previousPair = currentPair;
        } catch (Exception e) {
            System.out.println("Got exception: " + e);
        }
    }

    @Override
    protected boolean isFinished() {
        return points.isEmpty();
    }
}
