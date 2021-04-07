package frc.robot.commands.auto;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.toggles.IntakeToggle;
import frc.robot.util.BezierFunction;
import frc.robot.util.BezierPoint;
import frc.robot.util.CoordinatePair;

public class GalacticSearch extends Command {

    private Queue<CoordinatePair> points;
    private CoordinatePair previousPair;
    private CoordinatePair currentPair;
    private final double POINT_COUNT = 10000.0;
    private String path;
    private String color;
    private BezierFunction function;
    private CoordinatePair initialPoint = new CoordinatePair(10, 23);
    private static CoordinatePair endPoint = new CoordinatePair(90, 23);

    private double magnitude;
    private double distanceBetweenPoints;
    private boolean adjustStafe;

    public GalacticSearch(String path, String color) {
        IntakeToggle toggle = new IntakeToggle();
        toggle.start();
        requires(Robot.driveTrain);
        this.path = path;
        this.color = color;
        if (this.path.equals("A")) {
            if (this.color.equals("red")) {
                // 0.6 magnitude, 0.35 distance, no sideways scaling at all
                BezierPoint navACp1 = new BezierPoint(17.4, 23.1);
                BezierPoint navACp2 = new BezierPoint(32.6, 24.2);
                BezierPoint navACp3 = new BezierPoint(41.5, -4);
                BezierPoint navACp4 = new BezierPoint(44.7, 37.6); 
                
                magnitude = 0.6;
                distanceBetweenPoints = 0.35;
                adjustStafe = false;

                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                function = new BezierFunction(pointsArr);
            }
            if (this.color.equals("blue")) {
                
                BezierPoint navACp1 = new BezierPoint(36.4, 11.1);
                BezierPoint navACp2 = new BezierPoint(59.7, -7.7);
                BezierPoint navACp3 = new BezierPoint(34.2, 50.4);
                BezierPoint navACp4 = new BezierPoint(67.16, 22.53); 
                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                magnitude = 0.6;
                distanceBetweenPoints = 0.35;
                adjustStafe = true;

                function = new BezierFunction(pointsArr);
            }
        }
        if (this.path.equals("B")) {
            if (this.color.equals("red")) {
                BezierPoint navACp1 = new BezierPoint(16.9, 28.2);
                BezierPoint navACp2 = new BezierPoint(25.3, 43.6);
                BezierPoint navACp3 = new BezierPoint(31.3, -5.4);
                BezierPoint navACp4 = new BezierPoint(52.4, 29.97); 
                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                magnitude = 0.6;
                distanceBetweenPoints = 0.35;
                adjustStafe = true;

                function = new BezierFunction(pointsArr);
            }
            if (this.color.equals("blue")) {
                // DONE and TESTED
                BezierPoint navACp1 = new BezierPoint(45.1, 14.8);
                BezierPoint navACp2 = new BezierPoint(52.4, 18.4);
                BezierPoint navACp3 = new BezierPoint(58.4, 47.7);
                BezierPoint navACp4 = new BezierPoint(74.7, 15.05); 
                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                magnitude = 0.6;
                distanceBetweenPoints = 0.35;
                adjustStafe = true;

                function = new BezierFunction(pointsArr);
            }
        }
    }

    // Called just before this Command runs the first time
   
    protected void initialize() {
        points = getPoints();
        System.out.println(points);
    }

