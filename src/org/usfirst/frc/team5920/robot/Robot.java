/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5920.robot.subsystems.*;
import org.usfirst.frc.team5920.robot.commands.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	Command autonomousCommand;
	Command aCommand;
	
    SendableChooser<Command> autoChooser = new SendableChooser<>();
    
    public static OI oi;
    public static DriveTrain_Subsystem driveTrain_Subsystem;
    public static Gantry_Subsystem Gantry_Subsystem;
    public static Cage_Subsystem Cage_Subsystem;
    public static Mandible_Subsystem Mandible_Subsystem;
    public static Dashboard_Subsystem Dashboard_Subsystem;
   // public static Lighting_Subsystem Lighting;
    @SuppressWarnings("deprecation")
	public static NetworkTable CameraData;
    
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		
		/*autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Left Program", new Auto_LeftRoute());
		autoChooser.addObject("Center Program", new Auto_CenterRoute());
		autoChooser.addObject("Right Program", new Auto_RightRoute());
		SmartDashboard.putData("Auto mode Chooser", autoChooser);*/
		
		
		RobotMap.init();
		oi = new OI();

		SmartDashboard.putBoolean("Inner Mandible Open", false);
		SmartDashboard.putBoolean("Outer Mandible Open", false);
        CameraData =  NetworkTable.getTable("limelight");
		driveTrain_Subsystem = new DriveTrain_Subsystem();
		Mandible_Subsystem = new Mandible_Subsystem();
		Gantry_Subsystem = new Gantry_Subsystem();
		Cage_Subsystem = new Cage_Subsystem();
		Dashboard_Subsystem = new Dashboard_Subsystem();
	//	Lighting = new Lighting_Subsystem();
		//autonomousCommand = new Auto_Command();
		//SmartDashboard.putData(Scheduler.getInstance());
		//SmartDashboard.putData("gantry command", autonomousCommand);

	}
	
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		
		CameraData.putNumber("ledMode", 1);
		CameraData.putNumber("camMode", 1);
		String gameData;
		RobotMap.Auto_Choice="C";
		if (!RobotMap.Auto_Left.get()) {
			RobotMap.Auto_Choice="L";
		} 
		if (!RobotMap.Auto_Right.get()) {
			RobotMap.Auto_Choice="R";
		} 

		switch (RobotMap.Auto_Choice) {
        case "L":
        	autonomousCommand  = new Auto_LeftRoute();
        	SmartDashboard.putBoolean("Left Auto", true);
        	SmartDashboard.putBoolean("Right Auto", false);
        	SmartDashboard.putBoolean("Center Auto", false);
        	break;
        case "R":
        	autonomousCommand  = new Auto_RightRoute();
        	SmartDashboard.putBoolean("Left Auto", false);
        	SmartDashboard.putBoolean("Right Auto", true);
        	SmartDashboard.putBoolean("Center Auto", false);
        	break;
        case "C":
        	autonomousCommand  = new Auto_CenterRoute();
        	SmartDashboard.putBoolean("Left Auto", false);
        	SmartDashboard.putBoolean("Right Auto", false);
        	SmartDashboard.putBoolean("Center Auto", true);
        	break;
		}
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		DriverStation.Alliance color;
		color = DriverStation.getInstance().getAlliance();
		if(color == DriverStation.Alliance.Blue){
			SmartDashboard.putBoolean("Alliance", true);
		}else {
			SmartDashboard.putBoolean("Alliance", false);
		}
        if(gameData.length() > 0) {RobotMap.FieldLayout = gameData;}
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//RobotMap.useJoystick=false;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {RobotMap.FieldLayout = gameData;}
        if(RobotMap.FieldLayout.charAt(0) == 'L')
		  {
			RobotMap.SwitchLeft=true;
		  } else {
			RobotMap.SwitchLeft=false;
		  }

		RobotMap.initAuto();

		//autonomousCommand = autoChooser.getSelected();
		switch (RobotMap.Auto_Choice) {
        case "L":
        	autonomousCommand  = new Auto_LeftRoute();
        	SmartDashboard.putBoolean("Left Auto", true);
        	SmartDashboard.putBoolean("Right Auto", false);
        	SmartDashboard.putBoolean("Center Auto", false);
        	break;
        case "R":
        	autonomousCommand  = new Auto_RightRoute();
        	SmartDashboard.putBoolean("Left Auto", false);
        	SmartDashboard.putBoolean("Right Auto", true);
        	SmartDashboard.putBoolean("Center Auto", false);
        	break;
        case "C":
        	autonomousCommand  = new Auto_CenterRoute();
        	SmartDashboard.putBoolean("Left Auto", false);
        	SmartDashboard.putBoolean("Right Auto", false);
        	SmartDashboard.putBoolean("Center Auto", true);
        	break;
		}
		//autonomousCommand  = new Auto_LeftRoute();
		//autonomousCommand  = new Auto_CenterRoute();
		//autonomousCommand  = new Auto_RightRoute();
		if (autonomousCommand != null) autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		CameraData.putNumber("ledMode", 0);
		CameraData.putNumber("camMode", 0);
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	/*	RobotMap.useJoystick = true;*/
		RobotMap.initTele();
		RobotMap.useJoystick=true;		
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		/*if (DriverStation.getInstance().getMatchTime() < 30) {
			OI.DriverRumbleEnhanced(500,  .5,  true,  true);
		}*/

		CameraData.putNumber("ledMode", 1);
		CameraData.putNumber("camMode", 1);
		//This function is called periodically during operator control.
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		//This function is called periodically during test mode.
	}
}