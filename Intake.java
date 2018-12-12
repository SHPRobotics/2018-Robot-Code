package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*; 

public class Intake {

	Victor in;
	DigitalInput limIn;
	XboxController xbox;
	DoubleSolenoid inpis;
	DoubleSolenoid inop;
	Solenoid ip;
	Solenoid ip2;
	
	Intake (Victor intaker, XboxController xboxController, DoubleSolenoid intakePis, DoubleSolenoid intakeOpen, Solenoid inpis1, Solenoid inpis2) {
		
		in = intaker;
		xbox = xboxController;
		inpis = intakePis;
		inop = intakeOpen;
		ip = inpis1;
		ip2 = inpis2;
		
	}
	
	public void takeCube () {
		
		if (xbox.getYButton()) {
			
			in.setSpeed(.5);
			
		}
		else if(xbox.getXButton()) {
			in.setSpeed(-.5);
		}
		else {
			
			in.setSpeed(0);
			
		}
		
		if(xbox.getBumper(GenericHID.Hand.kLeft)) {
			
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
		
			
		if(xbox.getTriggerAxis(GenericHID.Hand.kRight) == 0) {
				
			in.setSpeed(-xbox.getTriggerAxis(GenericHID.Hand.kLeft)*.8);
			
			}
		
		else if(xbox.getBButton()) {
			
			in.setSpeed(.95);
			
		}
		
		else {
			
			in.setSpeed(xbox.getTriggerAxis(GenericHID.Hand.kRight)*.95);
			
		}
		
		if(xbox.getBumper(GenericHID.Hand.kRight)) {
			
			ip.set(true);
			ip2.set(true);
			
		}
		
		else {
			
			ip.set(false);
			ip2.set(false);
			
		}
		
		if(xbox.getStartButton()) {
			
			inop.set(DoubleSolenoid.Value.kReverse);
			
		}
		
		else {
			
			inop.set(DoubleSolenoid.Value.kForward);
			
		}
	}	
}