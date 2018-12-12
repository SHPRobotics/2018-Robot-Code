package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Elevator {

	DigitalInput limUp;
	DigitalInput limDown;
	XboxController xbox;
	Victor elevate;
	boolean override = true;
	
	Elevator(DigitalInput limitUp, DigitalInput limitDown, XboxController xboxController, Victor elevator) {
		
		limUp = limitUp;
		limDown = limitDown;
		xbox = xboxController;
		elevate = elevator;
		
	}
	
	public void limOverride() {
		
		if(override) {
			
			moveElevator();
			
		}
		else {
			
			elevate.setSpeed(xbox.getY(GenericHID.Hand.kLeft));
			
		}
	
		
		if(xbox.getStartButton() && xbox.getBackButton()) {
			
			override = false;
			
		}
		
		else if(xbox.getBumper(GenericHID.Hand.kLeft) && xbox.getBumper(GenericHID.Hand.kRight)) {
			
			override = true;
			
		}
		
	}
	
	public void moveElevator() {
		
		if (!limDown.get()) { //Bottom limit switch pressed down
			
			if (xbox.getY(GenericHID.Hand.kLeft) < 0) { //Checks if Left Stick is pressed up
				
				elevate.setSpeed((xbox.getY(GenericHID.Hand.kLeft))); //Moves up
				
			}
			
			else {
				
				elevate.setSpeed(0); //No motion
				
			}
			
		}
		
		else if(limUp.get()) { //Top limit pressed down
			
			if(xbox.getY(GenericHID.Hand.kLeft) > 0) { //Checks if Right Stick is pressed down
				
				elevate.setSpeed((xbox.getY(GenericHID.Hand.kLeft))); //Moves down
				
			}
			
			else {
				
				elevate.setSpeed(0); //No motion
				
			}
			
		}
		else {
			
			elevate.setSpeed((xbox.getY(GenericHID.Hand.kLeft)));
			
		}
	}
	
	public void simpleElevator() {
		
		elevate.setSpeed(-xbox.getY(GenericHID.Hand.kLeft));
		
	}
	
}
