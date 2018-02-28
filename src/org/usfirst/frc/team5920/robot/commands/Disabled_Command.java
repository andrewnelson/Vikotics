package org.usfirst.frc.team5920.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Disabled_Command extends Command {

    public Disabled_Command() {
    	setRunWhenDisabled(true);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString(  "Field State", gameData); 
		if(gameData.charAt(0) == 'L')
		{
			//Put left auto code here
		} else {
			//Put right auto code here
		}
		//Check Bot conditions, and report back to drive station and LEDs
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
