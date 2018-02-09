package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.commands.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/*import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;*/
//Sample Code
//https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/VelocityClosedLoop/src/org/usfirst/frc/team217/robot/Robot.java

public class DriveTrain_Subsystem extends Subsystem{
    private final DifferentialDrive MainDrive = RobotMap.driveTrain_MainDrive;
    private final PowerDistributionPanel PDP = RobotMap.RobotPDP;
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive_Command(0,0,0));
		
	}
	@Override
	public void periodic() {  
		SmartDashboard.putNumber("Drive Train 1", PDP.getCurrent(0));
		SmartDashboard.putNumber("Drive Train 2", PDP.getCurrent(1));
		/*SmartDashboard.putNumber("Power Controller 3", PDP.getCurrent(2));
		SmartDashboard.putNumber("Power Controller 4", PDP.getCurrent(3));
		SmartDashboard.putNumber("Power Controller 5 (30 AMP)", PDP.getCurrent(4));
		SmartDashboard.putNumber("Camera", PDP.getCurrent(7));
		SmartDashboard.putNumber("Power Controller 7 (30 AMP)", PDP.getCurrent(11));
		SmartDashboard.putNumber("Power Controller 8", PDP.getCurrent(12));
		SmartDashboard.putNumber("Power Controller 9", PDP.getCurrent(13));
		SmartDashboard.putNumber("Drive Train 3", PDP.getCurrent(14));
		SmartDashboard.putNumber("Drive Train 4", PDP.getCurrent(15));*/
		
		double robotSpeed = (RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0)+ (RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*-1))/2;
		robotSpeed = robotSpeed * 10; //Ticks per Sec
		SmartDashboard.putNumber("Average RPM", robotSpeed/ RobotMap.encoderPerRev*60);
		SmartDashboard.putNumber("Robot FPS", robotSpeed / RobotMap.ticksPerInch/12);
		SmartDashboard.putNumber("Right Ticks", RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*-1);
		SmartDashboard.putNumber("Left Ticks", RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right RPM", RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
		SmartDashboard.putNumber("Left RPM", RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
		
		SmartDashboard.putNumber("Right Total Ticks", RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)*-1);
		SmartDashboard.putNumber("Left Total Ticks", RobotMap.driveTrain_LeftMotor.getSelectedSensorPosition(0));
		
		DriveWithJoysticks();
	}
	
	public void DriveWithJoysticks() {
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
	
	public void StartAutoRight() {
		
	
	}
	
	public void StartAutoLeft() {
		//RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*43.9);
		
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*48);
		RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*48);
		MainDrive.tankDrive(.5, .5, true);
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

