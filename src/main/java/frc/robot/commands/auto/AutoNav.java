package frc.robot.commands.auto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.*;

public class AutoNav extends Command {
    private Queue<CoordinatePair> points;
    private CoordinatePair previousPair;
    private CoordinatePair currentPair;
    private final double POINT_COUNT = 10000.0;
    private final double STRETCH_FACTOR = 1;
    private BezierFunction function;
    private String path;

    public AutoNav(String path) {
        this.path = path;
        if (this.path.equals("Barrel Racing")) {
            BezierPoint barrelCp1 = new BezierPoint(14, 21.3);
            BezierPoint barrelCp2 = new BezierPoint(41.8, 33.3);
            BezierPoint barrelCp3 = new BezierPoint(54.5, 16);
            BezierPoint barrelCp4 = new BezierPoint(41, 16.14);
            BezierPoint barrelCp5 = new BezierPoint(51.8, -10.3);
            BezierPoint barrelCp6 = new BezierPoint(31, -7.9);
            BezierPoint barrelCp7 = new BezierPoint(6.5, 17.4);
            BezierPoint barrelCp8 = new BezierPoint(10.9, 53.5);

            BezierPoint barrelCp9 = new BezierPoint(43.5, 22.7);
            BezierPoint barrelCp10 = new BezierPoint(30.3, 33.7);
            BezierPoint barrelCp11 = new BezierPoint(107.4, -29.7);

            BezierPoint barrelCp12 = new BezierPoint(148.6, 39.5);
            BezierPoint barrelCp13 = new BezierPoint(81.7, 56.3);
            BezierPoint barrelCp14 = new BezierPoint(12.2, 72.5);
            BezierPoint barrelCp15 = new BezierPoint(5.1, 67);
            BezierPoint barrelCp16 = new BezierPoint(39.8, 0.9);

            BezierPoint barrelCp17 = new BezierPoint(46, -21.2);
            BezierPoint barrelCp18 = new BezierPoint(123.7, -47.6);
            BezierPoint barrelCp19 = new BezierPoint(111, 65.6);
            BezierPoint barrelCp20 = new BezierPoint(96.4, 24.3);
            BezierPoint barrelCp21 = new BezierPoint(47.2, 21.5);
            BezierPoint barrelCp22 = new BezierPoint(29.4, 27.7);
            BezierPoint barrelCp23 = new BezierPoint(15, 27.5);
            BezierPoint[] pointsArr = { barrelCp1, barrelCp2, barrelCp3, barrelCp4, barrelCp5, barrelCp6, barrelCp7,
                    barrelCp8, barrelCp9, barrelCp10, barrelCp11, barrelCp12, barrelCp13, barrelCp14, barrelCp15,
                    barrelCp16, barrelCp17, barrelCp18, barrelCp19, barrelCp20, barrelCp21, barrelCp22, barrelCp23 };

            function = new BezierFunction(pointsArr);

        } else if (this.path.equals("Bounce")) {
            BezierPoint bounceCp1 = new BezierPoint(-26.9, -1.3);
            BezierPoint bounceCp2 = new BezierPoint(-12.6, -2.9);
            BezierPoint bounceCp3 = new BezierPoint(-18.2, 59);
            BezierPoint bounceCp4 = new BezierPoint(-23.6, -4.4);
            BezierPoint bounceCp5 = new BezierPoint(-22.5, -11.7);
            BezierPoint bounceCp6 = new BezierPoint(-5.1, -55.4);
            BezierPoint bounceCp7 = new BezierPoint(26.4, -48.7);
            BezierPoint bounceCp8 = new BezierPoint(-28.1, -3);

            BezierPoint bounceCp9 = new BezierPoint(32, 137);
            BezierPoint bounceCp10 = new BezierPoint(-14.5, 11.4);
            BezierPoint bounceCp11 = new BezierPoint(-16, -28.4);

            BezierPoint bounceCp12 = new BezierPoint(-13.2, -42.2);
            BezierPoint bounceCp13 = new BezierPoint(60.3, -50);
            BezierPoint bounceCp14 = new BezierPoint(7.2, 39.6);
            BezierPoint bounceCp15 = new BezierPoint(11.3, 18.6);
            BezierPoint bounceCp16 = new BezierPoint(27.2, 3.2);
            BezierPoint[] pointsArr = { bounceCp1, bounceCp2, bounceCp3, bounceCp4, bounceCp5, bounceCp6, bounceCp7,
                    bounceCp8, bounceCp9, bounceCp10, bounceCp11, bounceCp12, bounceCp13, bounceCp14, bounceCp15,
                    bounceCp16 };

            function = new BezierFunction(pointsArr);

        } else if (this.path.equals("Slalom")) {
            BezierPoint slalomCp1 = new BezierPoint(12.6, 6.3);
            BezierPoint slalomCp2 = new BezierPoint(40.4, 43.4);
            BezierPoint slalomCp3 = new BezierPoint(84.9, 47);
            BezierPoint slalomCp4 = new BezierPoint(59.8, -30.3);
            BezierPoint slalomCp5 = new BezierPoint(104, -30.2);

            BezierPoint slalomCp6 = new BezierPoint(90.6, 35.4);
            BezierPoint slalomCp7 = new BezierPoint(72.7, 78);
            BezierPoint slalomCp8 = new BezierPoint(71, -8.1);

            BezierPoint slalomCp9 = new BezierPoint(81.5, 1.2);
            BezierPoint slalomCp10 = new BezierPoint(36.3, 1);
            BezierPoint slalomCp11 = new BezierPoint(23.8, 1.9);

            BezierPoint slalomCp12 = new BezierPoint(14.8, 30);

            BezierPoint[] pointsArr = { slalomCp1, slalomCp2, slalomCp3, slalomCp4, slalomCp5, slalomCp6, slalomCp7,
                    slalomCp8, slalomCp9, slalomCp10, slalomCp11, slalomCp12 };

            function = new BezierFunction(pointsArr);

        }
    }

    @Override
    protected void initialize() {
        try {
            points = getPoints();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<CoordinatePair> getPoints() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("BarrelRacing.txt", "UTF-8");
        double t = 0;
        LinkedList<CoordinatePair> list = new LinkedList<CoordinatePair>();
        CoordinatePair previousPoint = getFunctionVal(t);
        for (int p = 0; p < POINT_COUNT; p++) {
            while (previousPoint.getDistance(getFunctionVal(t)) < 0.25 && t < 1) {
                t += (0.001);
            }
            if (t > 1) {
                break;
            }
            previousPoint = getFunctionVal(t);
            list.add(previousPoint);
            writer.println(previousPoint);
        }
        writer.close();
        System.out.println(list);
        return (LinkedList<CoordinatePair>) list;
    }

    @Override
    protected void execute() {
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

            double magnitude = 0.3;

            double speedRFLB = Math.sin(angle + (Math.PI / 4)) * magnitude;
            double speedRBLF = Math.sin(angle - (Math.PI / 4)) * magnitude;

            Robot.driveTrain.drive(speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn, -speedRFLB + turn);

            previousPair = currentPair;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CoordinatePair getFunctionVal(double t) {
        double x = 0;
        double y = 0;

        x = function.getPos(t).getX();
        y = function.getPos(t).getY();

        return new CoordinatePair(x, y);
    }

    @Override
    protected boolean isFinished() {
        return points.isEmpty();
    }

}
