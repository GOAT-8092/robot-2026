// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {
  /** Creates a new TurretSubsystem. */
  PWMVictorSPX turretMotor;
  Encoder turretEncoder;

  public TurretSubsystem(PWMVictorSPX turretMotor, Encoder turrretEncoder) {
    this.turretMotor = turretMotor;
    this.turretEncoder = turrretEncoder;
  }

  public void setAngle(double angle){ // !NOT TESTED 
    double currentAngle = turretEncoder.getDistance();
    double error = angle - currentAngle;
    double kP = 0.01;
    double output = kP * error;
    turretMotor.set(output);
  }

  public void setSpeed(double speed){
    turretMotor.set(speed);
  }

  public boolean atSetpoint(double angle){
    return Math.abs(angle - turretEncoder.getDistance()) < 0.5;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
