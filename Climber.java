package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Climber {
	
	Victor climb;
	XboxController xbox;
	DoubleSolenoid climbPis;
	
	Climber(Victor climber, XboxController xboxController, DoubleSolenoid climberPiston) {
		
		climb = climber;
		xbox = xboxController;
		climbPis = climberPiston;
		
	}
	
	public void climbBar() {
		
		if (xbox.getY(GenericHID.Hand.kRight) > -.25 && xbox.getY(GenericHID.Hand.kRight) < .25) {
			
			climb.setSpeed(0);
			if (xbox.getBackButton()) {
				
				climb.setSpeed(-.9);
				
			}
			
		}
		else {
			
		climb.setSpeed(-(xbox.getY(GenericHID.Hand.kRight)));
		
		}
		if(xbox.getXButton()) {
			
			climbPis.set(DoubleSolenoid.Value.kForward);
			
		}
		
		else {
			
			climbPis.set(DoubleSolenoid.Value.kReverse);
			
			
		}
		
		if (xbox.getBackButton()) {
			
			climb.setSpeed(-.9);
			
		}
	}
}
