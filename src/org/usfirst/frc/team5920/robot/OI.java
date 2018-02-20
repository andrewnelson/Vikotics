/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5920.robot.subsystems.*;
import org.usfirst.frc.team5920.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */ 
public class OI { 
	//https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599739-running-commands-on-joystick-input for more examples.
	//public static XboxController DriverController;
	//public static XboxController OperatorController;
	
    
	public OI() {
		 //http://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/XboxController.html
		//https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599739-running-commands-on-joystick-input
		//https://wpilib.screenstepslive.com/s/3120/m/7952/l/97457-running-commands-on-joystick-input
		/*
	XboxController Driver = new XboxController(0);
	XboxController Operator = new XboxController(1);

		 Button DriverA = new JoystickButton(Driver, 1),
				DriverB = new JoystickButton(Driver, 2),
				DriverX = new JoystickButton(Driver, 3),
				DriverY = new JoystickButton(Driver, 4),
				DriverBack = new JoystickButton(Driver, 7),				
				DriverStart = new JoystickButton(Driver, 8)
				;
		  Button OperatorA = new JoystickButton(Operator, 1),
				OperatorB = new JoystickButton(Operator, 2),
				OperatorX = new JoystickButton(Operator, 3),
				OperatorY = new JoystickButton(Operator, 4),
				OperatorBack = new JoystickButton(Operator, 7),				
				OperatorStart = new JoystickButton(Operator, 8)
				;
		
		DriverA.whenPressed(new Mandible_Command(true, true));
		DriverB.whenPressed(new Mandible_Command(true, false));
		DriverX.whenPressed(new Mandible_Command(false, true));
		DriverY.whenPressed(new Mandible_Command(false, false));
		DriverBack.whenActive(new Mandible_Command(false, false));
		DriverStart.whenActive(new Mandible_Command(false, false));
		DriverBack.whenActive(new Cage_Command("Mandible", 1));
		DriverStart.whenActive(new Cage_Command("Ejector", 1));
		
		OperatorY.whenPressed(new Mandible_Command(1));
		OperatorB.whenPressed(new Mandible_Command(2));
		OperatorX.whenPressed(new Mandible_Command(3));
		*/
	}
	
	public static double OperatorLeftJoystick() {
		XboxController OperatorController = new XboxController(1);
		return OperatorController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}
	public static double OperatorRightJoystick() {
		XboxController OperatorController = new XboxController(1);
		return OperatorController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	
	public static double DriverLeftJoystick(){
		XboxController DriverController = new XboxController(0);
		return DriverController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}
	public static double DriverRightJoystick() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	
	public static boolean TurboSpeed() {
		XboxController DriverController = new XboxController(0);
		return DriverController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	public static boolean PercisionSpeed() {
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

	
}



