// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AddressableLEDSubsytem extends SubsystemBase {
  /** Creates a new AddrasableLedSubsytem. */
  AddressableLED led;
  AddressableLEDBuffer buffer;
    
  public AddressableLEDSubsytem(int pwmPort, int length) {
    buffer = new AddressableLEDBuffer(length);
    led = new AddressableLED(pwmPort);
    led.setLength(length);
    led.start();
  }

  int startIndex = 0;
  boolean waveState = true;
  public void wave(int r, int g, int b){
      if(startIndex >= buffer.getLength()){
        waveState = !waveState;
        startIndex = 0;
      }

      if(waveState){
        buffer.setRGB(startIndex, r, g, b);
        startIndex++;
        
      } else {
        buffer.setRGB(startIndex, 0, 0, 0);
        startIndex++;
      }
      
      led.setData(buffer);
  }

  public void setLEDColor(int r, int g, int b, int index){
    buffer.setRGB(index, r, g, b);
    led.setData(buffer);
  }
  public void setLEDColor(int r, int g, int b){
    for(int i = 0; i < buffer.getLength(); i++){
      buffer.setRGB(i, r, g, b);
    }
    led.setData(buffer);
  }
  public void clearLEDs(){
    for(int i = 0; i < buffer.getLength(); i++){
      buffer.setRGB(i, 0, 0, 0);
    }
    led.setData(buffer);
  }
  public void stop(){
    led.stop();
  }
  public void start(){
    led.start();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
