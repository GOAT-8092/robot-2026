# PARTS.md

Hardware components and parts list for FRC Team 8092 - 2026 Robot

## Current Configuration (Pre-Kickoff - January 2026)

**Robot Status**: Chassis operational, top mechanism removed
- **Chassis**: Fully operational mecanum drive system
- **Top Mechanism**: REMOVED (2025 lift system disassembled)
- **Vision System**: Limelight 3 configured and operational
- **Control System**: roboRIO 1.0 running current codebase

**Development Focus**: Code improvements and driver training until kickoff on January 10, 2026.

**Competition**: Avrasya Regional, Turkey (March 31 - April 2, 2026)

---

## Control System

### Main Controller
- **NI roboRIO 1.0** - Main robot controller running WPILib
  - Network Address: 10.80.92.4

### Power Distribution
- **CTRE Power Distribution Panel (PDP)** - Distributes power to robot components
- **CTRE Voltage Regulator Module (VRM)** - Provides regulated 12V and 5V power
- **OptiFuse 120A Main Breaker** - Main circuit protection

### Networking
- **Vivid-Hosting VH-109 FRC Radio** - Robot wireless communication
  - Network Address: 10.80.92.11
  - Team: 8092

### Human Interface
- **2x Logitech Controllers** - Driver and operator controllers
  - Primary driver: Port 0
  - Secondary operator: Port 1 (if used)
  - Used for drive control and button commands
  - Note: Code uses XboxController class which is compatible with most Logitech gamepads

## Drive System

### Motors
- **4x 2.5" CIM Motors** - Mecanum drive motors
  - Controlled by **4x VEX Pro Victor SPX Motor Controllers**
  - Front Left: PWM Port 3 (Inverted)
  - Rear Left: PWM Port 1 (Inverted)
  - Front Right: PWM Port 2 (Not inverted)
  - Rear Right: PWM Port 4 (Not inverted)

### Gearboxes
- **4x AndyMark Toughbox Mini Classic** - One per wheel
  - Gear Ratio: 12.75:1 reduction
  - Weight: ~1,097g (2.42 lbs) each
  - Construction: Nylon 6/6 housing with fiberglass fill
  - Gears: 20 DP, 14.5° pressure angle spur gears (4140 steel)
  - Compatible with 1 or 2 CIM/NEO motors per gearbox

### Sensors
- **navX-MXP AHRS Gyroscope**
  - Connection: MXP SPI port on roboRIO
  - Used for: Field-oriented drive, heading measurement
  - 9-axis IMU (gyro, accelerometer, magnetometer)

## Vision System

### Camera
- **Limelight 3**
  - Network Address: 10.80.92.200 (static IP)
  - Camera: OV5647 color rolling shutter (640x480 @ 90 FPS)
  - Field of View: 62.5° horizontal, 48.9° vertical
  - Illumination: 600 lumen green LEDs
  - Function: AprilTag detection and tracking
  - Pipeline: AprilTag detection for game piece alignment
  - Target Tags: 12, 15
  - Web Interface: http://10.80.92.200:5801

## LED System

### Indicators
- **Addressable LED Strip**
  - Port: PWM 9
  - Length: 300 LEDs
  - Type: WS2812B or similar addressable RGB LEDs
  - Status: Currently disabled in code (commented out)

## Port Assignments Summary

### PWM Ports
| Port | Device | Notes |
|------|--------|-------|
| 1 | Drive - Rear Left Motor | Inverted |
| 2 | Drive - Front Right Motor | Not inverted |
| 3 | Drive - Front Left Motor | Inverted |
| 4 | Drive - Rear Right Motor | Not inverted |
| 9 | Addressable LED Strip | Currently disabled |

### MXP/SPI Ports
| Port | Device | Notes |
|------|--------|-------|
| MXP SPI | NavX Gyroscope | Field orientation |

### Network Devices
| IP Address | Device | Notes |
|------------|--------|-------|
| 10.80.92.11 | VH-109 FRC Radio | Team 8092 wireless |
| 10.80.92.4 | roboRIO 1.0 | Main controller |
| 10.80.92.200 | Limelight 3 | Vision camera (static IP) |

### USB/Controller Ports
| Port | Device | Notes |
|------|--------|-------|
| 0 | Logitech Controller 1 | Primary driver controller |
| 1 | Logitech Controller 2 | Secondary operator controller (if configured) |

## Specifications

### Drive System
- **Drive Type**: Mecanum (omni-directional)
- **Motors**: 4x 2.5" CIM Motors (~5,330 RPM free speed)
- **Gearboxes**: 4x AndyMark Toughbox Mini Classic (12.75:1 ratio)
- **Output Speed**: ~418 RPM at wheels (5,330 RPM ÷ 12.75)
- **Motor Controllers**: 4x VEX Pro Victor SPX
- **Max Speed**: 70% (configurable in Constants.java)
- **Slew Rate**: 2.5 units/sec (X/Y), 2.0 units/sec (rotation)

### Vision System
- **Target Distance**: 1.3% camera area
- **Max Drive Speed**: 0.4 (reduced for vision)
- **Max Rotation Speed**: 0.25 (reduced for vision)
- **Rotation PID**: kP=0.03, kI=0.0, kD=0.006
- **Drive PID**: kP=0.1, kI=0.0, kD=0.005

## Vendor Dependencies

- **Studica**: NavX gyroscope support (Studica-2025.0.1.json)
- **WPILib**: Command-based framework (WPILibNewCommands.json)

