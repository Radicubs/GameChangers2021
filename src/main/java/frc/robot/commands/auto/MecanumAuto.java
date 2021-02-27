package frc.robot.commands.auto;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.CoordinatePair;

public class MecanumAuto extends Command {

    private Queue<CoordinatePair> points;
    private CoordinatePair previousPair;
    private CoordinatePair currentPair;
    private final int POINT_COUNT = 10000;
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

    private LinkedList<CoordinatePair> getPoints() {

        List<CoordinatePair> list = new LinkedList<CoordinatePair>();
        for (int t = 0; t < POINT_COUNT; t++) {
            list.add(getFunctionVal(t));
        }
        return (LinkedList<CoordinatePair>) list;
    }

    private CoordinatePair getFunctionVal(int t) {
        // insert function code here
        switch (this.path) {
            case "AutoNavA":
                break;
            case "AutoNavB":
                break;
            case "AutoNavC":
                break;

        }
        int x = 0;
        int y = 0;
        return new CoordinatePair(x, y);
    }

    @Override
    protected void execute() {
        try {
            // double originalY = points.peek().getY();
            // double originalX = points.remove().getX();
            currentPair = points.remove();
            double turn = 0;
            if (previousPair == null) {
                previousPair = points.remove();
            }

            double x = currentPair.getX() - previousPair.getY();
            double y = (currentPair.getY() - previousPair.getY());
            double angle = Math.atan2(x, y);
            angle = angle != Double.NaN ? angle : 0;

            // Normalize Values
            x = Math.cos(angle);
            y = Math.sin(angle);

            // double magnitude = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
            double magnitude = 1;

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
