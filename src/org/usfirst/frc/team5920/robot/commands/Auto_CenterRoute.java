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

	//MotionProfiler _mpLeft = new MotionProfiler(RobotMap.driveTrain_LeftMotor);
	//MotionProfiler _mpRight = new MotionProfiler(RobotMap.driveTrain_LeftMotor);
	
	double startTime=0;
	int driveStage=0;
	
    public Auto_CenterRoute() {
    	requires(Robot.driveTrain_Subsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//startTime = Timer.getFPGATimestamp();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.CameraData.putNumber("ledMode", 0);
    	Robot.CameraData.putNumber("camMode", 0);
    	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.Gantry_PrimeMotor.setSelectedSensorPosition(0, 0, 0);
    	
    	//startTime = Timer.getFPGATimestamp();
    	/*
    	RobotMap.driveTrain_LeftMotor.configMotionProfileTrajectoryPeriod(10, RobotMap.kTimeoutMs); 
    	RobotMap.driveTrain_RightMotor.configMotionProfileTrajectoryPeriod(10, RobotMap.kTimeoutMs); 
    	RobotMap.driveTrain_LeftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
    	RobotMap.driveTrain_RightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
    	*/
    	
    	/*SetValueMotionProfile setOutputL = _mpLeft.getSetValue();
    	SetValueMotionProfile setOutputR = _mpRight.getSetValue();
		_mpLeft.set(ControlMode.MotionProfile, setOutputL.value);
		_mpRight.set(ControlMode.MotionProfile, setOutputR.value);*/
		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.useJoystick=false;

    	if (RobotMap.SwitchLeft){
    		switch (driveStage) {
            case 0:  
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)<2001) {
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, RobotMap.standardSpeed);
            	} else {
            		driveStage = 1;
                	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
                	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, RobotMap.standardSpeed);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, RobotMap.standardSpeed);
            	}
            	break;
            case 1:
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)>10000) {
            		RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, 1);
            	}
            	if (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>15000) {
            		RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, 0);
            	}
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)>32160) {
            		driveStage = 2;
                	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
                	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, RobotMap.standardSpeed);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
            	}
            	break;
            case 2:
            	if (RobotMap.driveTrain_LeftMotor.getSelectedSensorPosition(0)<2001) {
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, 0);
            	} 
            	break;
    		}
    	}else {
    		switch (driveStage) {
            case 0:  
            	if (RobotMap.driveTrain_LeftMotor.getSelectedSensorPosition(0)<2001) {
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, RobotMap.standardSpeed);
            	} else {
            		driveStage = 1;
                	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
                	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, RobotMap.standardSpeed);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, RobotMap.standardSpeed);
            	}
            	break;
            case 1:
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)>10000) {
            		RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, 1);
            	}
            	if (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>15000) {
            		RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, 0);
            	}
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)>32160) {
            		driveStage = 2;
                	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
                	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
            		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, RobotMap.standardSpeed);
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
            	}
            	break;
            case 2:
            	if (RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)<2001) {
            		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, 0);
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

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.useJoystick=true;
    }
}
