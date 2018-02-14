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
    SendableChooser autoChooser;//<Command> chooser = new SendableChooser<>();
    
    public static OI oi;
    public static DriveTrain_Subsystem driveTrain_Subsystem;
    public static Gantry_Subsystem Gantry_Subsystem;
    public static Cage_Subsystem Cage_Subsystem;
    public static Pneumatics_Subsystem Pneumatics_Subsystem;
    @SuppressWarnings("deprecation")
	public static NetworkTable CameraData;
    
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//CommandBase.init();
		
		/*autoChooser = new SendableChooser();
		autoChooser.addDefault("Left Program", new Auto_Command());
		autoChooser.addObject("Center Program", new Auto_LeftRoute());
		autoChooser.addObject("Right Program", new Auto_LeftRoute());
		SmartDashboard.putData("Auto mode Chooser", autoChooser);*/
		
		RobotMap.init();
		oi = new OI();

		SmartDashboard.putBoolean("Inner Mandible Open", false);
		SmartDashboard.putBoolean("Outer Mandible Open", false);
        CameraData =  NetworkTable.getTable("limelight");
		driveTrain_Subsystem = new DriveTrain_Subsystem();
		Pneumatics_Subsystem = new Pneumatics_Subsystem();
		Gantry_Subsystem = new Gantry_Subsystem();
		Cage_Subsystem = new Cage_Subsystem();
		
       // SmartDashboard.putData(driveTrain_Subsystem);
        
        
	}

	@Override
	public void disabledInit() {
		CameraData.putNumber("ledMode", 1);
		CameraData.putNumber("camMode", 1);
	}

	@Override
	public void disabledPeriodic() {
		
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
		//autonomousCommand = new Auto_Command();
		//RobotMap.initAuto();
		autonomousCommand = (Command) autoChooser.getSelected();

		if (autonomousCommand != null) autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		RobotMap.initTele();
		CameraData.putNumber("ledMode", 1);
		CameraData.putNumber("camMode", 1);
				
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		//This function is called periodically during operator control.
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		//This function is called periodically during test mode.
	}
}