package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Gantry_Subsystem extends Subsystem {
	//private double currentPosition;
	
    public Gantry_Subsystem() {
    	//currentPosition = RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0);
    }
    
    public void periodic(){
    	//SmartDashboard.putNumber("dPAD", OI.OperatorPOV());
    	OI.GantryMover();
    	SmartDashboard.putNumber("Mandible Run", RobotMap.Mandible_Left.getSelectedSensorVelocity(0)*10/RobotMap.encoderPerRev*60);
    	
    	if (OI.OperatorLeftBumper()) {
    		RobotMap.Mandible_Left.set(ControlMode.PercentOutput, getJoystickWithDeadBand(OI.OperatorLeftTrigger()));
    	}else {
    		RobotMap.Mandible_Left.set(ControlMode.PercentOutput, -1 * getJoystickWithDeadBand(OI.OperatorLeftTrigger()));
    	}
    	
    	if (OI.OperatorRightBumper()) {
    		RobotMap.Mandible_Right.set(ControlMode.PercentOutput, getJoystickWithDeadBand(OI.OperatorRightTrigger()));
    	}else {
    		RobotMap.Mandible_Right.set(ControlMode.PercentOutput, -1 * getJoystickWithDeadBand(OI.OperatorRightTrigger()));
    	}
    	//RobotMap.Gantry_PrimeMotor.set(ControlMode.PercentOutput, getJoystickWithDeadBand(OI.OperatorRightJoystick()));
    	if (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>0) {SmartDashboard.putBoolean("G0", true);}
    	if (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>RobotMap.GantryHeight[1]) {SmartDashboard.putBoolean("G1", true);}
    	if (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>RobotMap.GantryHeight[2]) {SmartDashboard.putBoolean("G2", true);}
    	if (RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0)>RobotMap.GantryHeight[3]) {SmartDashboard.putBoolean("G0", true);}
    	SmartDashboard.putNumber("Gantry Position", RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0));
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void DropGantry() {
    	//Take Gantry to bottom (limitswitch) and reset ofset to 0	
    }
    public void moveTo(int Gantry_Position) {
    	//currentPosition = RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0);
    	switch (Gantry_Position) {
        case 180: //Send Gantry to bottom (position 0)
        	RobotMap.Gantry_PrimeMotor.set(ControlMode.Position, RobotMap.GantryHeight[0]);
        	DropGantry();
        	break;
        case 270: //Send Gantry to position 1 (10")
        	RobotMap.Gantry_PrimeMotor.set(ControlMode.Position, RobotMap.GantryHeight[1]);
    		break; 	
        case 0: //Send Gantry to position 2 (20")
        	RobotMap.Gantry_PrimeMotor.set(ControlMode.Position, RobotMap.GantryHeight[2]);
                break;
        case 90: //Send Gantry to position 2
        	RobotMap.Gantry_PrimeMotor.set(ControlMode.Position, RobotMap.GantryHeight[3]);
                 break;
                 
        case 45: //unused
            break;
        case 135: //unused
            break;
        case 225: //unused
    		break;
        case 315: //unused
    		break;
        default: //Send Gantry to position 0;
    		 DropGantry();
             break;
    }
    }
    
	private double getJoystickWithDeadBand(double joystickvalue) {
		if (!RobotMap.Gantry_TopLimit.get() && (joystickvalue < 0) ) {return 0;}
		if (!RobotMap.Gantry_BottomLimit.get() && (joystickvalue > 0) ) {return 0;}
		if (Math.abs(joystickvalue)<.2) {
			return 0;
		} else {
			return joystickvalue;
		}
	}
}

