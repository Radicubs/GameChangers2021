package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveMode;
import frc.robot.commands.IndexState;
import frc.robot.commands.IntakeState;
import frc.robot.commands.ShooterState;

public class OI {
  public static Joystick controller = new Joystick(RobotMap.JOYSTICK);
  public static Button aButton = new JoystickButton(controller, RobotMap.A_BUTTON);
  public static Button xButton = new JoystickButton(controller, RobotMap.X_BUTTON);
  public static Button yButton = new JoystickButton(controller, RobotMap.Y_BUTTON);
  public static Button bButton = new JoystickButton(controller, RobotMap.B_BUTTON);

  public OI() {
    aButton.whenPressed(new DriveMode());
    xButton.whenPressed(new IntakeState());
    yButton.whenPressed(new IndexState());
    bButton.whenPressed(new ShooterState());
    bButton.whenPressed(new ElevatorState());
    ;
  }
}
