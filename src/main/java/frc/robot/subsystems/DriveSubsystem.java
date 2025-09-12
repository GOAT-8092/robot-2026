// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

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
    m_drive.driveCartesian(xSpeed, ySpeed, rotation, gyroAngle);
  }
  public void drive(double xSpeed, double ySpeed, double rotation){
    m_drive.driveCartesian(applyDeadzone(xSpeed) * MotorConstants.DRIVE_MAX_SPEED, applyDeadzone(ySpeed) * MotorConstants.DRIVE_MAX_SPEED, applyDeadzone(rotation) * MotorConstants.DRIVE_MAX_SPEED);
  }

  public double applyDeadzone(double x){
    return Math.abs(x) > 0.1 ? x : 0;
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
