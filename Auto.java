package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Auto {
	
	
	Spark ld;
	Spark rd;
	Victor shoot;
	Victor elevate;
	String platformData;
	char nearSideSwitch;
	char scaleSide;
	char farSideSwitch;
	double turningTime = 5;
	double fowardTime = 10;
	double goalTime = 15;

	Timer time = new Timer();
	
	Auto(Spark leftDrive, Spark rightDrive, Victor shooter, Victor elevator){
		
		ld = leftDrive;
		rd = rightDrive;
		shoot = shooter;
		elevate = elevator;
		
	}
	
	public void setTimer() {
		
		time.reset();
		time.start();
		
	}
	
	public void setPlatformData() {
		
		platformData = DriverStation.getInstance().getGameSpecificMessage();
		nearSideSwitch = platformData.charAt(0);
		scaleSide = platformData.charAt(1);
		farSideSwitch = platformData.charAt(2);
		
	}
	
	/*public void autoScaleShooter() {
		
		if (scaleSide == 'L') {
			
			if (time.get() <= turningTime) {
				
				ld.setSpeed(.5);
				
			}
			
			else if (time.get() <= fowardTime && time.get() > turningTime) {
				
				ld.setSpeed(.5);
				rd.setSpeed(-.5);
				shoot.setSpeed(1);
				
			}
			
			else if (time.get() <= goalTime && time.get() > fowardTime) {
				
				shoot.setSpeed(1);
				//Push the cube into the shooter
				
			}
			
			else {
				
				ld.setSpeed(0);
				rd.setSpeed(0);
				
			}
			
		}
		
		else {
			
			if (time.get() <= turningTime) {
				
				rd.setSpeed(-.5);
				
			}
			
			else if (time.get() <= fowardTime && time.get() > turningTime) {
				
				ld.setSpeed(.5);
				rd.setSpeed(-.5);
				shoot.setSpeed(1);
				
			}
			
			else if (time.get() <= goalTime && time.get() > fowardTime) {
				
				shoot.setSpeed(1);
				//Push the cube into the shooter
				
			}
			
			else {
				
				ld.setSpeed(0);
				rd.setSpeed(0);
				
			}
			
		}
		
	}*/
	
	public void autoScaleElevator() {
		
		if (scaleSide == 'L') {
			
			if (time.get() <= turningTime) {
				
				ld.setSpeed(.5);
				
			}
			
			else if (time.get() > turningTime && time.get() <= fowardTime) {
				
				ld.setSpeed(.5);
				rd.setSpeed(-.5);
				elevate.setSpeed(.5);
				
			}
			
			else if (time.get() > fowardTime && time.get() <= goalTime) {
				
				ld.setSpeed(0);
				rd.setSpeed(0);
				elevate.setSpeed(.5);
				
			}
			
			else {
				
				ld.setSpeed(0);
				rd.setSpeed(0);
				elevate.setSpeed(0);
				
			}
			
		}
		
		else {
			
			if (time.get() <= turningTime) {
				
				rd.setSpeed(.5);
				
			}
			
			else if (time.get() > turningTime && time.get() <= fowardTime) {
				
				ld.setSpeed(.5);
				rd.setSpeed(-.5);
				elevate.setSpeed(.5);
				
			}
			
			else if (time.get() > fowardTime && time.get() <= goalTime) {
				
				ld.setSpeed(0);
				rd.setSpeed(0);
				elevate.setSpeed(.5);
				
			}
			
			else {
				
				ld.setSpeed(0);
				rd.setSpeed(0);
				elevate.setSpeed(0);
				
			}
			
		}
		
	}
	
}
