package org.usfirst.frc.team5920.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5920.robot.Robot; 

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 *
 */
public class Auto_Command extends Command {

    public Auto_Command() {
    		requires(Robot.driveTrain_Subsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    } 

    // Called just before this Command runs the first time
    protected void initialize() {
   // 	Robot.CameraData.putNumber("ledMode", 0);
	//	Robot.CameraData.putNumber("camMode", 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Pneumatics_Subsystem.extendArms();
setTimeout(10);
    	Robot.Pneumatics_Subsystem.closeArms();
    	setTimeout(10);
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
