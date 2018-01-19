/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team5920.robot.subsystems.*;
import org.usfirst.frc.team5920.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI { 
	//https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599739-running-commands-on-joystick-input for more examples.
    public Joystick joystickLeft;
    public Joystick joystickRight;
    
	public OI() {
		Joystick joystickLeft = new Joystick(0);
		Joystick joystickRight = new Joystick(1);
	    SmartDashboard.putData("Auto_Command", new Auto_Command());
	    SmartDashboard.putData("Tankdrive_Command", new TankDrive_Command(0,0,0));
	    SmartDashboard.putData("Disabled_Command", new Disabled_Command());
	}
	
	public Joystick getDriverLeftJoystick(){
		return joystickLeft;
	}
	
	public Joystick getDriverRightJoystick(){
		return joystickRight;
	}
}
