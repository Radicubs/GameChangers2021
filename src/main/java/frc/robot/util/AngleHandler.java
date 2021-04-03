package frc.robot.util;

import frc.robot.Robot;

public class AngleHandler {

    private double initAngle;
    private double targetAngle;
    private double angleMoved;

    public AngleHandler(double targetAngle) {
        this.initAngle = Robot.ahrs.getAngle();
        this.targetAngle = targetAngle;

    }

    public boolean isFinished() {
        return (this.targetAngle - this.initAngle) - Robot.ahrs.getAngle() < 0.1;
    }

    public void setInit(double initAngle) {
        this.initAngle = initAngle;
    }

    public void setTarget(double targetAngle) {
        this.targetAngle = targetAngle;
    }

}
