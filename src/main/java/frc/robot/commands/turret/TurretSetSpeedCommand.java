// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TurretSetSpeedCommand extends Command {
  /** Creates a new TurretSetSpeedCommand. */
  private final TurretSubsystem turretSubsystem;
  private final DoubleSupplier speed;

  public TurretSetSpeedCommand(DoubleSupplier speed, TurretSubsystem turretSubsystem) {
    this.turretSubsystem = turretSubsystem;
    this.speed = speed;
    addRequirements(turretSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turretSubsystem.setSpeed(speed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
