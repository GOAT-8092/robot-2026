// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private AHRS navx;

  private MecanumDrive m_drive;
  private PWMSparkMax rearLeftMotor;
  private PWMSparkMax rearRightMotor;
  private PWMSparkMax frontLeftMotor;
  private PWMSparkMax frontRightMotor;

  public DriveSubsystem(PWMSparkMax frontLeftMotor, PWMSparkMax rearLeftMotor, PWMSparkMax frontRightMotor, PWMSparkMax rearRightMotor) {
    navx = new AHRS(NavXComType.kMXP_SPI);
    navx.zeroYaw();

    this.rearLeftMotor = rearLeftMotor;
    this.rearRightMotor = rearRightMotor;
    this.frontLeftMotor = frontLeftMotor;
    this.frontRightMotor = frontRightMotor;
    m_drive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
  }

  public void drive(double xSpeed, double ySpeed, double rotation, Rotation2d gyroAngle){
    m_drive.driveCartesian(xSpeed, ySpeed, rotation, gyroAngle);
  }
  public void drive(double xSpeed, double ySpeed, double rotation){
    m_drive.driveCartesian(xSpeed, ySpeed, rotation);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Yaw", navx.getYaw());
  }
}
