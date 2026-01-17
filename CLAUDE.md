# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Important: Check PARTS.md First

**Before making any hardware-related changes** (motor ports, sensor connections, network configuration, controller mappings, etc.), **ALWAYS check PARTS.md** for the current hardware configuration.

**Available Languages:**
- **PARTS.md** - English version
- **PARTS_TR.md** - Turkish version (Türkçe)

**⚠️ CRITICAL: When updating PARTS.md files, you MUST update BOTH versions to keep them in sync.** If you modify hardware information in one language, apply the same changes to the other language version immediately.

PARTS.md/PARTS_TR.md are the authoritative sources for:
- Port assignments (PWM, MXP/SPI, USB, Network)
- Motor configurations and inversions
- Controller button mappings
- Hardware specifications and limitations
- Network addresses

These files contain detailed tables and specifications that must be verified before modifying hardware-related code.

## Project Overview

FRC Team 8092 (G.O.A.T. - "Greatest of All Times") robot code for the 2026 REBUILT season. This is a WPILib Java command-based robot project featuring:
- Mecanum drive system with field-oriented control
- Vision-based AprilTag alignment using Limelight 3
- Turret subsystem with encoder feedback
- Addressable LED subsystem (currently disabled)

## 2026 Season Information

**Game**: REBUILDTM (presented by Haas)
**Kickoff**: January 10, 2026

### Game Documentation
- **2026GameManual-TU.pdf** - Official 2026 FRC Game Manual (Turkish)
- **2026GameManual-TU.md** - Markdown conversion of the game manual (387 KB, 169 pages)

The game manual contains complete rules, arena specifications, robot requirements, and tournament details for the 2026 REBUILT season.

## Build and Development Commands

### Building and Deploying
```bash
# Build the project
./gradlew build

# Deploy to RoboRIO
./gradlew deploy

# Run tests
./gradlew test
```

### Simulation
```bash
# Run robot simulation with GUI
./gradlew simulateJava

# Run with specific arguments
./gradlew simulateJava --args="--gui"
```

### Code Quality
```bash
# Clean build artifacts
./gradlew clean

# View project dependencies
./gradlew dependencies
```

## Architecture

### Command-Based Framework Structure

This robot follows WPILib's command-based programming paradigm:

- **Robot.java** - Main robot class, extends TimedRobot, schedules CommandScheduler
- **RobotContainer.java** - Contains all subsystems, controllers, and button bindings. This is where the robot structure is declared
- **Constants.java** - Centralized configuration with nested classes for each subsystem

### Subsystems

**DriveSubsystem** (src/main/java/frc/robot/subsystems/DriveSubsystem.java)
- Mecanum drive with 4x CIM motors controlled by VEX Pro Victor SPX motor controllers
- Each motor connected to AndyMark Toughbox Mini gearbox (12.75:1 reduction)
- Controllers use PWMVictorSPX class for PWM control
- navX-MXP AHRS gyro (connected via MXP SPI) for field-oriented control
- Slew rate limiters for smooth acceleration
- Exponential curve applied to joystick inputs for fine control
- Methods: `drive()`, `driveRaw()`, `resetGyro()`, `getHeading()`, `getRotation2d()`
- Motor ports defined in `MotorConstants`

**TurretSubsystem** (src/main/java/frc/robot/subsystems/TurretSubsystem.java)
- PWMVictorSPX motor controlled by E38S6G5-400B-G24N encoder
- Gear ratio: 18:38 reduction
- Encoder: 400 pulses per revolution
- Simplified angle positioning using pulse counting

**AddressableLEDSubsytem** (src/main/java/frc/robot/subsystems/AddressableLEDSubsytem.java)
- 300-LED strip on PWM port 9
- Currently disabled in RobotContainer.java (commented out)

### Commands

Commands are organized by subsystem in subdirectories:

**Drive Commands** (src/main/java/frc/robot/commands/drive/)
- `DriveCommand` - Default command for teleop driving with joystick inputs
- `DriveFieldRelativeCommand` - Field-oriented driving using gyro
- `StabilizeRobotCommand` - Robot stabilization logic

**Vision Commands** (src/main/java/frc/robot/commands/vision/)
- `AlignToAprilTagCommand` - Autonomous alignment to AprilTags 12 and 15
  - Uses dual PID controllers for rotation and distance
  - Processes Limelight data via LimelightHelpers
  - Extensive debug logging every 10 executions
  - Bound to X button (whileTrue)

**Turret Commands** (src/main/java/frc/robot/commands/turret/)
- `TurretSetAngleCommand` - Set turret to specific angle using pulse count
- `TurretSetSpeedCommand` - Manual turret speed control

### Vision System

The robot uses a Limelight 3 camera for AprilTag detection:
- **LimelightHelpers.java** - Complete Limelight API wrapper (vendor library helper)
- Camera: OV5647 color rolling shutter (640x480 @ 90 FPS)
- Field of View: 62.5° horizontal, 48.9° vertical
- Network address: 10.80.92.200
- Web interface: http://10.80.92.200:5801
- AprilTag pipeline for game piece alignment
- Targets tags 12 and 15
- Constants in `VisionConstants` include PID tuning and speed limits

### Control System

#### Controller Support

