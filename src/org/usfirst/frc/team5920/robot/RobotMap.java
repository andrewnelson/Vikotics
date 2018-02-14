/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
    private static WPI_VictorSPX driveTrain_rearLeftMotor;
    private static WPI_VictorSPX driveTrain_rearRightMotor;
    //Drive module
 	public static DifferentialDrive driveTrain_MainDrive;
    //Pneumatics
 	public static Pneumatics_Subsystem Pneumatics;
 	public static Compressor airSupply;
    public static DoubleSolenoid innerMandibles;
    public static DoubleSolenoid outerMandibles;
    //Robot Info
    public static PowerDistributionPanel RobotPDP;
    //NavX
    
    //Gantry Lift
    public static WPI_TalonSRX Gantry_PrimeMotor;
    public static WPI_VictorSPX Gantry_SecondaryMotor;
    
    //Cage Motors
    public static WPI_TalonSRX Cage_LeftMotor;
    public static WPI_TalonSRX Cage_RightMotor;
    
    //Mandible Motors
    public static WPI_TalonSRX Mandible_Left;
    public static WPI_TalonSRX Mandible_Right;
    
    //Lighting
    
    //Robot run variables
    public static double encoderPerRev = 4096;
    public static double slowSpeed = encoderPerRev / 300 * 100;//last number is RPM, converts to encoder ticks
    public static double standardSpeed = encoderPerRev / 300 * 300;//Ditto
    public static double turboSpeed = encoderPerRev / 300 * 470;//Ditto
    
    public static double percisionspeed = .5;    
    public static double driveTrain_WheelDiameter = 6.0;
    public static double driveTrain_DistancePerRev = 2 * (driveTrain_WheelDiameter/2) * Math.PI;
    public static double ticksPerInch = encoderPerRev / driveTrain_DistancePerRev;

    //SRX constants
    public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
    
    public static void init() {
    	airSupply = new Compressor(3);
    	innerMandibles = new DoubleSolenoid(3, 1, 2);
    	outerMandibles = new DoubleSolenoid(3,3,4);
    	Pneumatics = new Pneumatics_Subsystem();
    	RobotPDP = new PowerDistributionPanel();
    	
    	driveTrain_LeftMotor = new WPI_TalonSRX(1);
    	driveTrain_rearLeftMotor = new WPI_VictorSPX(11);
    	driveTrain_RightMotor = new WPI_TalonSRX(2);
    	driveTrain_rearRightMotor = new WPI_VictorSPX(12);
    Gantry_PrimeMotor = new WPI_TalonSRX(21);
    Gantry_SecondaryMotor = new WPI_VictorSPX(22);
    Cage_LeftMotor = new WPI_TalonSRX(31);
    Cage_RightMotor = new WPI_TalonSRX(32);
    Mandible_Left = new WPI_TalonSRX(33);
    Mandible_Right = new WPI_TalonSRX(34);
    
    Gantry_PrimeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    Gantry_PrimeMotor.setSensorPhase(true);
    Gantry_SecondaryMotor.follow((WPI_TalonSRX)Gantry_PrimeMotor);
    Gantry_PrimeMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    Gantry_SecondaryMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    
    Cage_LeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    Cage_LeftMotor.setSensorPhase(true);
    Cage_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    
    Cage_RightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    Cage_RightMotor.setSensorPhase(true);
    Cage_RightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    
    Mandible_Left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    Mandible_Left.setSensorPhase(true);
    Mandible_Left.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    
    Mandible_Right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    Mandible_Right.setSensorPhase(true);
    Mandible_Right.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    
    driveTrain_rearLeftMotor.follow((WPI_TalonSRX)driveTrain_LeftMotor);
    	driveTrain_LeftMotor.setInverted(true);
    	driveTrain_rearLeftMotor.setInverted(true);
    	driveTrain_LeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	driveTrain_rearLeftMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	
    	
    	driveTrain_rearRightMotor.follow((WPI_TalonSRX)driveTrain_RightMotor);
    	driveTrain_RightMotor.setInverted(false);
    	driveTrain_rearRightMotor.setInverted(false);
    	driveTrain_RightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	driveTrain_rearRightMotor.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    	
    driveTrain_MainDrive = new DifferentialDrive(driveTrain_LeftMotor, driveTrain_RightMotor);
    driveTrain_MainDrive.setSafetyEnabled(true);
    driveTrain_MainDrive.setExpiration(0.1);
    driveTrain_MainDrive.setMaxOutput(1.0);
    
    driveTrain_LeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    driveTrain_LeftMotor.setSensorPhase(true);
    driveTrain_RightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
    driveTrain_RightMotor.setSensorPhase(true);

	/* set the peak, nominal outputs */
    driveTrain_LeftMotor.configNominalOutputForward(0, kTimeoutMs);
	driveTrain_LeftMotor.configNominalOutputReverse(0, kTimeoutMs);
	driveTrain_LeftMotor.configPeakOutputForward(1, kTimeoutMs);
	driveTrain_LeftMotor.configPeakOutputReverse(-1, kTimeoutMs);

	/* set closed loop gains in slot0 */
	driveTrain_LeftMotor.config_kF(kPIDLoopIdx, 0.1097, kTimeoutMs);
	driveTrain_LeftMotor.config_kP(kPIDLoopIdx, 0.113333, kTimeoutMs);
	driveTrain_LeftMotor.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
	driveTrain_LeftMotor.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
	
	/* set the peak, nominal outputs */
	driveTrain_RightMotor.configNominalOutputForward(0, kTimeoutMs);
	driveTrain_RightMotor.configNominalOutputReverse(0, kTimeoutMs);
	driveTrain_RightMotor.configPeakOutputForward(1, kTimeoutMs);
	driveTrain_RightMotor.configPeakOutputReverse(-1, kTimeoutMs);

	/* set closed loop gains in slot0 */
	driveTrain_RightMotor.config_kF(kPIDLoopIdx, 0.1097, kTimeoutMs);
	driveTrain_RightMotor.config_kP(kPIDLoopIdx, 0.113333, kTimeoutMs);
	driveTrain_RightMotor.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
	driveTrain_RightMotor.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
         
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
