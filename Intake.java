package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*; 

public class Intake {

	Victor in;
	DigitalInput limIn;
	XboxController xbox;
	DoubleSolenoid inpis;
	
	Intake (Victor intaker, XboxController xboxController, DoubleSolenoid intakePis) {
		
		in = intaker;
		xbox = xboxController;
		inpis = intakePis;
		
	}
	
	public void takeCube () {
		
		if (xbox.getXButton()) {
			
			in.setSpeed(.5);
			
		}
		else if(xbox.getYButton()) {
			in.setSpeed(-.5);
		}
		else {
			
			in.setSpeed(0);
			
		}
		
		if(xbox.getBumper(GenericHID.Hand.kRight)) {
			
			inpis.set(DoubleSolenoid.Value.kReverse);
			
		}
		else {
			
			inpis.set(DoubleSolenoid.Value.kForward);
			
		}
		
	}
	
	public void seeCube() {
		
		if (!limIn.get()) {
			
			if (xbox.getYButton()) {
				
				in.setSpeed(-.5);
				
				
			}
			
		}
		else {
			
			takeCube();
			
		}
		
	}
	
	public void betterTakeCube() {
		
		if(limIn.get()) {
			
			if(xbox.getXButton()) {
				
				in.setSpeed(.5);
				inpis.set(DoubleSolenoid.Value.kReverse);
				
			}
			
			else {
				
				inpis.set(DoubleSolenoid.Value.kReverse);
				in.setSpeed(0);
				
			}
			
		}
		
		else {
			
			if(xbox.getXButton()) {
				
				inpis.set(DoubleSolenoid.Value.kForward);
				in.setSpeed(-.25);
				
			}
			
			
			else {
				
				inpis.set(DoubleSolenoid.Value.kForward);
				in.setSpeed(0);
				
			}	
		}	
	}	
}
