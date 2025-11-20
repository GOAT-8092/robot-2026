// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class DriveSubsystem extends SubsystemBase {
  private AHRS navx;

  private MecanumDrive m_drive;
  private PWMVictorSPX rearLeftMotor;
  private PWMVictorSPX rearRightMotor;
  private PWMVictorSPX frontLeftMotor;
  private PWMVictorSPX frontRightMotor;

  // Slew rate limiters to smooth joystick inputs and prevent stuttering
  // Rate values are in units per second (higher = more responsive, lower = smoother)
  private final SlewRateLimiter xLimiter = new SlewRateLimiter(MotorConstants.DRIVE_SLEW_RATE_X);
  private final SlewRateLimiter yLimiter = new SlewRateLimiter(MotorConstants.DRIVE_SLEW_RATE_Y);
  private final SlewRateLimiter rotateLimiter = new SlewRateLimiter(MotorConstants.DRIVE_SLEW_RATE_ROTATION);

  public DriveSubsystem(PWMVictorSPX frontLeftMotor, PWMVictorSPX rearLeftMotor, PWMVictorSPX frontRightMotor, PWMVictorSPX rearRightMotor) {
    navx = new AHRS(NavXComType.kMXP_SPI);
    navx.reset();

    this.rearLeftMotor = rearLeftMotor;
    this.rearRightMotor = rearRightMotor;
    this.frontLeftMotor = frontLeftMotor;
    this.frontRightMotor = frontRightMotor;

    this.rearRightMotor.setInverted(MotorConstants.REAR_RIGHT_MOTOR_INVERTED);
    this.frontRightMotor.setInverted(MotorConstants.FRONT_RIGHT_MOTOR_INVERTED);
    this.rearLeftMotor.setInverted(MotorConstants.REAR_LEFT_MOTOR_INVERTED);
    this.frontLeftMotor.setInverted(MotorConstants.FRONT_LEFT_MOTOR_INVERTED);

    m_drive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
  }

  public void drive(double xSpeed, double ySpeed, double rotation, Rotation2d gyroAngle){
    // Apply deadzone first
    xSpeed = applyDeadzone(xSpeed);
    ySpeed = applyDeadzone(ySpeed);
    rotation = applyDeadzone(rotation);

    // Apply exponential curve for finer control at low speeds
    xSpeed = applyExponentialCurve(xSpeed);
    ySpeed = applyExponentialCurve(ySpeed);
    rotation = applyExponentialCurve(rotation);

    // Apply slew rate limiting for smooth acceleration
    xSpeed = xLimiter.calculate(xSpeed * MotorConstants.DRIVE_MAX_SPEED);
    ySpeed = yLimiter.calculate(ySpeed * MotorConstants.DRIVE_MAX_SPEED);
    rotation = rotateLimiter.calculate(rotation * MotorConstants.DRIVE_MAX_SPEED);

    m_drive.driveCartesian(xSpeed, ySpeed, rotation, gyroAngle);
  }

  public void drive(double xSpeed, double ySpeed, double rotation){
    // Apply deadzone first
    xSpeed = applyDeadzone(xSpeed);
    ySpeed = applyDeadzone(ySpeed);
    rotation = applyDeadzone(rotation);

    // Apply exponential curve for finer control at low speeds
    xSpeed = applyExponentialCurve(xSpeed);
    ySpeed = applyExponentialCurve(ySpeed);
    rotation = applyExponentialCurve(rotation);

    // Apply slew rate limiting for smooth acceleration
    xSpeed = xLimiter.calculate(xSpeed * MotorConstants.DRIVE_MAX_SPEED);
    ySpeed = yLimiter.calculate(ySpeed * MotorConstants.DRIVE_MAX_SPEED);
    rotation = rotateLimiter.calculate(rotation * MotorConstants.DRIVE_MAX_SPEED);

    m_drive.driveCartesian(xSpeed, ySpeed, rotation);
  }

  // Raw drive method for vision alignment - bypasses deadzone and speed scaling
  public void driveRaw(double xSpeed, double ySpeed, double rotation){
    m_drive.driveCartesian(xSpeed, ySpeed, rotation);
  }

  /**
   * Apply deadzone to joystick input
   * Returns 0 if input is below threshold, otherwise returns input
   */
  public double applyDeadzone(double input){
    return Math.abs(input) > 0.1 ? input : 0;
  }

  /**
   * Apply exponential curve to joystick input for finer control at low speeds
   * Uses squared input while preserving sign
   */
  private double applyExponentialCurve(double input) {
    // Square the input while preserving the sign for smoother, more precise control
    return Math.copySign(input * input, input);
  }

  public void resetGyro(){
    navx.reset();
  }
  public double getHeading(){
    return navx.getYaw();
  }
  public Rotation2d getRotation2d(){
    return Rotation2d.fromDegrees(getHeading());
  }
  public void stop(){
    rearLeftMotor.stopMotor();
    rearRightMotor.stopMotor();
    frontLeftMotor.stopMotor();
    frontRightMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Yaw", navx.getYaw());
  }
}
