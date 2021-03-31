package frc.robot.commands.auto;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import frc.robot.Robot;
import frc.robot.util.BezierFunction;
import frc.robot.util.BezierPoint;
import frc.robot.util.CoordinatePair;

public class TestBezier {


    private static Queue<CoordinatePair> points;
    private static CoordinatePair previousPair;
    private static CoordinatePair currentPair;
    private static final double POINT_COUNT = 10000.0;
    private final double STRETCH_FACTOR = 1;
    private static String path;
    private static String color;
    private static BezierFunction function;
    private static CoordinatePair initialPoint = new CoordinatePair(8.5, 23);

    private static CoordinatePair getFunctionVal(double t) {
        double x = 0;
        double y = 0;

        x = function.getPos(t).getX();
        y = function.getPos(t).getY();

        return new CoordinatePair(x, y);
    }
    public static void main(String[] args) {
        BezierPoint navACp1 = new BezierPoint(17.4, 23.1);
                BezierPoint navACp2 = new BezierPoint(32.6, 24.2);
                BezierPoint navACp3 = new BezierPoint(41.5, -4);
                BezierPoint navACp4 = new BezierPoint(44.7, 37.6);
                BezierPoint[] pointsArr = { navACp1, navACp2, navACp3, navACp4 };

                function = new BezierFunction(pointsArr);

       // LINEAR BEFORE CURVE
       LinkedList<CoordinatePair> list = new LinkedList<CoordinatePair>();
       double t = 0;
       CoordinatePair lastPoint = getFunctionVal(t);
       // lastPoint is actually the first point right here
       double angle = Math.atan2((initialPoint.getY() - lastPoint.getY()), (initialPoint.getX() - lastPoint.getX()));
       // double deltaY = initialPoint.getY() - lastPoint.getY();
       // double deltaX = (initialPoint.getX() - lastPoint.getX());
       double deltaY = Math.sin(angle) * 0.10;
       double deltaX = Math.cos(angle) * 0.10;
       CoordinatePair a = new CoordinatePair(initialPoint.getX(), initialPoint.getY());
       double distance = Math.sqrt(Math.pow((lastPoint.getX() - initialPoint.getX()), 2) + Math.pow((lastPoint.getY() - initialPoint.getY()),2));
       for (int i = 0; i < (int) (distance / 0.10); i++) {
           a = new CoordinatePair(a.getX() + deltaX, a.getY() + deltaY);
           list.add(a);
       }


       System.out.println("meow");
       
       list.push(lastPoint);
       for (int p = 0; p < POINT_COUNT; p++) {
           while (lastPoint.getDistance(getFunctionVal(t)) < 0.10 && t < 1) {
               t += (0.001);
           }
           if (t > 1) {
               break;
           }
           lastPoint = getFunctionVal(t);
           list.add(lastPoint);
       }

       /*
        * for (double t = 0; t < 1; t += 1.0 / POINT_COUNT) {
        * list.add(getFunctionVal(t)); }
        */
       System.out.println(list);

    }
}
