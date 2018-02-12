package org.usfirst.frc.team5920.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
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
    	if (OI.DriverX()) {
			FullMandibleExtend();
		}
		if (OI.DriverY()) {
			FullMandibleClose();
		}
    }
    
    public void innerMandibleExtend() {
    	RobotMap.innerMandibles.set(DoubleSolenoid.Value.kForward);
    }
    public void outerMandibleExtend() {
    	RobotMap.outerMandibles.set(DoubleSolenoid.Value.kForward);
    }
    public void FullMandibleExtend() {
    	RobotMap.innerMandibles.set(DoubleSolenoid.Value.kForward);
    	RobotMap.outerMandibles.set(DoubleSolenoid.Value.kForward);
    }
    
    public void innerMandibleClose() {
    	RobotMap.innerMandibles.set(DoubleSolenoid.Value.kReverse);
    }
    public void outerMandibleClose() {
    	RobotMap.outerMandibles.set(DoubleSolenoid.Value.kReverse);
    }
    public void FullMandibleClose() {
    	RobotMap.innerMandibles.set(DoubleSolenoid.Value.kReverse);
    	RobotMap.outerMandibles.set(DoubleSolenoid.Value.kReverse);
    }
}

