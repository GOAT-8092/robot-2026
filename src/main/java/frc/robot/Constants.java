// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    //CONTROLLER
    public static final int CONTROLLER_PORT = 0;

    public static final int CONTROLLER_BUTTON_A = 1;
    public static final int CONTROLLER_BUTTON_B = 2;
    public static final int CONTROLLER_BUTTON_X = 3;
    public static final int CONTROLLER_BUTTON_Y = 4;
    public static final int CONTROLLER_LEFT_BUTTON = 5;
    public static final int CONTROLLER_RIGHT_BUTTON = 6;
    public static final int CONTROLLER_SELECT = 7;
    public static final int CONTROLLER_START = 8;
    public static final int CONTROLLER_LEFT_STICK_BUTTON = 9;
    public static final int CONTROLLER_RIGHT_STICK_BUTTON = 10;
    
    public static final int CONTROLLER_LEFT_X_AXIS = 0;
    public static final int CONTROLLER_LEFT_Y_AXIS = 1;
    public static final int CONTROLLER_LEFT_TRIGGER = 2;
    public static final int  CONTROLLER_RIGHT_TRIGGER = 3;
    public static final int CONTROLLER_RIGHT_X_AXIS = 4;
    public static final int CONTROLLER_RIGHT_Y_AXIS = 5;
  }

  public static final class LEDConstants {
    public static final int LED_PWM_PORT = 9;
    public static final int LED_LENGTH = 300;
  }

  public static final class MotorConstants{
    // Motor Ports
    public static final int REAR_LEFT_MOTOR_PORT = 1;
    public static final int FRONT_RIGHT_MOTOR_PORT = 2;
    public static final int REAR_RIGHT_MOTOR_PORT = 3;
    public static final int FRONT_LEFT_MOTOR_PORT = 4;

    public static final int TURRET_MOTOR_PORT = 6;

    // Motor Inversion
  }
}
