package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.init.RunElevator;

public class Elevator extends Subsystem {
    private CANSparkMax elevatorBack;
    private CANSparkMax elevatorFront;

    private double speed = 0.25;

    public Elevator() {
        elevatorBack = new CANSparkMax(RobotMap.ELEVATOR_BACK, MotorType.kBrushless);
        elevatorFront = new CANSparkMax(RobotMap.ELEVATOR_FRONT, MotorType.kBrushless);

        // Set Motors to default and neutral
        elevatorBack.restoreFactoryDefaults();
        elevatorFront.restoreFactoryDefaults();

    }

    public void elevatorUp(double speed) {
        elevatorBack.set(-0.5);
        elevatorFront.set(speed / 2);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new RunElevator(speed));
    }

}
