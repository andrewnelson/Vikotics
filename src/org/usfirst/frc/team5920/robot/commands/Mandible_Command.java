package org.usfirst.frc.team5920.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Mandible_Command extends Command {
	private int mPosition;

    public Mandible_Command(int MandiblePosition) {
	    	requires(Robot.Mandible_Subsystem);
	    	mPosition = MandiblePosition;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		mPosition=3;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		switch(mPosition) {
    		case 1 :
    			Robot.Mandible_Subsystem.innerMandibleClose();
    			Robot.Mandible_Subsystem.outerMandibleClose();
    		case 2 :
    			Robot.Mandible_Subsystem.innerMandibleExtend();
    			Robot.Mandible_Subsystem.outerMandibleClose();
    		case 3 :
    			Robot.Mandible_Subsystem.innerMandibleExtend();
    			Robot.Mandible_Subsystem.outerMandibleExtend();
    		 default :
     			Robot.Mandible_Subsystem.innerMandibleExtend();
     			Robot.Mandible_Subsystem.outerMandibleExtend();
    		}
	    /*	if (innerMandible) {
	    		Robot.Pneumatics_Subsystem.innerMandibleClose();
	    	} else {
	    		Robot.Pneumatics_Subsystem.innerMandibleExtend();
	    	}
	    	if (outerMandible) {
	    		Robot.Pneumatics_Subsystem.outerMandibleClose();
	    	} else {
	    		Robot.Pneumatics_Subsystem.outerMandibleExtend();
	    	}*/
	    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
