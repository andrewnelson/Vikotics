package org.usfirst.frc.team5920.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.commands.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class Pneumatics_Subsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   
    @Override
    public void periodic() {/*
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
    	
    	if (OI.OperatorY()) {
    		innerMandibleExtend();
    		outerMandibleExtend();
			OI.OperatorRumbleEnhanced(500, 1, true, true);
		}
    	if (OI.OperatorB()) {
    		innerMandibleExtend();
			outerMandibleClose();		
			OI.OperatorRumbleEnhanced(500, 1, true, true);
    	}
		if (OI.OperatorA()) {
			innerMandibleClose();
			outerMandibleClose();
			OI.OperatorRumbleEnhanced(500, 1, true, true);
		}*/
    }
    /*
    public void innerMandibleExtend() {
    		RobotMap.innerMandibles.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putBoolean("Inner Mandible Open", true);
    }
    public void outerMandibleExtend() {
    		RobotMap.outerMandibles.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putBoolean("Outer Mandible Open", true);
    }
    
    public void innerMandibleClose() {
    		RobotMap.innerMandibles.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putBoolean("Inner Mandible Open", false);
    }
    public void outerMandibleClose() {
    		RobotMap.outerMandibles.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putBoolean("Outer Mandible Open", false);
    }
    private double getJoystickWithDeadBand(double value) {
    	return 0;
    }*/
}