The code supports both Xbox and Logitech controllers with separate button mappings for each type. See PARTS.md for complete controller configuration details.

- **Controller**: XboxController class on port 0
- **Controller Type Selection**: Set in `Constants.java` via `ACTIVE_CONTROLLER`:
  - `ControllerType.XBOX` - For Xbox or Logitech in XInput mode
  - `ControllerType.LOGITECH` - For Logitech in DirectInput mode
- **Controller Port Assignment**: Can be changed in FRC Driver Station USB Devices tab (no code change required)

#### Default Drive Mapping

- **Left stick Y-axis**: Forward/backward
- **Left stick X-axis**: Strafe left/right
- **Z-axis (twist)**: Rotation

#### Button Bindings

Current button/axis mappings are defined in `Constants.java` under `OperatorConstants`:

**Axes:**
- Left Stick X (axis 0): Strafe left/right
- Left Stick Y (axis 1): Forward/backward
- Z-axis (axis 2): Rotation

**Buttons (Xbox mapping):**
- X Button (3): AlignToAprilTagCommand (whileTrue)
- A Button (1): Available
- B Button (2): Available
- Y Button (4): Available
- Left/Right Bumpers (5/6): Available
- Start/Select (7/8): Available

**Note**: For Logitech DirectInput mode, buttons are mapped differently. See `LogitechMapping` class in Constants.java and PARTS.md for calibration instructions.

## Key Implementation Details

### Drive System Characteristics

The DriveSubsystem applies these transformations in order:
1. Deadzone (0.1 threshold)
2. Exponential curve (squared input preserving sign)
3. Slew rate limiting for smooth acceleration
4. Max speed scaling (0.7 default)

The `driveRaw()` method bypasses all filtering for vision commands.

### Vision Alignment Logic

AlignToAprilTagCommand implements:
- Rotation PID to center target (tx = 0)
- Distance PID to reach target area (default 1.3%)
- Simultaneous rotation and forward/backward movement
- Reduced forward speed when misaligned (>15° = 50%, >8° = 70%)
- Only aligns to tags 12 and 15
- Uses negative X-axis values for forward motion

### Motor Inversion

Motor inversion is CRITICAL and configured per motor in Constants:
- REAR_LEFT: inverted (true)
- FRONT_LEFT: inverted (true)
- FRONT_RIGHT: not inverted (false)
- REAR_RIGHT: not inverted (false)

## Vendor Dependencies

Located in `vendordeps/`:
- **Studica-2025.0.1.json** - Studica hardware support (NavX gyro)
- **WPILibNewCommands.json** - WPILib command framework

## Configuration

- Team number: 8092
- Java version: 17
- WPILib version: 2025.3.2
- GradleRIO handles RoboRIO deployment and artifact management

### Network Addresses
- Radio: 10.80.92.11 (Vivid-Hosting VH-109)
- roboRIO: 10.80.92.4 (NI roboRIO 1.0)
- Limelight: 10.80.92.200 (Limelight 3)

## Testing

JUnit 5 is configured for unit tests:
```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "ClassName"
```

Test files should be placed in `src/test/java/`.

## Important Notes

- Motor inversion is critical and configured per motor in Constants
- VEX Pro Victor SPX controllers use PWMVictorSPX class for PWM control
- Victor SPX controllers support 100A continuous, 130A peak current
- Limelight LED mode is forced on during AprilTag alignment
- The robot coordinate system uses X for forward/backward, Y for strafe
- NavX gyro is reset in DriveSubsystem constructor
- SmartDashboard values are logged for vision debugging (Vision/* keys)
- Limelight 3 documentation: https://docs.limelightvision.io/tr/docs/docs-limelight/getting-started/limelight-3

## Hardware Specifications (Reference)

See PARTS.md for complete specifications. Key constraints:

### Motor Specifications (2.5" CIM)
- Free Speed: ~5,330 RPM
- Stall Torque: ~2.41 N⋅m (343 oz⋅in)
- Stall Current: ~131 A
- Free Current: ~2.7 A

### Drive System Calculations
- Gearbox Ratio: 12.75:1 (AndyMark Toughbox Mini Classic)
- Output Speed: ~418 RPM at wheels (5,330 RPM ÷ 12.75)
- Max Speed: 70% (configurable in Constants.java)
- Slew Rate: 2.5 units/sec (X/Y), 2.0 units/sec (rotation)

### Vision System Limits
- Target Distance: 1.3% camera area
- Max Drive Speed: 0.4 (reduced for vision)
- Max Rotation Speed: 0.25 (reduced for vision)
- Rotation PID: kP=0.03, kI=0.0, kD=0.006
- Drive PID: kP=0.1, kI=0.0, kD=0.005

### Quick Port Reference
| Port | Device |
|------|--------|
| PWM 1 | Drive - Rear Left Motor (Inverted) |
| PWM 2 | Drive - Front Right Motor |
| PWM 3 | Drive - Front Left Motor (Inverted) |
| PWM 4 | Drive - Rear Right Motor |
| PWM 9 | Addressable LED Strip (disabled) |
| MXP SPI | NavX Gyroscope |
| USB 0 | Primary Controller |
| 10.80.92.4 | roboRIO |
| 10.80.92.11 | FRC Radio |
| 10.80.92.200 | Limelight 3 |
