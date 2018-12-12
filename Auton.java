package org.usfirst.frc.team5992.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Auton {
	Victor ld = null;
	Victor rd = null;
	Victor shoot = null;
	AnalogInput ranger1 = null;
	
	double autoSpeed = -.6;
	Shooter shootObj = null;
	
	Timer time = null;
	//ADXRS450_Gyro gyro1 = null;
	
	DoubleSolenoid solGear = null;
	//final double KBASELINETRIGGER = 4.8;//will be set later based on the bias of the sensor
	//private boolean atBaseLine = false;
	private boolean atAngle = false;
	
	SimpleDrive drive = null;
	
	public Auton (Victor leftDrive, Victor rightDrive, DoubleSolenoid soleGear, Timer t, AnalogInput rangefinder, SimpleDrive simpleDriveObj, Victor shooter, Shooter shootObjPassed){
		drive = simpleDriveObj;
		ld  = leftDrive;
		rd = rightDrive;
		solGear = soleGear;
		time = t;
		//ranger1 = rangefinder;
		//gyro1 = gyro;
		shooter = shoot;
		shootObj = shootObjPassed;
		
		//gyro1.calibrate();//calibrate the gyroscope when the constructor is called
	}
	
	
	
	public void gear(){//will replace these values with ultrasonic sensor  
		if (time.get() < 3){
			ld.setSpeed(.25);
			rd.setSpeed(-.25);			
		}
		else if (time.get() >= 3 && time.get() < 3.1){
			ld.setSpeed(0);
			rd.setSpeed(0);
		}
		else if (time.get() >= 3.1 && time.get() < 3.4){
			solGear.set(DoubleSolenoid.Value.kReverse);
		}
		else if (time.get() >= 3.4 && time.get() < 4){
			ld.setSpeed(-.25);
			rd.setSpeed(.25);
		}
		else {
			ld.setSpeed(0);
			rd.setSpeed(0);
		}
		
	}
	/*
	public void autonRedShoot(){
		
		if (time.get() < 1.5){
			ld.setSpeed(-.6);
			rd.setSpeed(drive.ASR(0, autoSpeed));
			autoSpeed = drive.ASR(0, autoSpeed);
		}	
		else if (time.get() < 4){
			
			drive.autoAngularDrive(120);
			
		}else if(time.get()<5){
		
			
			ld.setSpeed(-.6);
			rd.setSpeed(drive.ASR(0, autoSpeed));
			autoSpeed = drive.ASR(0, autoSpeed);
			
		}
		else if(time.get()<13){
			
			ld.setSpeed(0);
			rd.setSpeed(0);
			//shoot.setSpeed(-1);
			shootObj.shootVolt(true);
			//agitamater.set(-.5);
			
		}
			else{
				ld.setSpeed(.6);
				rd.setSpeed(drive.ASR(0, autoSpeed));
			}
		
	}
	
	public void autonBlueShoot(){
		
		if (time.get() < 2){
			
			shoot.setSpeed(-1);
			
		}
		else if(time.get() < 13){
			
			solGear.set(DoubleSolenoid.Value.kReverse);
			
		}
		else{
			
			shoot.setSpeed(0);
			ld.setSpeed(.6);
			rd.setSpeed(drive.ASR(180, autoSpeed));
			autoSpeed = drive.ASR(180, autoSpeed);
			
		}
	
		//for i in range(6):
		//for int i 
		
	}
	
	public void gearRanger(){//gear method using values from the ultrasonic sensor
		if ((ranger1.getVoltage() > 4.8)&&(Timer.getMatchTime() < 3)){
			ld.setSpeed(.25);
			rd.setSpeed(-.25);			
		}
		else if ((ranger1.getVoltage() > 4.8)&& (Timer.getMatchTime() >= 3 && Timer.getMatchTime() < 3.1)){
			ld.setSpeed(0);
			rd.setSpeed(0);
		}
		else if ((ranger1.getVoltage() < 0.5)&&(Timer.getMatchTime() >= 3.1 && Timer.getMatchTime() < 3.4)){
			solGear.set(DoubleSolenoid.Value.kReverse);
		}
		else if ((Timer.getMatchTime() > 3.4) && (ranger1.getVoltage() < (.5))){
			ld.setSpeed(-.25);
			rd.setSpeed(.25);
		}
		else {
			ld.setSpeed(0);
			rd.setSpeed(0);
		}
	}
	
	public void baseLineRanger(){
		if(ranger1.getVoltage() > KBASELINETRIGGER){
			ld.setSpeed(.3);
			rd.setSpeed(.3);
			atBaseLine = false;
		}
		else{
			ld.setSpeed(0);
			rd.setSpeed(0);
			atBaseLine = true;
		}
	}
	
	public boolean getAtBaseLine(){
		return atBaseLine;
	}
	
	public void shootLeft(){
		drive.autoAngularDrive(135);
		atAngle = true;
	}
	public void shootRight(){
		drive.autoAngularDrive(225);
		atAngle = true;
	}
	*/
	public boolean getAtAngle(){
		return atAngle;
	}
}

