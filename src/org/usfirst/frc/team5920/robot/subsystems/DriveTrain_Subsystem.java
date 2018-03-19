package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.commands.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;

/*import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;*/
//Sample Code
//https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/VelocityClosedLoop/src/org/usfirst/frc/team217/robot/Robot.java

public class DriveTrain_Subsystem extends Subsystem{
	int counter = 0;
	public static AHRS ahrs;

	  double last_world_linear_accel_x;
	  double last_world_linear_accel_y;

    //private final DifferentialDrive MainDrive = RobotMap.driveTrain_MainDrive;
    //private final PowerDistributionPanel PDP = RobotMap.RobotPDP;
	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new TankDrive_Command());
		
	}
    public DriveTrain_Subsystem() {
    	 try {
             ahrs = new AHRS(SPI.Port.kMXP); 
         } catch (RuntimeException ex ) {
            // DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
         }
    }
	@Override
	public void periodic() {  
		
		double curr_world_linear_accel_x = ahrs.getWorldLinearAccelX();
        double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
        last_world_linear_accel_x = curr_world_linear_accel_x;
        double curr_world_linear_accel_y = ahrs.getWorldLinearAccelY();
        double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
        last_world_linear_accel_y = curr_world_linear_accel_y;
        
        if ( ( Math.abs(currentJerkX) > RobotMap.kCollisionThreshold_DeltaG ) ||
             ( Math.abs(currentJerkY) > RobotMap.kCollisionThreshold_DeltaG) ) {
            OI.DriverRumbleEnhanced(300, 1, true, true);
            OI.OperatorRumbleEnhanced(300, 1, true, true);
        }
		
		//DriveWithJoysticks();
		if (RobotMap.useJoystick) {DriveWithSpeed();}
	}
	
/*	public void DriveWithJoysticks() {
		if (OI.PercisionSpeed()){
			MainDrive.tankDrive(getJoystickWithDeadBand(OI.DriverLeftJoystick() * RobotMap.percisionspeed), getJoystickWithDeadBand(OI.DriverRightJoystick() * RobotMap.percisionspeed));
			//MainDrive.tankDrive(getJoystickWithDeadBand(OI.DriverLeftJoystick() * RobotMap.percisionspeed), getJoystickWithDeadBand(OI.DriverRightJoystick() * RobotMap.percisionspeed), true);
		}else {
			MainDrive.tankDrive(getJoystickWithDeadBand(OI.DriverLeftJoystick()), getJoystickWithDeadBand(OI.DriverRightJoystick()));
			//MainDrive.tankDrive(getJoystickWithDeadBand(OI.DriverLeftJoystick()), getJoystickWithDeadBand(OI.DriverRightJoystick()), true);

		}
	}*/
	public void DriveWithSpeed() {
		double Velocity = 0;
		
		if(OI.StandardSpeed()) {
			Velocity = RobotMap.slowSpeed;
		} else if (OI.TurboSpeed()) {
			Velocity = RobotMap.turboSpeed;
		} else {
			Velocity = RobotMap.standardSpeed;
		}

		SmartDashboard.putNumber("Velocity", (Velocity * getJoystickWithDeadBand(OI.DriverRightJoystick())));
		RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, (Velocity * getJoystickWithDeadBand(OI.DriverRightJoystick())));
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Velocity, (Velocity * getJoystickWithDeadBand(OI.DriverLeftJoystick())));
	}
	public void Start() {
		//MainDrive.tankDrive(.5, .5);
	}
	
	public void StartAutoRight() {
		
	
	}
	/*
	public void StartAutoLeft() {
		//MainDrive.arcadeDrive(1,-.7);
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		
		
		//RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		//RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		
		//RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*43.9);
		
		//RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*48);
		//RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*48);
		////MainDrive.tankDrive(.5, .5, true);
	}
*/
	
	public void Stop() {
		//MainDrive.tankDrive(0, 0);
		RobotMap.driveTrain_LeftMotor.set(0);
		RobotMap.driveTrain_RightMotor.set(0);
	}
	
	private double getJoystickWithDeadBand(double joystickvalue) {
		if (Math.abs(joystickvalue)<.1) {
			return 0 * RobotMap.robotDirection;
		} else if (joystickvalue > .9) {
			return 1 * RobotMap.robotDirection;
		}else if (joystickvalue < -0.9) {
			return -1 * RobotMap.robotDirection;
		} else {
			return joystickvalue * RobotMap.robotDirection;
		}
	}
}

