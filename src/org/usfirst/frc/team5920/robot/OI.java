/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team5920.robot.subsystems.*;
import org.usfirst.frc.team5920.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI { 
	//https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599739-running-commands-on-joystick-input for more examples.
	public XboxController DriveController;
    
	public OI() {
		//XboxController DriveController = new XboxController(0);

	/*    SmartDashboard.putData("Auto_Command", new Auto_Command());
	    SmartDashboard.putData("Tankdrive_Command", new TankDrive_Command(0,0,0));
	    SmartDashboard.putData("Disabled_Command", new Disabled_Command());*/
	}
	
	public static double LeftDrive(){
		XboxController DriveController = new XboxController(0);
		return DriveController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}
	public static double RightDrive() {
		XboxController DriveController = new XboxController(0);
		return DriveController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	
	public static boolean PercisionDrive() {
		XboxController DriveController = new XboxController(0);
		return DriveController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	/*
	
	public Joystick getDriverLeftJoystick(){
		return joystickLeft;
	}
	
	public Joystick getDriverRightJoystick(){
		return joystickRight;
	}*/
}



