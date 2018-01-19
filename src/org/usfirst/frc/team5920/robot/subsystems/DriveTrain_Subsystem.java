package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.commands.TankDrive_Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain_Subsystem extends Subsystem{
	/*TahomaRobotics Calling code
	  private final DriveController leftDrive = new DriveController(new MotorController[] {
			new MotorController(new VictorSP(RobotMap.driveLeft), false) });*/
	private VictorSP leftDrive = new VictorSP(RobotMap.driveLeft);
	private VictorSP rightDrive = new VictorSP(RobotMap.driveRight);
	private DifferentialDrive MainDrive = new DifferentialDrive(leftDrive, rightDrive);

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive_Command());
		
	}
	
	public void TeleopDrive(Joystick Driver, Joystick Driver2) {
		MainDrive.tankDrive(Driver.getY(), Driver2.getY());
	}
	
	public void Stop() {
		MainDrive.tankDrive(0, 0);
	}
}
