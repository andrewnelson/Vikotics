package org.usfirst.frc.team5920.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.commands.*;
import com.ctre.phoenix.motorcontrol.ControlMode;


public class Gantry_Subsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	/*private double currentPosition;
	
    public Gantry_Subsystem() {
    	currentPosition = RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0);
    }
    */
	
	public void periodic() {
	    	if (OI.OperatorRightBumper()) {
	    		
	    		RobotMap.Mandible_Left.set(ControlMode.Velocity, RobotMap.MandibleSpeed);
	    		RobotMap.Mandible_Right.set(ControlMode.Velocity, RobotMap.MandibleSpeed);
	    	}else {
	    		RobotMap.Mandible_Left.set(ControlMode.Velocity, 0);
	    		RobotMap.Mandible_Right.set(ControlMode.Velocity, 0);
	    	}
	    	
	    	if (OI.OperatorLeftBumper()) {
	    		
	    		RobotMap.Cage_LeftMotor.set(ControlMode.Velocity, RobotMap.CageSpeed);
	    		RobotMap.Cage_RightMotor.set(ControlMode.Velocity, RobotMap.CageSpeed);
	    	}else {
	    		RobotMap.Cage_LeftMotor.set(ControlMode.Velocity, 0);
	    		RobotMap.Cage_RightMotor.set(ControlMode.Velocity, 0);
	    		
	    	}
		RobotMap.Gantry_PrimeMotor.set(ControlMode.Velocity, (RobotMap.GantrySpeed * getJoystickWithDeadBand(OI.OperatorRightJoystick())));
	    //	SmartDashboard.putNumber("Gantry Position", RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0));
    }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }	
    public void Start() {
		//
	}
	public void Stop() {
		//MainDrive.tankDrive(0, 0);
		RobotMap.Gantry_PrimeMotor.set(0);
		RobotMap.Cage_LeftMotor.set(0);
		RobotMap.Cage_RightMotor.set(0);
		RobotMap.Mandible_Left.set(0);
		RobotMap.Mandible_Right.set(0);
		
	}
    public void DropGantry() {
    	//Take Gantry to bottom (limitswitch) and reset ofset to 0
    	
    	
    }
    public void moveTo(int Gantry_Position) {
    	//currentPosition = RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0);
    	switch (Gantry_Position) {
        case 0: //Send Gantry to position 0
        		DropGantry();
                break;
        case 1: ////Send Gantry to position 1
                break;
        case 2: //Send Gantry to position 2
                 break;
        case 3: //Send Gantry to position 3
                 break;
        case 4: //Send Gantry to climb position
        		break;
        		
        default: //Send Gantry to position 0;
        		 DropGantry();
                 break;
    }
    }
    
	private double getJoystickWithDeadBand(double joystickvalue) {
		if (Math.abs(joystickvalue)<.2) {
			return 0;
		} else {
			return joystickvalue;
		}
	}
}