## Controller Setup and Configuration

### Quick Switch Between Xbox and Logitech Controllers

The code now supports **two separate button mappings** for Xbox and Logitech controllers. To switch between them:

1. **Open `src/main/java/frc/robot/Constants.java`**
2. **Find line 27** (look for `ACTIVE_CONTROLLER`)
3. **Change the controller type:**
   ```java
   // For Xbox or Logitech in XInput mode:
   public static final ControllerType ACTIVE_CONTROLLER = ControllerType.XBOX;

   // For Logitech in DirectInput mode:
   public static final ControllerType ACTIVE_CONTROLLER = ControllerType.LOGITECH;
   ```
4. **Redeploy code** to the robot
5. **Done!** All button mappings automatically switch

### Changing Controller Port Assignment

To change which physical controller is used:

1. **In FRC Driver Station:**
   - Open the "USB Devices" tab
   - Drag controllers to reorder them (top = port 0, next = port 1, etc.)
   - Changes take effect immediately without redeploying code

2. **In Code (if needed):**
   - Edit `src/main/java/frc/robot/Constants.java`
   - Change `CONTROLLER_PORT` value in `OperatorConstants` class
   - Redeploy code to robot

### Button Mapping

Current button/axis mappings are defined in `Constants.java` under `OperatorConstants`:

**Axes:**
- Left Stick X (axis 0): Strafe left/right
- Left Stick Y (axis 1): Forward/backward
- Z-axis (axis 2): Rotation
- Right Trigger (axis 3): Available
- Right Stick X (axis 4): Available
- Right Stick Y (axis 5): Available

**Buttons:**
- X Button (3): AprilTag alignment (active binding)
- A Button (1): Available
- B Button (2): Available
- Y Button (4): Available
- Left/Right Bumpers (5/6): Available
- Start/Select (7/8): Available
- Stick Buttons (9/10): Available

### Testing Controllers

To verify controller functionality:
1. Open FRC Driver Station
2. Click "USB Devices" tab
3. Select your controller
4. Move sticks and press buttons to see values change
5. Verify axis numbers match the constants in code

### Logitech Controller Compatibility

The code supports both XInput and DirectInput Logitech controllers:

**XInput Mode (Logitech switch set to "X"):**
- Use `ControllerType.XBOX` in Constants.java
- Button mappings match Xbox controller
- Works seamlessly with XboxController class

**DirectInput Mode (Logitech switch set to "D"):**
- Use `ControllerType.LOGITECH` in Constants.java
- Custom button mappings defined in `LogitechMapping` class
- Adjust mappings in Constants.java:57-75 for your specific controller

### Calibrating Logitech Button Mappings

If your Logitech controller buttons don't match the defaults:

1. Open FRC Driver Station → USB Devices tab
2. Select your controller and press each button
3. Note the button numbers that appear
4. Edit `Constants.java` → `LogitechMapping` class (around line 56)
5. Update the button numbers to match your controller
6. Redeploy code

## Component Details

### roboRIO 1.0 Specifications
- First generation NI roboRIO
- 667 MHz dual-core ARM Cortex-A9 processor
- 256 MB RAM
- Runs NI Linux Real-Time OS

### 2.5" CIM Motor Specifications
- Type: Brushed DC motor
- Free Speed: ~5,330 RPM
- Stall Torque: ~2.41 N⋅m (343 oz⋅in)
- Stall Current: ~131 A
- Free Current: ~2.7 A
- Standard FRC motor for drive systems

### VEX Pro Victor SPX Motor Controller Specifications
- PWM-controlled motor controller
- Continuous Current: 100A
- Peak Current: 130A (2 seconds)
- Compatible with 12V brushed DC motors
- Built-in thermal protection
- LED status indicators (red/orange/green)
- Compact design for space-constrained applications
- Dimensions: 2.75" x 1.26" x 0.65"
- Operating Voltage: 6-12V

### AndyMark Toughbox Mini Classic Specifications
- Gear Ratio: 12.75:1 reduction
- Weight: 1,097g (2.42 lbs) per gearbox
- Housing Material: Nylon 6/6 with long fiberglass fill
- Gear Specifications: 20 DP, 14.5° pressure angle spur gears
- Gear Material: 4140 steel (standard)
- Motor Mounting: Supports 1 or 2 CIM or NEO motors
- Maintenance: 30-minute break-in period, periodic lubrication with red tacky grease recommended

### Limelight 3 Features
- OV5647 color rolling shutter camera
- 640x480 resolution at 90 FPS
- 62.5° horizontal FOV, 48.9° vertical FOV
- 600 lumen green illumination
- Hardware-accelerated AprilTag detection
- 10/100 Ethernet connectivity
- 5Gbps USB-A host port
- Web interface for configuration: http://10.80.92.200:5801
- NetworkTables integration for robot communication
- Power: 4.1V-16V input, 7W max consumption

### navX-MXP Capabilities
- 9-axis sensor fusion (gyro, accelerometer, compass)
- 200 Hz update rate
- Yaw/Pitch/Roll orientation
- Collision detection and motion processing

## Notes

- All motor inversions are critical for proper drive operation
- LED subsystem is present but currently disabled
- Network addresses are configured for Team 8092 (10.80.92.x)
- Limelight 3 web interface accessible at http://10.80.92.200:5801
- Limelight 3 documentation: https://docs.limelightvision.io/tr/docs/docs-limelight/getting-started/limelight-3
- Controller remapping in Driver Station is the easiest method and requires no code changes
