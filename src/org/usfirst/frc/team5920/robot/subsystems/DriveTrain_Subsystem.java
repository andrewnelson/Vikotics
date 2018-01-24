package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.commands.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/*import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;*/
//Sample Code
//https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/VelocityClosedLoop/src/org/usfirst/frc/team217/robot/Robot.java

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
		DriveWithJoysticks();
	}
	
	 
	public void DriveWithJoysticks() {
		if (OI.PercisionDrive()){
			MainDrive.tankDrive(getJoystickWithDeadBand(OI.LeftDrive() * RobotMap.percisionspeed), getJoystickWithDeadBand(OI.RightDrive() * RobotMap.percisionspeed));
		}else {
			MainDrive.tankDrive(getJoystickWithDeadBand(OI.LeftDrive()), getJoystickWithDeadBand(OI.RightDrive()));
		}

	}
	
	public void Stop() {
		//MainDrive.tankDrive(0, 0);
	}
	public double getJoystickWithDeadBand(double joystickvalue) {
		if(Math.abs(joystickvalue) < .1)
			 return 0;
		else 
			return joystickvalue;
}
}

