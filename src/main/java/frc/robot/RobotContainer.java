// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.TurretConstants;
import frc.robot.commands.drive.DriveCommand;
import frc.robot.commands.turret.TurretSetAngleCommand;
import frc.robot.commands.turret.TurretSetSpeedCommand;
import frc.robot.subsystems.AddressableLEDSubsytem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  AddressableLEDSubsytem m_led = new AddressableLEDSubsytem(
    Constants.LEDConstants.LED_PWM_PORT,
    Constants.LEDConstants.LED_LENGTH
  );

  DriveSubsystem m_robotDrive = new DriveSubsystem(
    new PWMVictorSPX(MotorConstants.FRONT_LEFT_MOTOR_PORT),
    new PWMVictorSPX(MotorConstants.REAR_LEFT_MOTOR_PORT),
    new PWMVictorSPX(MotorConstants.FRONT_RIGHT_MOTOR_PORT),
    new PWMVictorSPX(MotorConstants.REAR_RIGHT_MOTOR_PORT)
  );

  TurretSubsystem m_turret = new TurretSubsystem(
    new PWMVictorSPX(MotorConstants.TURRET_MOTOR_PORT),
    new Encoder(0, 1)
  );

  XboxController m_XboxController = new XboxController(OperatorConstants.CONTROLLER_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_robotDrive.setDefaultCommand(
      new DriveCommand(
        () -> m_XboxController.getRawAxis(OperatorConstants.CONTROLLER_LEFT_Y_AXIS),
        () -> -m_XboxController.getRawAxis(OperatorConstants.CONTROLLER_LEFT_X_AXIS),
        () -> -m_XboxController.getRawAxis(OperatorConstants.CONTROLLER_RIGHT_X_AXIS),
        m_robotDrive
      )
    );

    m_turret.setDefaultCommand(
      new TurretSetSpeedCommand(
        () -> m_XboxController.getRawAxis(OperatorConstants.CONTROLLER_RIGHT_TRIGGER) - m_XboxController.getRawAxis(OperatorConstants.CONTROLLER_LEFT_TRIGGER),
        m_turret
      )
    );

    // m_led.setDefaultCommand(
    //   new RunCommand(
    //     () -> 
    //       m_led.wave(63,0,63),
    //     m_led
    //   )
    // );
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // new Trigger(m_XboxController::getAButton)
    //   .onTrue(
    //     new TurretSetAngleCommand(TurretConstants.TURRET_SETPOINT_PULSE_1,m_turret)
    //   );

    // new Trigger(m_XboxController::getBButton)
    //   .onTrue(
    //     new TurretSetAngleCommand(TurretConstants.TURRET_SETPOINT_PULSE_2,m_turret)
    //   );

    
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
