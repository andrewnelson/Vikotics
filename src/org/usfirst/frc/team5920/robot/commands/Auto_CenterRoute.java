package org.usfirst.frc.team5920.robot.commands;

import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class Auto_CenterRoute extends Command {

	int driveStage=0;
	
    public Auto_CenterRoute() {
    	requires(Robot.driveTrain_Subsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.CameraData.putNumber("ledMode", 0);
    	Robot.CameraData.putNumber("camMode", 0);
    	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.Gantry_PrimeMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.Cage_RightMotor.setSelectedSensorPosition(0, 0, 0);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.useJoystick=false;

    	if (RobotMap.SwitchLeft){
    		// Go Left
    		switch (driveStage) {
            case 0:  
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)<1950) {
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, -1 * RobotMap.standardSpeed);
            	} else {
            		driveStage = 1;
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
            	
                	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
                	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, -1 * RobotMap.standardSpeed);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, -1 * RobotMap.standardSpeed);
            	}
            	break;
            case 1:
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)>13500) {
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, 0);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
            		driveStage = 2;
            	}

            	//RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, 1);
            	break;
            case 2:
            	if (liftGantry()) {
            		driveStage = 3;
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, -0.15);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, -0.15);
            	}
            	break;
            case 3:
            	if (ejectCube()) {
        			RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, 0);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, 0);	
        		}
            	break;
    		}
    	}else {
    		//Go Right
    		switch (driveStage) {
            case 0:  
            	if (RobotMap.driveTrain_LeftMotor.getSelectedSensorPosition(0)<1750) {
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, -1 * RobotMap.standardSpeed);
            	} else {
            		driveStage = 1;
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
            	
                	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
                	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, -1 * RobotMap.standardSpeed);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, -1 * RobotMap.standardSpeed);
            	}
            	break;
            case 1:
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)>13500) {
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, 0);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
            		driveStage = 2;
            	}

            	//RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, 1);
            	break;
            case 2:
            	if (liftGantry()) {
            		driveStage = 3;
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, -0.15);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, -0.15);
            	}
            	break;
            case 3:
            		if (ejectCube()) {
            			RobotMap.driveTrain_LeftMotor.set(ControlMode.PercentOutput, 0);
                		RobotMap.driveTrain_RightMotor.set(ControlMode.PercentOutput, 0);	
            		}
            	break;
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
    		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, -1 * RobotMap.slowSpeed);
    		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, -1 * RobotMap.slowSpeed);
    		return false;
    	}else {
    		RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, RobotMap.GantryFeedForward);

    		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
    		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, 0);
    		return true;
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.useJoystick=true;
    }
}
