package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.commands.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Cage_Subsystem extends Subsystem {
 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void periodic() {
		if (!DriverStation.getInstance().isAutonomous()) {
			if (OI.OperatorLeftBumper()) {
	    		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput,  -1 * getJoystickWithDeadBand(OI.OperatorLeftTrigger()));
	    		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, -1 * getJoystickWithDeadBand(OI.OperatorLeftTrigger()));
	    		
	    	}else {
	    		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, getJoystickWithDeadBand(OI.OperatorLeftTrigger()));
	    		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, getJoystickWithDeadBand(OI.OperatorLeftTrigger()));
	    	}
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	public void Start() {
		//Spin up cage wheels.
	}
	public void Stop() {
		//Stop Cage wheels.
	}
	public void EjectCube(){
		
	}
	public void EjectCubeReverse(){
		
	}
	
	private double getJoystickWithDeadBand(double joystickvalue) {
		if (Math.abs(joystickvalue)<.2) {
			return 0;
		} else {
			return joystickvalue;
		}
	}
}

