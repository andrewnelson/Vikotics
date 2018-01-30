/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5920.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.usfirst.frc.team5920.robot.subsystems.DriveTrain_Subsystem;
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
   // SendableChooser<Command> autoChooser;//<Command> chooser = new SendableChooser<>();
    
    public static OI oi;
    public static DriveTrain_Subsystem driveTrain_Subsystem;
    public static Gantry_Subsystem gantry_Subsystem;
    public static Cage_Subsystem cage_Subsystem;
    public static NetworkTable CameraData;
    
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	//	autonomousCommand = new Auto_LeftRoute();
		//CommandBase.init();
		
		/*autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Left Program", new Auto_Command());
		autoChooser.addObject("Center Program", new Auto_LeftRoute());
		autoChooser.addObject("Right Program", new Auto_LeftRoute());
		SmartDashboard.putData("Auto mode Chooser", autoChooser);*/
		
		RobotMap.init();
		oi = new OI();
		driveTrain_Subsystem = new DriveTrain_Subsystem();
       // SmartDashboard.putData(driveTrain_Subsystem);
     //   gantry_Subsystem = new Gantry_Subsystem();
       // cage_Subsystem = new Cage_Subsystem();
        
         CameraData =  NetworkTable.getTable("limelight");
        
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
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
		RobotMap.initAuto();
		//autonomousCommand = (Command) autoChooser.getSelected();
		//autonomousCommand.start();

		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		RobotMap.initTele();
		CameraData.putNumber("ledMode", 1);
		CameraData.putNumber("camMode", 1);
				
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {

		Scheduler.getInstance().run();
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

