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
    private final double STRETCH_FACTOR = 1;
    private String path;
    private String color;
    private BezierFunction function;
    private CoordinatePair initialPoint = new CoordinatePair(8.5, 23);
    private static CoordinatePair endPoint = new CoordinatePair(82, 23);

    public GalacticSearch(String path, String color) {
        IntakeToggle toggle = new IntakeToggle();
        toggle.start();
        requires(Robot.driveTrain);
        this.path = path;
        this.color = color;
        if (this.path.equals("A")) {
            if (this.color.equals("red")) {
                
                BezierPoint navACp1 = new BezierPoint(17.4, 23.1);
                BezierPoint navACp2 = new BezierPoint(32.6, 24.2);
                BezierPoint navACp3 = new BezierPoint(41.5, -4);
                BezierPoint navACp4 = new BezierPoint(44.7, 37.6); 
                /*
                BezierPoint navACp1 = new BezierPoint(41.5, -9.5);
                BezierPoint navACp2 = new BezierPoint(-27.3, 68);
                BezierPoint navACp3 = new BezierPoint(34, -69);
                BezierPoint navACp4 = new BezierPoint(6.7, 8.3); 
                BezierPoint navACp5 = new BezierPoint(-29.8, -9.5); */


                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                function = new BezierFunction(pointsArr);
            }
            if (this.color.equals("blue")) {
                
                BezierPoint navACp1 = new BezierPoint(17.4, 23.1);
                BezierPoint navACp2 = new BezierPoint(32.6, 24.2);
                BezierPoint navACp3 = new BezierPoint(41.5, -4);
                BezierPoint navACp4 = new BezierPoint(44.7, 37.6); 
                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                function = new BezierFunction(pointsArr);
            }
        }
        if (this.path.equals("B")) {
            if (this.color.equals("red")) {
                
                BezierPoint navACp1 = new BezierPoint(17.4, 23.1);
                BezierPoint navACp2 = new BezierPoint(32.6, 24.2);
                BezierPoint navACp3 = new BezierPoint(41.5, -4);
                BezierPoint navACp4 = new BezierPoint(44.7, 37.6); 
                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                function = new BezierFunction(pointsArr);
            }
            if (this.color.equals("blue")) {
                
                BezierPoint navACp1 = new BezierPoint(17.4, 23.1);
                BezierPoint navACp2 = new BezierPoint(32.6, 24.2);
                BezierPoint navACp3 = new BezierPoint(41.5, -4);
                BezierPoint navACp4 = new BezierPoint(44.7, 37.6); 
                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                function = new BezierFunction(pointsArr);
            }
        }
    }
        
    // Called just before this Command runs the first time
   
    protected void initialize() {
        points = getPoints();
    }

    private LinkedList<CoordinatePair> getPoints() {
        double t = 0;
        CoordinatePair lastPoint = getFunctionVal(t);
        
        // LINEAR BEFORE CURVE
        LinkedList<CoordinatePair> list = new LinkedList<CoordinatePair>();
        
        // lastPoint is actually the first point right here
        double angle = Math.atan2((initialPoint.getY() - lastPoint.getY()), (initialPoint.getX() - lastPoint.getX()));
        // double deltaY = initialPoint.getY() - lastPoint.getY();
        // double deltaX = (initialPoint.getX() - lastPoint.getX());
        double deltaY = Math.sin(angle) * 0.20;
        double deltaX = Math.cos(angle) * 0.20;
        CoordinatePair a = new CoordinatePair(initialPoint.getX(), initialPoint.getY());
        double distance = Math.sqrt(Math.pow((lastPoint.getX() - initialPoint.getX()), 2) + Math.pow((lastPoint.getY() - initialPoint.getY()),2));
        for (int i = 0; i < (int) (distance / 0.20); i++) {
            a = new CoordinatePair(a.getX() - deltaX, a.getY() - deltaY);
            list.add(a);
        } 

        System.out.println("meow");
        
        list.push(lastPoint);
        for (int p = 0; p < POINT_COUNT; p++) {
            while (lastPoint.getDistance(getFunctionVal(t)) < 0.20 && t < 1) {
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
        deltaY = Math.sin(angle) * 0.20;
        deltaX = Math.cos(angle) * 0.20;
        a = new CoordinatePair(lastPoint.getX(), lastPoint.getY());
        distance = Math.sqrt(Math.pow((endPoint.getX() - lastPoint.getX()), 2) + Math.pow((endPoint.getY() - lastPoint.getY()),2));
        for (int i = 0; i < (int) (distance / 0.20); i++) {
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

            double x = currentPair.getX() - previousPair.getX();
            // x *= 1.37;
            double y = (currentPair.getY() - previousPair.getY());
            double angle = Math.atan2(-y, -x);
            angle = angle != Double.NaN ? angle : 0;

            double turn = 0; 
            // fix this drive code later
            
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
        System.out.println(Math.atan2(y + 0.00001, x + 0.00001));
        // System.out.println((angle_error * (180 / Math.PI)));
      }

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
