package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.commands.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
		SmartDashboard.putBoolean("Cube in Cage", RobotMap.Cage_CubePresent.get());
    	if (OI.OperatorRightBumper()) {
    		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, RobotMap.CageSpeed);
    		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, RobotMap.CageSpeed);
    	}else {
    		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, 0);
    		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, 0);    		
    	}
    	/*
    	if (OI.OperatorRightTrigger()) {
    		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, -0.75);
    		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, -0.75);
    	}else {
    		RobotMap.Cage_LeftMotor.set(ControlMode.PercentOutput, 0);
    		RobotMap.Cage_RightMotor.set(ControlMode.PercentOutput, 0);   
    	}*/
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
}

