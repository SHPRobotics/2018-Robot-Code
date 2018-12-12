package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Auto {
	
	
	Spark ld;
	Spark rd;
	Victor elevate;
	Victor intake;
	String platformData;
	char nearSideSwitch;
	char scaleSide;
	char farSideSwitch;
	double turningTime = 5;
	double fowardTime = 10;
	double goalTime = 15;
	DigitalInput limUp;
	DigitalInput co;
	DigitalInput ct;
	PowerDistributionPanel pdd;

	Timer time = new Timer();
	
	Auto(Spark leftDrive, Spark rightDrive, Victor elevator, Victor intaker, DigitalInput limitUp, DigitalInput chooseOne, DigitalInput chooseTwo, PowerDistributionPanel pdp){
		
		ld = leftDrive;
		rd = rightDrive;
		elevate = elevator;
		intake = intaker;
		limUp = limitUp;
		co = chooseOne;
		ct = chooseTwo;
		pdd = pdp;
		
	}
	
	public void chooseAuto() {
		
	}
	
	public void setTimer() {
		
		time.reset();
		time.start();
		
	}
	
	public double setVolts(double volts) {
		
		double voltage = pdd.getVoltage();
		
		return (volts / voltage);
		
	}
	
	public void setPlatformData() {
		
		platformData = DriverStation.getInstance().getGameSpecificMessage();
		nearSideSwitch = platformData.charAt(0);
		scaleSide = platformData.charAt(1);
		farSideSwitch = platformData.charAt(2);
		
	}
	
	public void tL(double speedy) {
		
		ld.setSpeed(-setVolts(5));
		rd.setSpeed(-setVolts(5));
		
	}
	
	public void tR(double speedy) {
		
		ld.setSpeed(setVolts(5));
		rd.setSpeed(setVolts(5));
		
	}
	
	public void dS(double driveSpeed) {
		
		ld.setSpeed(.92*driveSpeed);
		rd.setSpeed(-driveSpeed);
		
	}
	
	public void quickTest1() {
		
		if(time.get() < 3) {
			
			dS(setVolts(.5));
			elevate.setSpeed(0.5);
			
		}
		else {
			
			dS(0);
			elevate.setSpeed(0);
			intake.setSpeed(.4);
			
		}
		
	}
	
	public void startingLeftSide(Solenoid s1, Solenoid s2) {
		
		if(nearSideSwitch == 'L') {
			
			if(time.get() < .5) {
				s1.set(false);
				s2.set(false);
				dS(0);
				intake.setSpeed(-.3);
				
			}
			
			else if(time.get() <= 3.5) {
				
				ld.setSpeed(.3);
				rd.setSpeed(-.3);
				
			}
		
			else if (time.get() < 6.5) {
				
				s1.set(false);
				s2.set(false);
				dS(.4);
				elevate.setSpeed(.7);
				
			}
			
			else if(time.get() < 7.75) {
				
				ld.setSpeed(.41);
				rd.setSpeed(.41);
				elevate.setSpeed(0);
				intake.setSpeed(0);
				
			}
			
			else if(time.get() < 9.25) {
				
				dS(.4);
				elevate.setSpeed(0);
				
			}
			
			else if(time.get() < 10.235) {
				
				dS(0);
				elevate.setSpeed(0);
				intake.setSpeed(.3);
				
			}
			
			else {
				
				dS(0);
				elevate.setSpeed(0);
				intake.setSpeed(0);
				
			}
			
		}
		
		else {
			
			driveF();
			
		}
		
	}
	public void driveF() {
		
		if(time.get() <= 6.5) {
			ld.setSpeed(setVolts(4.375));
			rd.setSpeed(-setVolts(4.375));
			
	}
		else {
			
			dS(0);
			
		}
	}
	
	public void quickTest2() {
		
		if(time.get() < 3) {
			
			dS(.5);
			
		}
		
		else {
			
			dS(0);
			
		}
		
	}
	
	public void startingMiddle(Solenoid s1, Solenoid s2) {
		
		if(time.get() < .5) {
			
			ld.setSpeed(.25);
			rd.setSpeed(-.25);
			
		}
		else if(time.get() <= 2) {
			
			ld.setSpeed(.25);
			rd.setSpeed(-.25);
			
		}
		
		if (nearSideSwitch == 'L') {			
			
			 if (time.get() <= 2.875 && time.get() >= 2) {
				 
				 intake.setSpeed(-.2);
				 s1.set(false);
				 s2.set(false);
				 ld.setSpeed(-.4);
				 rd.setSpeed(-.4);
				 
			 }
			 
			 else if(time.get() <= 7.6) {
				 
				 dS(.35);
				 
			 }
			 
			 else if(time.get() <= 8.05) {
				 
				 ld.setSpeed(.4);
				 rd.setSpeed(.4);
				 
			 }
			 
			 else if(time.get() < 9) {
				 
				 ld.setSpeed(.4);
				 rd.setSpeed(.4);
				 elevate.setSpeed(1);
				 
			 }
			 
			 else if(time.get() <= 11.02) {
				 
				 dS(.4);
				 elevate.setSpeed(1);
				 
			 }
			 
			 else if (time.get() <= 11.80) {
				 
				 dS(0);
				 elevate.setSpeed(0);
				 intake.setSpeed(.3);
				 
			 }
			 
			 else {
				 
				 intake.setSpeed(0);
				 
			 }
		}
		else {
			
			if(time.get() <= 1.5) {
				dS(.3);
				s1.set(false);
				s2.set(false);
				
			}
		
			else if(time.get() < 6.5) {
				
				dS(.3);
				elevate.setSpeed(.7);
				
			}
			
			else if(time.get() < 7) {
				
				dS(0);
				elevate.set(0);
				intake.setSpeed(.3);
				
			}
			
			else {
				
				dS(0);
				elevate.set(0);
				intake.setSpeed(0);
				
			}
		}
	}
	
	public void startingRightSide(Solenoid s1, Solenoid s2) {

		if(nearSideSwitch == 'R') {
			
			if(time.get() <= 4) {
						
				dS(.4);
						
			}
			
			else if(time.get() < 7) {
				
				s1.set(false);
				s2.set(false);
				dS(.3);
				elevate.setSpeed(.7);
				
			}
			
			else if(time.get() < 8) {
				
				ld.setSpeed(-.4);
				rd.setSpeed(-.4);
				elevate.setSpeed(0);
				intake.setSpeed(0);
				
			}
			
			else if(time.get() < 9.5) {
				
				dS(.4);
				elevate.setSpeed(0);
				
			}
			
			else if(time.get() < 10.5) {
				
				dS(0);
				elevate.setSpeed(0);
				intake.setSpeed(.4);
				
			}
			
			else {
				
				dS(0);
				elevate.setSpeed(0);
				intake.setSpeed(0);
				
			}		
		}		
		
		else {
			
			driveF();
			
		}
	}		

	public void rSR(Solenoid s1, Solenoid s2) {
		
		if (time.get() < 3) {
			
			dS(setVolts(8.75));
			
		}
		
		else if(time.get() < 3.8) {
			
			s1.set(false);
			s2.set(false);
			dS(setVolts(5));
			intake.setSpeed(-.3);
			
		}
		else if(time.get() < 9 || !limUp.get()) {
			
			s1.set(false);
			s2.set(false);
			elevate.setSpeed(1);
			dS(0);
			
		}
		/*else if(time.get() < 9.39) {
			
			dS(.3);
			
		}
		else if(time.get() < 10) {
			
			dS(0);
			//intake.setSpeed(.55);
			
		}
		else if(time.get() < 10.39) {
			
			dS(-.3);
			intake.setSpeed(0);
			
		}
		else if(time.get() < 12) {
			
			//elevate.setSpeed(-.7);
			dS(0);
			
		}*/
		else {
			
			elevate.setSpeed(0);
			dS(0);
			intake.setSpeed(0);
			
		}
	}

	
	public void sLSCO(Solenoid s1, Solenoid s2) {
		
		if(time.get() < 3) {
		
			dS(.7);
			s1.set(false);
			s2.set(false);
			
		}
		else if(time.get() < 3.65) {
			
			tL(.4);
			intake.setSpeed(-.3);
			
		}
		else {
			
			dS(0);
			
		}
	}
	
	public void startRightScale(Solenoid s1,Solenoid s2) {
		
		if(scaleSide == 'R') {
			
			rSR(s1, s2);
			
		}
		else {
			
			sLSCO(s1, s2);
			
		}
		
	}
	
	public void newStartingRightSide(Solenoid s1, Solenoid s2) {

		if(nearSideSwitch == 'R') {
			
			if(time.get() <= 4) {
						
				ld.setSpeed(setVolts(4));
				rd.setSpeed(-setVolts(4));		
				
			}
			
			else if(time.get() < 7) {
				
				s1.set(false);
				s2.set(false);
				//dS(setVolts(3.75));
				elevate.setSpeed(-.7);
				
			}
			
			else if(time.get() < 8) {
				
				ld.setSpeed(-setVolts(5));
				rd.setSpeed(-setVolts(5));
				elevate.setSpeed(0);
				intake.setSpeed(0);
				
			}
			
			else if(time.get() < 9.5) {
				
				dS(setVolts(5));
				elevate.setSpeed(0);
				
			}
			
			else if(time.get() < 10.5) {
				
				dS(0);
				elevate.setSpeed(0);
				intake.setSpeed(.4);
				
			}
			
			else {
				
				dS(0);
				elevate.setSpeed(0);
				intake.setSpeed(0);
				
			}		
		}		
		
		else {
			
			driveF();
			
		}
	}		

	public void newStartingLeftSide(Solenoid s1, Solenoid s2) {
		
		if(nearSideSwitch == 'L') {
			
			if(time.get() < .5) {
				s1.set(false);
				s2.set(false);
				dS(0);
				intake.setSpeed(-.3);
				
			}
			
			else if(time.get() <= 4) {
				
				ld.setSpeed(setVolts(5));
				rd.setSpeed(-setVolts(5.3));
				
			}
			
			else if(time.get() < 5) {
				
				elevate.set(-1);
				
			}
		
			else if (time.get() < 6.25) {
				
				s1.set(false);
				s2.set(false);
				ld.setSpeed(setVolts(5));
				rd.setSpeed(setVolts(5));
				//dS(setVolts(4.5));
				elevate.setSpeed(-1);
				
			}
			
			else if(time.get() < 7.25) {
				
				//ld.setSpeed(setVolts(5));
				//rd.setSpeed(-setVolts(5));
				dS(setVolts(5));
				elevate.setSpeed(0);
				
			}
			
			else if(time.get() < 10.235) {
				
				dS(0);
				elevate.setSpeed(0);
				intake.setSpeed(.6);
				
			}
			
			else {
				
				dS(0);
				elevate.setSpeed(0);
				intake.setSpeed(0);
				
			}
			
		}
		
		else {
			
			driveF();
			
		}
		
	}

	public void leftScale(Solenoid s1, Solenoid s2) {
		
		if (time.get() < 2.89) {
			
			ld.setSpeed(setVolts(9));
			rd.setSpeed(-setVolts(9));
			
		}
		
		else if(time.get() < 3.2) {
			
			s1.set(false);
			s2.set(false);
			dS(0);
			elevate.setSpeed(-1);
			
		}
		else if(time.get() < 3.75) {
			
			s1.set(false);
			s2.set(false);
			ld.set(setVolts(4));
			rd.set(setVolts(4));
			elevate.setSpeed(-1);
			
		}
		else if(time.get() < 8 || !limUp.get()) {
			
			s1.set(false);
			s2.set(false);
			elevate.setSpeed(-1);
			dS(0);
			
		}
		
		else if(time.get() < 9) {
			
			elevate.setSpeed(0);
			dS(setVolts(3));
			
		}
		
		else if(time.get() < 11) {
			
			intake.setSpeed(1);
			elevate.setSpeed(0);
			dS(0);
			
			
		}
		
		
	}
	
	public void rightScale(Solenoid s1, Solenoid s2) {
		
			if (time.get() < 2.9) {
				
				s1.set(true);
				s2.set(true);
				ld.setSpeed(setVolts(8.8));
				rd.setSpeed(-setVolts(9));
				
			}
			
			else if(time.get() < 3.2) {
				s1.set(false);
				s2.set(false);
				dS(0);
				elevate.setSpeed(1);
				
			}
			else if(time.get() < 3.8) {
				
				ld.set(-setVolts(4));
				rd.set(-setVolts(4));
				elevate.setSpeed(-1);
				
			}
			else if(time.get() < 8 || !limUp.get()) {
				
				s1.set(false);
				s2.set(false);
				elevate.setSpeed(-1);
				dS(0);
				
			}
			
			else if(time.get() < 10) {
				
				dS(setVolts(3));
				elevate.setSpeed(0);
				
			}
			
			else if(time.get() < 11) {
				
				intake.setSpeed(1);
				elevate.setSpeed(0);
				dS(0);
				
				
			}
		
	}
	
	public void leftScalePrio(Solenoid s1,Solenoid s2) {
		
		if (scaleSide == 'L') {
			
			leftScale(s1, s2);
			
		}
		else {
			
			newStartingLeftSide(s1, s2);
			
		}
		
	}
	
	public void leftSwitchPrio(Solenoid s1, Solenoid s2) {
		
		if (nearSideSwitch == 'L' || scaleSide == 'R') {
			
			newStartingLeftSide(s1, s2);
			
		}
		else {
			
			leftScale(s1,s2);
			
		}
		
	}

}
	

