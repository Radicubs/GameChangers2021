package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Drive extends Command {
    private double angle, magnitude, turn;
    private boolean limelight = false;

    public Drive(double angle, double magnitude, double turn) {
        requires(Robot.driveTrain);
        this.angle = angle;
        this.magnitude = -magnitude;
        this.turn = turn;
    }

    public Drive(boolean limelight) {
        requires(Robot.driveTrain);
        if (limelight) {
            this.limelight = limelight;
        }
    }

    @Override
    public void initialize() {
    }

    @Override
    public boolean isFinished() {
        // if (limelight) {
        // return (Math.abs(this.magnitude) < 0.10);
        // } else {
        return false;
        // }
    }

    @Override
    /*
     * public void execute() { if (limelight) { if
     * (Robot.limeLight.calculateDistance() > 10) { this.magnitude =
     * -(Robot.limeLight.calculateDistance() - 65) / 35; if
     * (Math.abs(this.magnitude) < 0.1) { this.magnitude = -0.1; }
     * System.out.println(this.magnitude); this.angle = 0; } // Drive! //
     * Robot.driveBase.drive(-0.1, 0.1); double x =
     * Robot.limeLight.getTable().getEntry("tx").getDouble(0.0);
     * System.out.println(Robot.limeLight.getTable().getEntry("tx").getDouble(0.0));
     * if (Math.abs(x) > 6) { this.turn = ((x) / 180); } } double speedRFLB =
     * Math.sin(angle + (Math.PI / 4)) * magnitude; double speedRBLF =
     * Math.sin(angle - (Math.PI / 4)) * magnitude; // if (turn < 0.05) { // turn -=
     * ((Robot.ahrs.getAngle() - Robot.init_angle) / (180)); // }
     * 
     * Robot.driveTrain.drive(speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn,
     * -speedRFLB + turn); }
     */
    public void execute() {
        try {
            double turn = 0;

            turn *= 0.5 / 2;

            double angle_offset = ((Robot.ahrs.getAngle() - Robot.init_angle) / (180)) * Math.PI;

            if (turn < 0.05) {
                turn -= ((Robot.ahrs.getAngle() - Robot.init_angle) / (180));
            }

            double speedRFLB = Math.sin(angle + angle_offset + (Math.PI / 4)) * magnitude;
            double speedRBLF = Math.sin(angle + angle_offset - (Math.PI / 4)) * magnitude;

            /*
             * if ((Math.abs(speedRFLB - speedRBLF) / ((Math.abs(speedRBLF) +
             * Math.abs(speedRFLB)) / 1.5)) < 1 && (Math.abs(speedRFLB - speedRBLF) /
             * ((Math.abs(speedRBLF) + Math.abs(speedRFLB)) / 1.5)) > 0) { double factor =
             * 0.6 (1 - (Math.abs(speedRFLB - speedRBLF) / ((Math.abs(speedRBLF) +
             * Math.abs(speedRFLB)) / 1.5))) + 1; // System.out.println("Factor " + factor);
             * speedRFLB *= factor; speedRBLF *= factor; }
             */
            System.out.println("Speed: " + speedRBLF);

            Robot.driveTrain.drive(speedRFLB + turn, -speedRBLF + turn, speedRBLF + turn, -speedRFLB + turn);
        } catch (Exception e) {
            System.out.println("Got exception: " + e);
        }
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
