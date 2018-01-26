/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.*;

//import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static WPI_TalonSRX driveTrain_LeftMotor;
    private static WPI_TalonSRX _rearLeftMotor;
    
    public static WPI_TalonSRX driveTrain_RightMotor;
    private static WPI_TalonSRX _rearRightMotor;
    public static DifferentialDrive driveTrain_MainDrive;
    
    //Robot run variables
    public static double percisionspeed = .5;
    
    
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static SpeedController leftDrive;
    public static SpeedController rightDrive;
    public static DifferentialDrive MainDrive;


    public static void init() {
    	driveTrain_LeftMotor = new WPI_TalonSRX(1);
    	_rearLeftMotor = new WPI_TalonSRX(11);
    	_rearLeftMotor.follow((WPI_TalonSRX)driveTrain_LeftMotor);
    	driveTrain_LeftMotor.setInverted(true);
    	_rearLeftMotor.setInverted(true);
    	driveTrain_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	_rearLeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	
    	driveTrain_RightMotor = new WPI_TalonSRX(2);
    	_rearRightMotor = new WPI_TalonSRX(12);
    	_rearRightMotor.follow((WPI_TalonSRX)driveTrain_RightMotor);
    	driveTrain_RightMotor.setInverted(true);
    	_rearRightMotor.setInverted(true);
    	driveTrain_RightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	_rearRightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	
        driveTrain_MainDrive = new DifferentialDrive(driveTrain_LeftMotor, driveTrain_RightMotor);
        driveTrain_MainDrive.setSafetyEnabled(true);
        driveTrain_MainDrive.setExpiration(0.1);
        driveTrain_MainDrive.setMaxOutput(1.0);
         
    }
    public static void initAuto() {
    	driveTrain_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	_rearLeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	driveTrain_RightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	_rearRightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	
    }
    public static void initTele() {
    	driveTrain_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	_rearLeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	driveTrain_RightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	_rearRightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	
    }
}

/*
Source / Reference files
NavX
https://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/

Talon SRX
https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/README.md

Anything else we are adding this year???


*/
