package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Elevator {

	DigitalInput div;
	XboxController xbox;
	Victor elevate;
	
	Elevator(DigitalInput digitalInputValue, XboxController xboxController, Victor elevator) {
		
		div = digitalInputValue;
		xbox = xboxController;
		elevate = elevator;
		
	}
	
	public void moveElevator() {
		
		if (div.get()) {
			
			elevate.setSpeed(xbox.getY(GenericHID.Hand.kRight));
			
		}
		
		else {
			
			elevate.setSpeed(0);
			
		}
		
	}
	
}

