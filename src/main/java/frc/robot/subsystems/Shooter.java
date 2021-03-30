package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RunIndex;
import frc.robot.commands.RunShooter;

public class Shooter extends Subsystem {
    private TalonFX shooterMotorOne;
    private TalonFX shooterMotorTwo;

    private double speed = 1;

    public Shooter() {

        // constructor
        shooterMotorOne = new TalonFX(RobotMap.SHOOTER_LEFT);
        shooterMotorTwo = new TalonFX(RobotMap.SHOOTER_RIGHT);

        shooterMotorOne.configFactoryDefault();
        shooterMotorTwo.configFactoryDefault();

        shooterMotorOne.setNeutralMode(NeutralMode.Coast);
        shooterMotorTwo.setNeutralMode(NeutralMode.Coast);
    }

    public void shootBall(double speed) {
        shooterMotorOne.set(ControlMode.PercentOutput, -speed);
        shooterMotorTwo.set(ControlMode.PercentOutput, speed);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new RunShooter(speed));
    }
}
