package org.usfirst.frc.team5920.robot.commands;

import org.usfirst.frc.team5920.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive_command extends Command{
	public TankDrive_command() {
		requires(Robot.DriveTrain);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void execute() {
		Robot.DriveTrain.TeleopDrive(Robot.oi.getDriverJoystick(), Robot.oi.getDriver2Joystick() );
	
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
		
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}

}
