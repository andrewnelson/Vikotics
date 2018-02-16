package org.usfirst.frc.team5920.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5920.robot.RobotMap;
import org.usfirst.frc.team5920.robot.OI;
import org.usfirst.frc.team5920.robot.Robot;
import org.usfirst.frc.team5920.robot.commands.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class Pneumatics_Subsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   
    @Override
    public void periodic() {
    	if (OI.DriverY()) {
    		innerMandibleExtend();
			outerMandibleExtend();
		}
    	if (OI.DriverB()) {
    		innerMandibleExtend();
			outerMandibleClose();
    	}
		if (OI.DriverA()) {
			innerMandibleClose();
			outerMandibleClose();
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
/*    public void FullMandibleExtend() {
	    	RobotMap.innerMandibles.set(DoubleSolenoid.Value.kForward);
	    	RobotMap.outerMandibles.set(DoubleSolenoid.Value.kForward);
    }*/
    
    public void innerMandibleClose() {
    		RobotMap.innerMandibles.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putBoolean("Inner Mandible Open", false);
    }
    public void outerMandibleClose() {
    		RobotMap.outerMandibles.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putBoolean("Outer Mandible Open", false);
    }
/*    public void FullMandibleClose() {
	    	RobotMap.innerMandibles.set(DoubleSolenoid.Value.kReverse);
	    	RobotMap.outerMandibles.set(DoubleSolenoid.Value.kReverse);
    }*/
}

