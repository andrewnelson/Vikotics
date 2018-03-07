package org.usfirst.frc.team5920.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 *
 */
public class Auto_Command extends Command {

    public Auto_Command() {
    		requires(Robot.driveTrain_Subsystem);
    		requires(Robot.Mandible_Subsystem);
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.driveTrain_Subsystem.StartAutoLeft();
    	//RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, .25);
    	RobotMap.Gantry_In_Motion = true;
    	RobotMap.Gantry_PrimeMotor.set(ControlMode.MotionMagic, 10000);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {

    	RobotMap.Gantry_PrimeMotor.set(ControlMode.MotionMagic, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.Gantry_PrimeMotor.set(ControlMode.MotionMagic, 0);
    }
}
