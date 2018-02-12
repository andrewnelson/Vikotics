package org.usfirst.frc.team5920.robot.commands;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_LeftRoute extends Command {

    public Auto_LeftRoute() {
    	requires(Robot.driveTrain_Subsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	    	RobotMap.driveTrain_RightMotor.setSelectedSensorPosition(0, 0, 0);
	    	RobotMap.driveTrain_LeftMotor.setSelectedSensorPosition(0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    	//RobotMap.MainDrive.tankDrive(.5, .5,true);
	
	    	Robot.driveTrain_Subsystem.StartAutoLeft();
	    	//Timer.delay(15);
	    	//Robot.driveTrain_Subsystem.Stop();

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
