package org.usfirst.frc.team5920.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gantry_Subsystem extends Subsystem {

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
        
        default: //Send Gantry to position 0;
        		 DropGantry();
                 break;
    }
    }
}

