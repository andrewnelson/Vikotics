package org.usfirst.frc.team5920.robot.commands;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_RightRoute extends Command {

	double startTime=0;
    public Auto_RightRoute() {
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
    	
    	startTime = Timer.getFPGATimestamp();
	    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       	RobotMap.useJoystick=false;
     		
        	if (!RobotMap.SwitchLeft){
        		if((Timer.getFPGATimestamp()-startTime > 4.99) && (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>-15000) ) {
        	    	RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, 1);
        		}else if (Timer.getFPGATimestamp()-startTime>5 && (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)<-14500) ) {

            		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, 1);
        	    	RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, 1);
        		}else {
        			RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, RobotMap.GantryFeedForward);
        		}
        		
        		if(Timer.getFPGATimestamp()-startTime <2) {
        	    	RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, -0.5);
        	    	RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, -0.5);
            	}else if(Timer.getFPGATimestamp()-startTime <3) {
        	    	RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, 0);
        	    	RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, -1);
            	}else if(Timer.getFPGATimestamp()-startTime <5) {
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, 0);
        	    	RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, 0);

            	} else {
            		Robot.Mandible_Subsystem.innerMandibleExtend();
            		Robot.Mandible_Subsystem.outerMandibleExtend();
        	    	RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, 0);
        	    	RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, 0);
        	    	RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, 0);
        	    	RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, 0);
            	}
        	}else {
        		 //Our switch is the other side.
            	if(Timer.getFPGATimestamp()-startTime <2) {
        	    	RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, -0.5);
        	    	RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, -0.5);
            	}
            	else {
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

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.useJoystick=true;
    }
}
