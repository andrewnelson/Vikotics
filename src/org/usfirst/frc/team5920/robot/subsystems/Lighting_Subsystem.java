package org.usfirst.frc.team5920.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.commands.LED_Command;
import edu.wpi.first.wpilibj.I2C;

/**
 *
 */
public class Lighting_Subsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LED_Command());
    }
    
	public void periodic() {
		//Read light mode
	}
	
	
	public void Start() {
		//MainDrive.tankDrive(.5, .5);
	}
	public void Stop() {
		//MainDrive.tankDrive(0, 0);
	}
}

