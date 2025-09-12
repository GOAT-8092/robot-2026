// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
  /** Creates a new TurretSubsystem. */
  PWMVictorSPX turretMotor;
  Encoder turretEncoder;

  public TurretSubsystem(PWMVictorSPX turretMotor, Encoder turrretEncoder) {
    this.turretMotor = turretMotor;
    this.turretEncoder = turrretEncoder;
    
  }

  public double getAngle(){
    return turretEncoder.get() / TurretConstants.TURRET_ENCODER_PULSE_PER_REVOLUTION * TurretConstants.TURRET_GEAR_RATIO;
  }

  public void setAngle(double angle){ // !NOT TESTED 
    double currentAngle = getAngle();
    double error = angle - currentAngle;
    double kP = 0.01;
    double output = kP * error;
    turretMotor.set(output);
  }

  public void setAnglebyPulse(int pulse){
    double error = pulse - turretEncoder.get();
    double kP = 1 / TurretConstants.TURRET_ENCODER_PULSE_PER_REVOLUTION / 10;
    double output = kP * error;
    turretMotor.set(output);
  }

  public void setSpeed(double speed){
    turretMotor.set(speed);
  }

  public boolean atSetpoint(double angle){
    return Math.abs(angle - turretEncoder.getDistance()) < 0.5;
  }
  public boolean atSetpointbyPulse(double pulse){
    return Math.abs(pulse - turretEncoder.get()) < TurretConstants.TURRET_ENCODER_PULSE_PER_REVOLUTION / 4;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
