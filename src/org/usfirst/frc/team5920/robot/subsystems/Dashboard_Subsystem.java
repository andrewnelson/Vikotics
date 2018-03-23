package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Dashboard_Subsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Dashboard_Subsystem() {
    	//currentPosition = RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void periodic(){
		//Limit Switches
    	//	SmartDashboard.putBoolean("Cube in Cage", !RobotMap.Cage_CubePresent.get());
		SmartDashboard.putBoolean("Gantry Bottom", !RobotMap.Gantry_BottomLimit.get());
		SmartDashboard.putBoolean("Gantry Top", !RobotMap.Gantry_TopLimit.get());
		
		//NavX
        //SmartDashboard.putNumber(   "IMU_CompassHeading",   ahrs.getCompassHeading());
        SmartDashboard.putNumber(   "IMU_FusedHeading",     DriveTrain_Subsystem.ahrs.getFusedHeading());
        SmartDashboard.putNumber(   "IMU_YawRateDPS",       DriveTrain_Subsystem.ahrs.getRate());
        SmartDashboard.putNumber(   "Velocity_X",           DriveTrain_Subsystem.ahrs.getVelocityX());
        SmartDashboard.putNumber(   "Velocity_Y",           DriveTrain_Subsystem.ahrs.getVelocityY());
        
        //Drivebase
		SmartDashboard.putNumber("Right Ticks", RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*-1);
		SmartDashboard.putNumber("Left Ticks", RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right RPM", RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
		SmartDashboard.putNumber("Left RPM", RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
		
		SmartDashboard.putNumber("Cage Left", RobotMap.Cage_LeftMotor.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Cage Right", RobotMap.Cage_RightMotor.getSelectedSensorPosition(0));
		SmartDashboard.putBoolean("Drive with Joystick", RobotMap.useJoystick);
		
		double robotSpeed = (RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0)+ (RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*-1))/2;
		robotSpeed = robotSpeed * 10; //Ticks per Sec
		SmartDashboard.putNumber("Robot FPS", Math.abs(robotSpeed / RobotMap.ticksPerInch/12));
		
		//Gantry
	    	SmartDashboard.putBoolean("Gantry in Motion", RobotMap.Gantry_In_Motion);
	    	SmartDashboard.putBoolean("G 00", (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)< 200));
	    	SmartDashboard.putBoolean("G 10", (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>RobotMap.GantryHeight[1]));
	    	SmartDashboard.putBoolean("G 20", (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>RobotMap.GantryHeight[2]));
	    	SmartDashboard.putBoolean("G Max", (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>RobotMap.GantryHeight[3]));
	    	SmartDashboard.putNumber("Gantry Position", RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0));
	    	
	    	//Mandible
	    SmartDashboard.putNumber("Mandible Run", RobotMap.Mandible_Left.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
	    
	    //Cage
	    SmartDashboard.putNumber("Cage Run", RobotMap.Cage_LeftMotor.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
	    SmartDashboard.putString("Field Layout", RobotMap.FieldLayout);
    }
}

