/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team5920.robot.subsystems.Pneumatics_Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

//import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Motor Controllers
    public static WPI_TalonSRX driveTrain_LeftMotor;
    public static WPI_TalonSRX driveTrain_RightMotor;    
    private static WPI_VictorSPX _rearLeftMotor;
    private static WPI_VictorSPX _rearRightMotor;
    //Drive module
 	public static DifferentialDrive driveTrain_MainDrive;
    //Pneumatics
 	public static Pneumatics_Subsystem Pneumatics;
 	public static Compressor airSupply;
    public static DoubleSolenoid intakeArms;
    //Robot Info
    public static PowerDistributionPanel RobotPDP;
    //NavX
    
    //Lighting
    
    //Robot run variables
    public static double percisionspeed = .5;
    public static double encoderPerRev = 4096;    
    public static double driveTrain_WheelDiameter = 6.0;
    public static double driveTrain_DistancePerRev = 2 * (driveTrain_WheelDiameter/2) * Math.PI;
    public static double ticksPerInch = encoderPerRev / driveTrain_DistancePerRev;

    public static void init() {
    /*	airSupply = new Compressor(3);
    	intakeArms = new DoubleSolenoid(3, 1, 2);*/
    	Pneumatics = new Pneumatics_Subsystem();
    	
    	RobotPDP = new PowerDistributionPanel();
    	
    	driveTrain_LeftMotor = new WPI_TalonSRX(1);
    	_rearLeftMotor = new WPI_VictorSPX(11);
    	_rearLeftMotor.follow((WPI_TalonSRX)driveTrain_LeftMotor);
    	driveTrain_LeftMotor.setInverted(false);
    	_rearLeftMotor.setInverted(false);
    	driveTrain_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	_rearLeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	
    	driveTrain_RightMotor = new WPI_TalonSRX(2);
    	_rearRightMotor = new WPI_VictorSPX(12);
    	_rearRightMotor.follow((WPI_TalonSRX)driveTrain_RightMotor);
    	driveTrain_RightMotor.setInverted(false);
    	_rearRightMotor.setInverted(false);
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
    	
    	
    	//driveTrain_LeftMotor.set(ControlMode.Position, .8);
    	//driveTrain_LeftMotor.changeControlMode(ControlMode.Position); //Change control mode of talon, default is PercentVbus (-1.0 to 1.0)
    	driveTrain_LeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	//driveTrain_LeftMotor.ConfigSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
    	//driveTrain_LeftMotor.setPID(0.5, 0.001, 0.0); //Set the PID constants (p, i, d)

    	int kSlotIdx=0;
    	int kTimeoutMs=100;   	
		driveTrain_LeftMotor.selectProfileSlot(kSlotIdx, 0);
		driveTrain_LeftMotor.config_kF(kSlotIdx, 0.2, kTimeoutMs);
    	driveTrain_LeftMotor.config_kP(kSlotIdx, 0.2, kTimeoutMs);
    	driveTrain_LeftMotor.config_kI(kSlotIdx, 0, kTimeoutMs);
    	driveTrain_LeftMotor.config_kD(kSlotIdx, 0, kTimeoutMs);
    	driveTrain_LeftMotor.config_IntegralZone(0, 100, kTimeoutMs);
    
    	//driveTrain_LeftMotor.enableControl(); //Enable PID control on the talon


    	
    }
    public static void initTele() {
    	driveTrain_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	_rearLeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	driveTrain_RightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	_rearRightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	
    	
    }
}
