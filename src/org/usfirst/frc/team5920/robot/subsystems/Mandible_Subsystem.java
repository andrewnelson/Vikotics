package org.usfirst.frc.team5920.robot.subsystems;

import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class Mandible_Subsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	 public Mandible_Subsystem() {
	    	//currentPosition = RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0);
	    }
	    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void periodic() { 
   	
    	if (OI.OperatorRightBumper()) {
    		RobotMap.Mandible_Left.set(ControlMode.PercentOutput, 0.75 * getJoystickWithDeadBand(OI.OperatorRightTrigger()));
    		RobotMap.Mandible_Right.set(ControlMode.PercentOutput, 0.75 * getJoystickWithDeadBand(OI.OperatorRightTrigger()));
    		
    	}else {
    		RobotMap.Mandible_Left.set(ControlMode.PercentOutput, -0.8 * getJoystickWithDeadBand(OI.OperatorRightTrigger()));
    		RobotMap.Mandible_Right.set(ControlMode.PercentOutput, -0.8 * getJoystickWithDeadBand(OI.OperatorRightTrigger()));
    	}
    	    	
    	if (OI.OperatorX()) {
    		RobotMap.Gantry_In_Motion=false;
    		
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
		}
    }
    
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

	private double getJoystickWithDeadBand(double joystickvalue) {
		joystickvalue = joystickvalue*-1;
		if (Math.abs(joystickvalue)<.2) {
			return 0;
		} else {
			return joystickvalue;
		}
	}
}

