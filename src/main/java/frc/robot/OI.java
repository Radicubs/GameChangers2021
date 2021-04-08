package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.auto.*;
import frc.robot.commands.toggles.*;

public class OI {
  public static Joystick controller = new Joystick(RobotMap.JOYSTICK);
  public static Button aButton = new JoystickButton(controller, RobotMap.A_BUTTON);
  public static Button xButton = new JoystickButton(controller, RobotMap.X_BUTTON);
  public static Button yButton = new JoystickButton(controller, RobotMap.Y_BUTTON);
  public static Button bButton = new JoystickButton(controller, RobotMap.B_BUTTON);
  public static Button rbButton = new JoystickButton(controller, RobotMap.RB_BUTTON);
  public static Button lbButton = new JoystickButton(controller, RobotMap.LB_BUTTON);

  public static Joystick buttonBoard = new Joystick(RobotMap.BUTTON_BOARD);
  public static Button topOne = new JoystickButton(buttonBoard, RobotMap.TOP_BUTTON_ONE);
  public static Button topTwo = new JoystickButton(buttonBoard, RobotMap.TOP_BUTTON_TWO);
  public static Button topThree = new JoystickButton(buttonBoard, RobotMap.TOP_BUTTON_THREE);
  public static Button topFour = new JoystickButton(buttonBoard, RobotMap.TOP_BUTTON_FOUR);
  public static Button bottomOne = new JoystickButton(buttonBoard, RobotMap.BOTTOM_BUTTON_ONE);
  public static Button bottomTwo = new JoystickButton(buttonBoard, RobotMap.BOTTOM_BUTTON_TWO);
  public static Button bottomThree = new JoystickButton(buttonBoard, RobotMap.BOTTOM_BUTTON_THREE);
  public static Button bottomFour = new JoystickButton(buttonBoard, RobotMap.BOTTOM_BUTTON_FOUR);

  public OI() {
    // aButton.whenPressed(new DriveToggle());
    aButton.whenPressed(new IntakeToggle());
    xButton.whenPressed(new IndexToggle());
    yButton.whenPressed(new ShooterToggle());
    rbButton.whenPressed(new ResetGyro());
    topOne.whenPressed(new TurnAndShoot());
    topTwo.whenPressed(new PowerPort());
    bButton.whenPressed(new ElevatorToggle());
  }
}
