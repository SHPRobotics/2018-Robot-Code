package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;
import java.lang.Math;

public class Drive {

	Joystick lj;
	Joystick rj;
	XboxController xbox;
	Spark ld;
	Spark rd;
	
	Drive(XboxController xboxController, Joystick leftJoy, Joystick rightJoy, Spark leftDrive, Spark rightDrive){
		
		xbox = xboxController;
		lj = leftJoy;
		rj = rightJoy;
		ld = leftDrive;
		rd = rightDrive;
		
	}
	
	public void linearDrive() {
		
		ld.setSpeed(-lj.getY());
		rd.setSpeed(rj.getY());
		
	}
	
	public void exponentialDrive() {
		
		ld.setSpeed(-((.7*Math.pow(lj.getY(), 3))+(.3*lj.getY())));
		rd.setSpeed(((.7*Math.pow(rj.getY(), 3))+(.3*rj.getY())));
		
	}
	
	public void chooseDrive() {
		
		if (rj.getTrigger() && lj.getTrigger()) {
			
			revDrive();
			
		}
		else {
			
			exponentialDrive();
			
		}
		
	}
	
	public void revDrive() {
		
		rd.setSpeed(-((.7*Math.pow(lj.getY(), 3))+(.3*lj.getY())));
		ld.setSpeed(((.7*Math.pow(rj.getY(), 3))+(.3*rj.getY())));
		
	}
	
	public void testXboxDrive() {
		
		ld.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kRight));
		rd.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kRight));
		
		ld.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kLeft));
		rd.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kLeft));
		
	}
	
	public void linearXboxDrive() {
		
		if(xbox.getX(GenericHID.Hand.kLeft) > 0.1) {
			
			//Fowards Turning
			ld.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kRight));
			rd.setSpeed(((xbox.getTriggerAxis(GenericHID.Hand.kLeft))*(-Math.abs(xbox.getX(GenericHID.Hand.kLeft))+1)));
			
			//Backwards Turning
			ld.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kLeft));
			rd.setSpeed(-((xbox.getTriggerAxis(GenericHID.Hand.kLeft))*(-Math.abs(xbox.getX(GenericHID.Hand.kLeft))+1)));
			
		}
		
		else if(xbox.getX(GenericHID.Hand.kLeft) < -0.1) {
			
			//Fowards Turning
			rd.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kRight));
			ld.setSpeed(-((xbox.getTriggerAxis(GenericHID.Hand.kLeft))*(-Math.abs(xbox.getX(GenericHID.Hand.kLeft))+1)));
			
			//Backwards Turning
			rd.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kLeft));
			ld.setSpeed(((xbox.getTriggerAxis(GenericHID.Hand.kLeft))*(-Math.abs(xbox.getX(GenericHID.Hand.kLeft))+1)));
			
		}
		
		else if((xbox.getX(GenericHID.Hand.kLeft) == 1) && (xbox.getTriggerAxis(GenericHID.Hand.kRight) == 1) && (xbox.getBButton())) {
			
			ld.setSpeed(-.5);
			rd.setSpeed(-.5);
			
		}
		
		else if((xbox.getX(GenericHID.Hand.kLeft) == -1) && (xbox.getTriggerAxis(GenericHID.Hand.kRight) == 1) && (xbox.getBButton())) {
			
			ld.setSpeed(.5);
			rd.setSpeed(.5);
			
		}
		
		else {
			
			ld.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kRight));
			rd.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kRight));
			
			ld.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kLeft));
			rd.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kLeft));
			
		}
		
	}
	
	public void exponentialXboxDrive() {		
		
		if(xbox.getX(GenericHID.Hand.kLeft) > 0.1) {
			
			ld.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kRight));
			rd.setSpeed((xbox.getTriggerAxis(GenericHID.Hand.kRight))*(-((.7*(Math.abs(xbox.getX(GenericHID.Hand.kLeft))))+(.3*(Math.abs(xbox.getX(GenericHID.Hand.kLeft)))))+1));
			
			ld.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kLeft));
			rd.setSpeed(-(xbox.getTriggerAxis(GenericHID.Hand.kRight))*(-((.7*(Math.abs(xbox.getX(GenericHID.Hand.kLeft))))+(.3*(Math.abs(xbox.getX(GenericHID.Hand.kLeft)))))+1));
			
		}
		
		else if(xbox.getX(GenericHID.Hand.kLeft) < -0.1) {
			
			rd.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kRight));
			ld.setSpeed(-(xbox.getTriggerAxis(GenericHID.Hand.kRight))*(-((.7*(Math.abs(xbox.getX(GenericHID.Hand.kLeft))))+(.3*(Math.abs(xbox.getX(GenericHID.Hand.kLeft)))))+1));
			
			rd.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kLeft));
			ld.setSpeed((xbox.getTriggerAxis(GenericHID.Hand.kRight))*(-((.7*(Math.abs(xbox.getX(GenericHID.Hand.kLeft))))+(.3*(Math.abs(xbox.getX(GenericHID.Hand.kLeft)))))+1));
			
		}
		
		else if((xbox.getX(GenericHID.Hand.kLeft) == 1) && (xbox.getTriggerAxis(GenericHID.Hand.kRight) == 1)) {
			
			ld.setSpeed(-.5);
			rd.setSpeed(-.5);
			
		}
		
		else if((xbox.getX(GenericHID.Hand.kLeft) == -1) && (xbox.getTriggerAxis(GenericHID.Hand.kRight) == 1)) {
			
			ld.setSpeed(.5);
			rd.setSpeed(.5);
			
		}
		
		else {
			
			ld.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kRight));
			rd.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kRight));
			
			ld.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kLeft));
			rd.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kLeft));
			
		}
		
	}
	
	/*public void seanTrapp() {
		
		final String David = "im depressed";
		
		if(xbox.getStartButton() && xbox.getAButton() && xbox.getTriggerAxis(GenericHID.Hand.kRight) == 1 && xbox.getBumper(GenericHID.Hand.kRight)) {
			while(David == "im depressed") {
					
				System.out.println("David is depressed and will now proceed to kill himself");
				
			}
		}	
		
		else {
			
			System.out.println("David is depressed but he won't kill himself");
			
		}
		
	}*/
	
}
