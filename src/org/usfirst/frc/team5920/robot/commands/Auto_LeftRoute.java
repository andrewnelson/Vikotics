package org.usfirst.frc.team5920.robot.commands;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.subsystems.Mandible_Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_LeftRoute extends Command {
	
	double startTime=0;
	int driveStage=0;
	
    public Auto_LeftRoute() {
    	requires(Robot.driveTrain_Subsystem);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.CameraData.putNumber("ledMode", 0);
    	Robot.CameraData.putNumber("camMode", 0);
    	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.Gantry_PrimeMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.Cage_RightMotor.setSelectedSensorPosition(0, 0, 0);
    	
    	startTime = Timer.getFPGATimestamp();
	    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.useJoystick=false;
	    	if (RobotMap.SwitchLeft){
	    		//We have our switch
	    		switch (driveStage) {
	            case 0:  
		            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)<15000) {
		            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, -1 * RobotMap.standardSpeed);
		            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, -1 * RobotMap.standardSpeed);
		            	} else {
		            		driveStage = 1;
		                	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
		            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, -1 * RobotMap.slowSpeed);
		            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
		            	}
		            	break;
	            case 1:
		            	if (RobotMap.driveTrain_LeftMotor.getSelectedSensorPosition(0)>12000) {
		            		RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, -1 * RobotMap.slowSpeed);
		            	}
		            	if (liftGantry()) {
		            		driveStage = 2;
		            	}
		            	break;
	            case 2:
	            	if (RobotMap.driveTrain_LeftMotor.getSelectedSensorPosition(0)>5345) {
	            		if (ejectCube()) {
	            			driveStage=3;
	            		}
	            	}
	            	break;
	            case 3:
	            	if (RobotMap.Cage_RightMotor.getSelectedSensorPosition(0)>8000) {
	            		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, 0);
	            		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, 0);
	            	}
	            	break;
	            		
	    		}
	    		if(Timer.getFPGATimestamp()-startTime >5) {
	    	    	RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, 0);
	    	    	RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, 0);
	    		}
	    	}else {
	   		 //Our switch is the other side.
	        	if(Timer.getFPGATimestamp()-startTime <2) {
		    	    	RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, -0.5);
		    	    	RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, -0.5);
	        	} else {
		    	    	RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, 0);
		    	    	RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, 0);
	        	}
	    	}
    }
   
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.useJoystick=true;
    }

    private boolean ejectCube() {
    	if (RobotMap.Cage_RightMotor.getSelectedSensorPosition(0)<8000) {
    		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, 1);
    		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, 1);
    		return false;
    	} else {
    		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, 0);
    		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, 0);
    		return true;
    	}
}
private boolean liftGantry() {	
	if (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)<15000) {
		RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, 1);
		return false;
	}else {
		RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, RobotMap.GantryFeedForward);
		return true;
	}
}
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.useJoystick=true;
    }
}