    private LinkedList<CoordinatePair> getPoints() {
        double t = 0;
        CoordinatePair lastPoint = getFunctionVal(t);
        System.out.println(lastPoint);
        // LINEAR BEFORE CURVE
        LinkedList<CoordinatePair> list = new LinkedList<CoordinatePair>();
        
        // lastPoint is actually the first point right here
        double angle = Math.atan2((initialPoint.getY() - lastPoint.getY()), (initialPoint.getX() - lastPoint.getX()));
        // double deltaY = initialPoint.getY() - lastPoint.getY();
        // double deltaX = (initialPoint.getX() - lastPoint.getX());
        double deltaY = Math.sin(angle) * distanceBetweenPoints;
        double deltaX = Math.cos(angle) * distanceBetweenPoints;
        CoordinatePair a = new CoordinatePair(initialPoint.getX(), initialPoint.getY());
        double distance = Math.sqrt(Math.pow((lastPoint.getX() - initialPoint.getX()), 2) + Math.pow((lastPoint.getY() - initialPoint.getY()),2));
        for (int i = 0; i < (int) (distance / distanceBetweenPoints); i++) {
            a = new CoordinatePair(a.getX() - deltaX, a.getY() - deltaY);
            list.add(a);
        } 

        System.out.println("meow");
        
        list.add(lastPoint);
        for (int p = 0; p < POINT_COUNT; p++) {
            while (lastPoint.getDistance(getFunctionVal(t)) < distanceBetweenPoints && t < 1) {
                t += (0.001);
            }
            if (t > 1) {
                break;
            }
            lastPoint = getFunctionVal(t);
            list.add(lastPoint);
        }

        // LINEAR AFTER CURVE
        endPoint = new CoordinatePair(endPoint.getX(), lastPoint.getY());
        //lastPoint = new CoordinatePair(lastPoint.getX(), lastPoint.getY());

        // lastPoint is actually the first point right here
        angle = Math.atan2((lastPoint.getY() - endPoint.getY()), (lastPoint.getX() - endPoint.getX()));
        // double deltaY = initialPoint.getY() - lastPoint.getY();
        // double deltaX = (initialPoint.getX() - lastPoint.getX());
        deltaY = Math.sin(angle) * distanceBetweenPoints;
        deltaX = Math.cos(angle) * distanceBetweenPoints;
        a = new CoordinatePair(lastPoint.getX(), lastPoint.getY());
        distance = Math.sqrt(Math.pow((endPoint.getX() - lastPoint.getX()), 2) + Math.pow((endPoint.getY() - lastPoint.getY()),2));
        for (int i = 0; i < (int) (distance / distanceBetweenPoints); i++) {
            a = new CoordinatePair(a.getX() - deltaX, a.getY() - deltaY);
            list.add(a);
        }

        /*
         * for (double t = 0; t < 1; t += 1.0 / POINT_COUNT) {
         * list.add(getFunctionVal(t)); }
         */
        System.out.println(list);
        return (LinkedList<CoordinatePair>) list;
    }

    private CoordinatePair getFunctionVal(double t) {
        double x = 0;
        double y = 0;

        x = function.getPos(t).getX();
        y = function.getPos(t).getY();

        return new CoordinatePair(x, y);
    }

    @Override
    protected void execute() {
        System.out.println(points.size());

        try {
            if (previousPair == null) {
                previousPair = points.remove();
            }
            currentPair = points.remove();
            //currentPair.setY(currentPair.getY() * 1.37);

            double x = currentPair.getX() - previousPair.getX();
            double y = (currentPair.getY() - previousPair.getY());
            double angle = Math.atan2(-y, -x);
            angle = angle != Double.NaN ? angle : 0;

            double turn = 0; 
            // fix this drive code later
            /*
      if (Math.abs(y) > 0.01 || Math.abs(x) > 0.01) {
        double angle_from_forward = Math.PI - (Math.PI / 2 - Math.atan2(-x + 0.00001, -y + 0.00001));
        if (angle_from_forward > Math.PI) {
          angle_from_forward -= Math.PI * 2;
        }

        double gyro_angle = ((Robot.ahrs.getAngle() - Robot.init_angle) / (180)) * Math.PI;
        gyro_angle = gyro_angle % (2 * Math.PI);
        // System.out.println(gyro_angle);
        angle += gyro_angle;
        double angle_error = -(gyro_angle - angle_from_forward);
        turn = angle_error / (2 * Math.PI);
        // System.out.println(Math.atan2(y + 0.00001, x + 0.00001));
        // System.out.println((angle_error * (180 / Math.PI)));
      } */

            double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
            double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;

            if (adjustStafe) {
            double factor = 0.37 * (1 - (Math.abs(speedRFLB - speedRBLF) / ((Math.abs(speedRBLF) + Math.abs(speedRFLB)) / 1.5))) + 1;
            // System.out.println("Factor " + factor);
            speedRFLB *= factor;
            speedRBLF *= factor;
            }

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
