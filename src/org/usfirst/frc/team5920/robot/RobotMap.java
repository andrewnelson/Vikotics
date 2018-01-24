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
//import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static SpeedController driveTrain_LeftMotor;
    public static SpeedController driveTrain_RightMotor;
    public static DifferentialDrive driveTrain_MainDrive;
    
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
        driveTrain_LeftMotor = new VictorSP(0);
        driveTrain_LeftMotor.setInverted(true);
        driveTrain_RightMotor = new VictorSP(1);
        driveTrain_RightMotor.setInverted(true);
        driveTrain_MainDrive = new DifferentialDrive(driveTrain_LeftMotor, driveTrain_RightMotor);
        driveTrain_MainDrive.setSafetyEnabled(true);
        driveTrain_MainDrive.setExpiration(0.1);
        driveTrain_MainDrive.setMaxOutput(1.0);
        
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
