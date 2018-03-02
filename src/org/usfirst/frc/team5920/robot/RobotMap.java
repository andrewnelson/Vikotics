/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.NeutralMode;


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
    private static WPI_VictorSPX driveTrain_rearLeftMotor;
    private static WPI_VictorSPX driveTrain_rearRightMotor;
    //Drive module
 	public static DifferentialDrive driveTrain_MainDrive;
    //Pneumatics
 	public static Compressor airSupply;
    public static DoubleSolenoid innerMandibles;
    public static DoubleSolenoid outerMandibles;
    //Robot Info
    //public static PowerDistributionPanel RobotPDP;
    //NavX
    
    //Gantry Lift
    public static WPI_TalonSRX Gantry_PrimeMotor;
    public static WPI_VictorSPX Gantry_SecondaryMotor;
    
    public static DigitalInput Gantry_TopLimit;
    public static DigitalInput Gantry_BottomLimit;
    
    //Cage Motors
    public static WPI_TalonSRX Cage_LeftMotor;
    public static WPI_TalonSRX Cage_RightMotor;
    
    public static DigitalInput Cage_CubePresent;
    
    //Mandible Motors
    public static WPI_TalonSRX Mandible_Left;
    public static WPI_TalonSRX Mandible_Right;
    
    //Lighting
    
    //Robot run variables
    public static int robotDirection = 1;
    public static double encoderPerRev = 4096;
    public static double slowSpeed = encoderPerRev / 300 * 100;//last number is RPM, converts to encoder ticks
    public static double standardSpeed = encoderPerRev / 300 * 300;//Ditto
    public static double turboSpeed = encoderPerRev / 300 * 470;//Ditto
    
    public static double GantrySpeed = encoderPerRev / 300 * 470;//Ditto
   // public static double CageSpeed = encoderPerRev / 300 * 470;//Ditto
    //public static double MandibleSpeed = encoderPerRev / 300 * 470;//Ditto
    public static double GantryTicksPerInch = 4096;
    
    public static double percisionspeed = .5;    
    public static double driveTrain_WheelDiameter = 6.0;
    public static double driveTrain_DistancePerRev = 2 * (driveTrain_WheelDiameter/2) * Math.PI;
    public static double ticksPerInch = encoderPerRev / driveTrain_DistancePerRev;

    public static double MandibleSpeed = .50;
    public static double CageSpeed = .50;
    
    //Gantry Heights
    public static int[] GantryHeight = {0, 5000, 20000, 40000};
    
    
    //SRX constants
    public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
    
    public static void init() {
    	Gantry_TopLimit = new DigitalInput(0);
    	Gantry_BottomLimit = new DigitalInput(1);
    	Cage_CubePresent = new DigitalInput(2);
    	
    	airSupply = new Compressor(3);
    	innerMandibles = new DoubleSolenoid(3, 0, 1);
    	outerMandibles = new DoubleSolenoid(3,2,3);
    	//sRobotPDP = new PowerDistributionPanel();
    	
    	driveTrain_LeftMotor = new WPI_TalonSRX(1);
    	driveTrain_rearLeftMotor = new WPI_VictorSPX(11);
    	driveTrain_RightMotor = new WPI_TalonSRX(2);
    	driveTrain_rearRightMotor = new WPI_VictorSPX(12);
	    Gantry_PrimeMotor = new WPI_TalonSRX(5);
	    Gantry_SecondaryMotor = new WPI_VictorSPX(51);
	    Cage_LeftMotor = new WPI_TalonSRX(53);
	    Cage_RightMotor = new WPI_TalonSRX(56);
	    Mandible_Left = new WPI_TalonSRX(54);
	    Mandible_Right = new WPI_TalonSRX(55);
	    
	    Gantry_PrimeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
	    Gantry_PrimeMotor.setSensorPhase(true);
	    Gantry_SecondaryMotor.follow((WPI_TalonSRX)Gantry_PrimeMotor);
	    Gantry_PrimeMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
	    Gantry_SecondaryMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    
	    SetupDriveMotor(driveTrain_LeftMotor, NeutralMode.Coast, true);
	    SetupDriveMotor(driveTrain_RightMotor, NeutralMode.Coast, false);
	    SetupSlaveMotor(driveTrain_rearLeftMotor, driveTrain_LeftMotor, NeutralMode.Coast, true);
	    SetupSlaveMotor(driveTrain_rearRightMotor, driveTrain_RightMotor, NeutralMode.Coast, false);
	    	  
	    SetupCurrentMotor(Mandible_Right, false);
		SetupCurrentMotor(Mandible_Left, true);
		
		SetupCurrentMotor(Cage_LeftMotor, true);
		SetupCurrentMotor(Cage_RightMotor, true);
		SetupCurrentMotor(Gantry_PrimeMotor, true);
		SetupSlaveMotor(Gantry_SecondaryMotor, Gantry_PrimeMotor, NeutralMode.Coast, true);
    }
    
   
    
 /*   private static void SetupMotorControl(WPI_TalonSRX controller) {
    	controller.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    	controller.setSensorPhase(true);
    	controller.setInverted(true);
    	controller.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	    
    	// set the peak, nominal outputs 
    	controller.configNominalOutputForward(0, kTimeoutMs);
    	controller.configNominalOutputReverse(0, kTimeoutMs);
    	controller.configPeakOutputForward(1, kTimeoutMs);
    	controller.configPeakOutputReverse(-1, kTimeoutMs);

    	// set closed loop gains in slot0 
    	controller.config_kF(kPIDLoopIdx, 0.1097, kTimeoutMs);
    	controller.config_kP(kPIDLoopIdx, 0.113333, kTimeoutMs);
    	controller.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
    	controller.config_kD(kPIDLoopIdx, 0, kTimeoutMs);

	
		// set the peak, nominal outputs 
		Gantry_PrimeMotor.configNominalOutputForward(0, kTimeoutMs);
		Gantry_PrimeMotor.configNominalOutputReverse(0, kTimeoutMs);
		Gantry_PrimeMotor.configPeakOutputForward(1, kTimeoutMs);
		Gantry_PrimeMotor.configPeakOutputReverse(-1, kTimeoutMs);
	
		// set closed loop gains in slot0 
		Gantry_PrimeMotor.config_kF(kPIDLoopIdx, 0.1097, kTimeoutMs);
		Gantry_PrimeMotor.config_kP(kPIDLoopIdx, 0.113333, kTimeoutMs);
		Gantry_PrimeMotor.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
		Gantry_PrimeMotor.config_kD(kPIDLoopIdx, 0, kTimeoutMs);      
    }*/
    
    private static void SetupCurrentMotor(WPI_TalonSRX _tal, boolean inverted) {
    	_tal.setInverted(inverted);
		boolean _currentLimEn = true;
		final int kPeakCurrentAmps = 15; // threshold to trigger current limit 
		final int kPeakTimeMs = 0; // how long after Peak current to trigger current limit 
		final int kContinCurrentAmps = 10; // hold current after limit is triggered 

		_tal.configPeakCurrentLimit(kPeakCurrentAmps, 10);
		_tal.configPeakCurrentDuration(kPeakTimeMs, 10); // this is a necessary call to avoid errata. 
		_tal.configContinuousCurrentLimit(kContinCurrentAmps, 10);
		_tal.enableCurrentLimit(_currentLimEn); // honor initial setting 

		// setup a basic closed loop 
		_tal.setNeutralMode(NeutralMode.Brake);
		_tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		_tal.setSensorPhase(true); // flip until sensor is in phase, or closed-loop will not work 
		_tal.config_kP(0, 2.0, 10);
		_tal.config_kI(0, 0.0, 10);
		_tal.config_kD(0, 0.0, 10);
		_tal.config_kF(0, 0.0, 10);
    }
    
    private static void SetupDriveMotor(WPI_TalonSRX _tal, com.ctre.phoenix.motorcontrol.NeutralMode BrakeMode, boolean inverted) {
	    _tal.setInverted(inverted);
    	_tal.setNeutralMode(BrakeMode);
    	// set the peak, nominal outputs 
    	_tal.configNominalOutputForward(0, kTimeoutMs);
    	_tal.configNominalOutputReverse(0, kTimeoutMs);
    	_tal.configPeakOutputForward(1, kTimeoutMs);
    	_tal.configPeakOutputReverse(-1, kTimeoutMs);

    	// set closed loop gains in slot0 
    	_tal.config_kF(kPIDLoopIdx, 0.1097, kTimeoutMs);
    	_tal.config_kP(kPIDLoopIdx, 0.113333, kTimeoutMs);
    	_tal.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
    	_tal.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
    }
    private static void SetupSlaveMotor(WPI_VictorSPX _vic, WPI_TalonSRX _tal,com.ctre.phoenix.motorcontrol.NeutralMode BrakeMode, boolean inverted) {
    	_vic.setInverted(inverted);
    	_vic.setNeutralMode(BrakeMode);
    	_vic.follow((WPI_TalonSRX)_tal);
	    	
    }
    
    public static void initAuto() {
    	driveTrain_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	driveTrain_rearLeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	driveTrain_RightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	driveTrain_rearRightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	
    	
    	//driveTrain_LeftMotor.set(ControlMode.Position, .8);
    	//driveTrain_LeftMotor.changeControlMode(ControlMode.Position); //Change control mode of talon, default is PercentVbus (-1.0 to 1.0)
    	//driveTrain_LeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	driveTrain_LeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
	    driveTrain_LeftMotor.setSensorPhase(true);
	    driveTrain_RightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
	    driveTrain_RightMotor.setSensorPhase(true);
    	//driveTrain_LeftMotor.ConfigSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
    	//driveTrain_LeftMotor.setPID(0.5, 0.001, 0.0); //Set the PID constants (p, i, d)

    
    	//driveTrain_LeftMotor.enableControl(); //Enable PID control on the talon

    }
    public static void initTele() {
    	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
    	driveTrain_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	driveTrain_rearLeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	driveTrain_RightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	driveTrain_rearRightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    	
    }
}
