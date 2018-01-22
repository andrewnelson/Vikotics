package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.commands.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain_Subsystem extends Subsystem{
    public final SpeedController LeftMotor = RobotMap.driveTrain_LeftMotor;
    public final SpeedController RightMotor = RobotMap.driveTrain_RightMotor;
    private final DifferentialDrive MainDrive = RobotMap.driveTrain_MainDrive;
	/*TahomaRobotics Calling code
	  private final DriveController leftDrive = new DriveController(new MotorController[] {
			new MotorController(new VictorSP(RobotMap.driveLeft), false) });*/

	
	//private DifferentialDrive MainDrive = new DifferentialDrive(leftDrive, rightDrive);

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive_Command(0,0,0));
		
	}
	@Override
	public void periodic() {
		//MainDrive.tankDrive(joystick1.getY(), joystick2.getY());
	}
	
	/*  
	public void DriveWithJoysticks(Joystick left, Joystick right) {
		MainDrive.tankDrive(left.getY(),  right.getY());
		
	}*/
	
	public void Stop() {
		MainDrive.tankDrive(0, 0);
	}
}
