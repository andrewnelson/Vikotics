package org.usfirst.frc.team5920.robot.commands;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;


public class TankDrive_Command extends Command {
    private double m_x;
    private double m_y; 
    private double m_z;
    
    public TankDrive_Command(double x, double y, double z) {
        m_x = x;
        m_y = y;
        m_z = z;
    		requires(Robot.driveTrain_Subsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		//Robot.driveTrain_Subsystem.LeftMotor.set(OI.LeftDrive());
    		//Robot.driveTrain_Subsystem.RightMotor.set(OI.RightDrive());
    	
    		}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.driveTrain_Subsystem.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
