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
   /* public void periodic() {
    	if (OI.DriverX()) {
			extendArms();
		}
		if (OI.DriverY()) {
			closeArms();
		}
    }*/
    public void extendArms() {
    	RobotMap.intakeArms.set(DoubleSolenoid.Value.kForward);
    }
    public void closeArms() {
    	RobotMap.intakeArms.set(DoubleSolenoid.Value.kReverse);
    }
}

