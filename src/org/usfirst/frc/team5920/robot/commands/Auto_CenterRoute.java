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

	MotionProfiler _mpLeft = new MotionProfiler(RobotMap.driveTrain_LeftMotor);
	MotionProfiler _mpRight = new MotionProfiler(RobotMap.driveTrain_LeftMotor);
	
    public Auto_CenterRoute() {
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
    	
    	
    	RobotMap.driveTrain_LeftMotor.configMotionProfileTrajectoryPeriod(10, RobotMap.kTimeoutMs); 
    	RobotMap.driveTrain_RightMotor.configMotionProfileTrajectoryPeriod(10, RobotMap.kTimeoutMs); 
    	RobotMap.driveTrain_LeftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
    	RobotMap.driveTrain_RightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
    	
    	/*SetValueMotionProfile setOutputL = _mpLeft.getSetValue();
    	SetValueMotionProfile setOutputR = _mpRight.getSetValue();
		_mpLeft.set(ControlMode.MotionProfile, setOutputL.value);
		_mpRight.set(ControlMode.MotionProfile, setOutputR.value);*/
		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.useJoystick=false;
    	_mpLeft.control();
    	_mpRight.control();
    	//RobotMap.driveTrain_LeftMotor.set(ControlMode.MotionMagic, 2700);
    	//RobotMap.driveTrain_RightMotor.set(ControlMode.MotionMagic, 2700);
    	_mpLeft.startMotionProfile();
    	_mpRight.startMotionProfile();
    	
    	if (RobotMap.SwitchLeft){
    		
    	}else {
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
