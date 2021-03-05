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
    private final double POINT_COUNT = 350.0;
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
                // Control Points for Bezier Curve
                // CoordinatePair navACp1 = new CoordinatePair(0.08, -0.2 / STRETCH_FACTOR);
                // CoordinatePair navACp2 = new CoordinatePair(18.57, 10.05 / STRETCH_FACTOR);
                // CoordinatePair navACp3 = new CoordinatePair(-6.28, -9.12 / STRETCH_FACTOR);
                // CoordinatePair navACp4 = new CoordinatePair(13.94, -1.32 / STRETCH_FACTOR);

                // x = ((1 - t)
                // * ((1 - t) * ((1 - t) * navACp1.getX() + t * navACp2.getX())
                // + t * ((1 - t) * navACp2.getX() + t * navACp3.getX()))
                // + t * ((1 - t) * ((1 - t) * navACp2.getX() + t * navACp3.getX())
                // + t * ((1 - t) * navACp3.getX() + t * navACp4.getX())));

                // y = ((1 - t)
                // * ((1 - t) * ((1 - t) * navACp1.getY() + t * navACp2.getY())
                // + t * ((1 - t) * navACp2.getY() + t * navACp3.getY()))
                // + t * ((1 - t) * ((1 - t) * navACp2.getY() + t * navACp3.getY())
                // + t * ((1 - t) * navACp3.getY() + t * navACp4.getY())));

                BezierPoint navACp1 = new BezierPoint(0.08, -0.2);
                BezierPoint navACp2 = new BezierPoint(18.57, 10.05);
                BezierPoint navACp3 = new BezierPoint(-6.28, -9.12);
                BezierPoint navACp4 = new BezierPoint(13.94, -1.32);
                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

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
