package org.usfirst.frc.team5920.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5920.robot.subsystems.*;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;

/**
 *
 */
public class Mandible_Command extends Command {
	private boolean innerMandible;
	private boolean outerMandible;

    public Mandible_Command(boolean innerMandible, boolean MandibleClosed) {
	    	requires(Robot.Pneumatics_Subsystem);
	    	if (innerMandible) {
	    		innerMandible = MandibleClosed;
	    	}else {
	    		outerMandible = MandibleClosed;
	    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		innerMandible = true;
    		outerMandible = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    	if (innerMandible) {
	    		Robot.Pneumatics_Subsystem.innerMandibleClose();
	    	} else {
	    		Robot.Pneumatics_Subsystem.innerMandibleExtend();
	    	}
	    	if (outerMandible) {
	    		Robot.Pneumatics_Subsystem.outerMandibleClose();
	    	} else {
	    		Robot.Pneumatics_Subsystem.outerMandibleExtend();
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
