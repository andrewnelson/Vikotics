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
	AHRS ahrs;

	  double last_world_linear_accel_x;
	  double last_world_linear_accel_y;

	  final static double kCollisionThreshold_DeltaG = 0.5f;
	boolean collisionDetected = false;
    //private final DifferentialDrive MainDrive = RobotMap.driveTrain_MainDrive;
    //private final PowerDistributionPanel PDP = RobotMap.RobotPDP;
	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new TankDrive_Command());
		
	}
    public DriveTrain_Subsystem() {
    	 try {
             /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
             /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
             /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
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
        
        if ( ( Math.abs(currentJerkX) > kCollisionThreshold_DeltaG ) ||
             ( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) ) {
            collisionDetected = true;
        }
        SmartDashboard.putBoolean(  "CollisionDetected", collisionDetected);
        
		
		
		  SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
          SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
          SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
          SmartDashboard.putNumber(   "IMU_Pitch",            ahrs.getPitch());
          SmartDashboard.putNumber(   "IMU_Roll",             ahrs.getRoll());
          SmartDashboard.putNumber(   "IMU_CompassHeading",   ahrs.getCompassHeading());
          SmartDashboard.putNumber(   "IMU_FusedHeading",     ahrs.getFusedHeading());
          SmartDashboard.putNumber(   "IMU_TotalYaw",         ahrs.getAngle());
          SmartDashboard.putNumber(   "IMU_YawRateDPS",       ahrs.getRate());
          SmartDashboard.putNumber(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
          SmartDashboard.putNumber(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
          SmartDashboard.putBoolean(  "IMU_IsMoving",         ahrs.isMoving());
          SmartDashboard.putBoolean(  "IMU_IsRotating",       ahrs.isRotating());
          SmartDashboard.putNumber(   "Velocity_X",           ahrs.getVelocityX());
          SmartDashboard.putNumber(   "Velocity_Y",           ahrs.getVelocityY());
          SmartDashboard.putNumber(   "Displacement_X",       ahrs.getDisplacementX());
          SmartDashboard.putNumber(   "Displacement_Y",       ahrs.getDisplacementY());
          SmartDashboard.putNumber(   "RawGyro_X",            ahrs.getRawGyroX());
          SmartDashboard.putNumber(   "RawGyro_Y",            ahrs.getRawGyroY());
          SmartDashboard.putNumber(   "RawGyro_Z",            ahrs.getRawGyroZ());
          SmartDashboard.putNumber(   "RawAccel_X",           ahrs.getRawAccelX());
          SmartDashboard.putNumber(   "RawAccel_Y",           ahrs.getRawAccelY());
          SmartDashboard.putNumber(   "RawAccel_Z",           ahrs.getRawAccelZ());
          SmartDashboard.putNumber(   "RawMag_X",             ahrs.getRawMagX());
          SmartDashboard.putNumber(   "RawMag_Y",             ahrs.getRawMagY());
          SmartDashboard.putNumber(   "RawMag_Z",             ahrs.getRawMagZ());
          SmartDashboard.putNumber(   "IMU_Temp_C",           ahrs.getTempC());
          
		
		
		
		
		double robotSpeed = (RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0)+ (RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*-1))/2;
		robotSpeed = robotSpeed * 10; //Ticks per Sec
		SmartDashboard.putNumber("Robot FPS", Math.abs(robotSpeed / RobotMap.ticksPerInch/12));
		SmartDashboard.putNumber("Right Ticks", RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*-1);
		SmartDashboard.putNumber("Left Ticks", RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right RPM", RobotMap.driveTrain_RightMotor.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
		SmartDashboard.putNumber("Left RPM", RobotMap.driveTrain_LeftMotor.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
		
		SmartDashboard.putNumber("Right Total Ticks", RobotMap.driveTrain_RightMotor.getSelectedSensorPosition(0)*-1);
		SmartDashboard.putNumber("Left Total Ticks", RobotMap.driveTrain_LeftMotor.getSelectedSensorPosition(0));


		
		//DriveWithJoysticks();
		DriveWithSpeed();
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
		
		if(OI.PercisionSpeed()) {
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
	
	public void StartAutoLeft() {
		//MainDrive.arcadeDrive(1,-.7);
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		
		
		/*RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*120);
		
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*43.9);
		
		RobotMap.driveTrain_LeftMotor.set(ControlMode.Position, RobotMap.ticksPerInch*48);
		RobotMap.driveTrain_RightMotor.set(ControlMode.Position, RobotMap.ticksPerInch*48);*/
		//MainDrive.tankDrive(.5, .5, true);
	}
	
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

