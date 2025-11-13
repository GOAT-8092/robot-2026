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
    // The current joystick's twist (Z) axis reports on index 2 and is used for robot rotation.
    public static final int CONTROLLER_Z_AXIS = 2;
    public static final int CONTROLLER_LEFT_TRIGGER = 2;
    public static final int  CONTROLLER_RIGHT_TRIGGER = 3;
    public static final int CONTROLLER_RIGHT_X_AXIS = 4;
    public static final int CONTROLLER_RIGHT_Y_AXIS = 5;
  }

  public static final class LEDConstants {
    public static final int LED_PWM_PORT = 9;
    public static final int LED_LENGTH = 300;
  }

  public static final class TurretConstants{
    public static final double TURRET_MAX_ANGLE = 90.0;
    public static final double TURRET_MIN_ANGLE = 0;
    public static final double TURRET_MAX_SPEED = 0.5;
    public static final double TURRET_MIN_SPEED = -0.5;
    public static final double TURRET_SETPOINT_TOLERANCE = 1.0;
    public static final double TURRET_SETPOINT_1 = 0.0;
    public static final double TURRET_SETPOINT_2 = 45.0;
    public static final int TURRET_SETPOINT_PULSE_1 = 0;
    public static final int TURRET_SETPOINT_PULSE_2 = 4000; // NOT SETTED
    public static final double TURRET_kP = 0.01;
    public static final double TURRET_ENCODER_PULSE_PER_REVOLUTION = 400.0; // E38S6G5-400B-G24N
    public static final double TURRET_GEAR_RATIO = 18.0/38.0;
  }
  
  public static final class MotorConstants{
    // Motor Ports
    public static final int REAR_LEFT_MOTOR_PORT = 1;
    public static final int FRONT_RIGHT_MOTOR_PORT = 2;
    public static final int REAR_RIGHT_MOTOR_PORT = 4;
    public static final int FRONT_LEFT_MOTOR_PORT = 3;

    public static final boolean REAR_LEFT_MOTOR_INVERTED = true;
    public static final boolean FRONT_RIGHT_MOTOR_INVERTED = false;
    public static final boolean REAR_RIGHT_MOTOR_INVERTED = false;
    public static final boolean FRONT_LEFT_MOTOR_INVERTED = true;

    public static final double DRIVE_MAX_SPEED = 0.7;

    public static final int TURRET_MOTOR_PORT = 6;

    // Motor Inversion
  }
}
