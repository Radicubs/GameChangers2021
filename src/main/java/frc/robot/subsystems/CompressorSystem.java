package frc.robot.subsystems;

import com.fasterxml.jackson.databind.util.RootNameLookup;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CompressorSystem extends Subsystem {

    private Solenoid leftSolenoid;
    private Solenoid rightSolenoid;
    public Compressor compressor;

    public CompressorSystem() {
        this.leftSolenoid = new Solenoid(RobotMap.LEFT_SOLENOID);
        this.rightSolenoid = new Solenoid(RobotMap.RIGHT_SOLENOID);
        this.compressor = new Compressor(RobotMap.COMPRESSOR);
        leftSolenoid.set(true);
        rightSolenoid.set(true);
    }

    public void CompressOn() {
        compressor.setClosedLoopControl(true);
    }

    public void initDefaultCommand() {
    }

}
