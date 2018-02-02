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
	private XboxController DriverController;
    
    
	public OI() {
		 
		//XboxController DriveController = new XboxController(0);

	/*    SmartDashboard.putData("Auto_Command", new Auto_Command());
	    SmartDashboard.putData("Tankdrive_Command", new TankDrive_Command(0,0,0));
	    SmartDashboard.putData("Disabled_Command", new Disabled_Command());*/
	}
	
	public static double DriverLeftJoystick(){
		XboxController DriverController = new XboxController(0);
		return DriverController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}
	public static double DriverRightJoystick() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	
	public static boolean DriverPercisionDrive() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	public static boolean autotest() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}
	
	public static boolean DriverX() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getXButton();
	}
	public static boolean DriverY() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getYButton();
	}
	public static boolean DriverA() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getAButton();
	}
	public static boolean DriverB() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getBButton();
	}
	/*
	
	public Joystick getDriverLeftJoystick(){
		return joystickLeft;
	}
	
	public Joystick getDriverRightJoystick(){
		return joystickRight;
	}*/
}



