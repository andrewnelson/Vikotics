package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

//Moving to the TalonSRX
// https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/README.md

@SuppressWarnings("deprecation")
public class DriveTrain extends Subsystem{
	private RobotDrive MainDrive = new RobotDrive(RobotMap.driveLeft, RobotMap.driveRight);

private Victor leftDrive = new Victor(RobotMap.driveLeft);
private Victor rightDrive = new Victor(RobotMap.driveRight);

	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
		
	}
	
	public void TeleopDrive(Joystick Driver, Joystick Driver2) {
		MainDrive.tankDrive(Driver.getY(), Driver2.getY());
	}
	
	public void Stop() {
		MainDrive.drive(0, 0);
	}
}