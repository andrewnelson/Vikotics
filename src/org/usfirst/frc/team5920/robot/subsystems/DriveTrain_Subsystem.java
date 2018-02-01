package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.commands.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/*import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;*/
//Sample Code
//https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/VelocityClosedLoop/src/org/usfirst/frc/team217/robot/Robot.java

public class DriveTrain_Subsystem extends Subsystem{
    private final DifferentialDrive MainDrive = RobotMap.driveTrain_MainDrive;
 
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive_Command(0,0,0));
		
	}
	@Override
	public void periodic() {
		DriveWithJoysticks();
	}
	
	public void DriveWithJoysticks() {
		SmartDashboard.putNumber("Right RPM", RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Left RPM", RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0));
		if (OI.autotest()) {
			
		}
	
		if (OI.DriverPercisionDrive()){
			MainDrive.tankDrive(getJoystickWithDeadBand(OI.DriverLeftJoystick() * RobotMap.percisionspeed), getJoystickWithDeadBand(OI.DriverRightJoystick() * RobotMap.percisionspeed));
			//MainDrive.tankDrive(getJoystickWithDeadBand(OI.DriverLeftJoystick() * RobotMap.percisionspeed), getJoystickWithDeadBand(OI.DriverRightJoystick() * RobotMap.percisionspeed), true);
		}else {
			MainDrive.tankDrive(getJoystickWithDeadBand(OI.DriverLeftJoystick()), getJoystickWithDeadBand(OI.DriverRightJoystick()));
			//MainDrive.tankDrive(getJoystickWithDeadBand(OI.DriverLeftJoystick()), getJoystickWithDeadBand(OI.DriverRightJoystick()), true);

		}
	}
	public void Start() {
		//MainDrive.tankDrive(.5, .5);
	}
	
	
	public void StartAutoLeft() {
		//RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*43.9);
		
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*48);
		RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*48);
		//MainDrive.tankDrive(.5, .5, true);
	}
	
	public void Stop() {
		MainDrive.tankDrive(0, 0);
	}
	private double getJoystickWithDeadBand(double joystickvalue) {
		if(Math.abs(joystickvalue) < .1)
			 return 0;
		else 
			return joystickvalue;
}
}

