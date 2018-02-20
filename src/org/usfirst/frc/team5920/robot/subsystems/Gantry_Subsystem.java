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
	private double currentPosition;
	
    public Gantry_Subsystem() {
    	currentPosition = RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0);
    }
    
    public void periodic(){
    	RobotMap.driveTrain_RightMotor.set(ControlMode.Velocity, (Velocity * getJoystickWithDeadBand(OI.DriverRightJoystick())));
    	RobotMap.Gantry_PrimeMotor.set(getJoystickWithDeadBand(OI.OperatorRightJoystick()));
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
    	currentPosition = RobotMap.Gantry_PrimeMotor.getSelectedSensorPosition(0);
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
			return 0 * RobotMap.robotDirection;
		} else {
			return joystickvalue * RobotMap.robotDirection;
		}
	}
}

