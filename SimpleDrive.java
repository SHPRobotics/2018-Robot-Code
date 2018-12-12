package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class SimpleDrive{
	
	Joystick lj = null;
	Victor ld = null;
	Joystick rj = null;
	Victor rd = null;
	Joystick ej = null;
	
	//ADXRS450_Gyro gyro1 = null;
	SimpleDrive(Joystick rightJoy, Joystick leftJoy, Victor rightDrive, Victor leftDrive, Joystick endJoy) {
		
		rj = rightJoy;
		lj = leftJoy;
		rd = rightDrive;
		ld = leftDrive;
		//gyro1 = gyro;
		ej = endJoy;
		
	}
	
	
	public void linDrive(){
		
		ld.setSpeed((-lj.getY()));
		rd.setSpeed((rj.getY()));
	}

	public void autoLinDrive(boolean setGo){
		if(setGo){
			ld.setSpeed(.5);
			rd.setSpeed(.5);
		}
		else{
			ld.stopMotor();
			rd.stopMotor();
		}
	}
	
	
	public void curveDrive(){
		
		ld.setSpeed(((.6 * Math.pow(lj.getY(), 3)) + (.1 * Math.pow(lj.getY(), 2)) + (.3 * lj.getY())));
		rd.setSpeed(((.6 * Math.pow(rj.getY(), 3)) + (.1 * Math.pow(rj.getY(), 2)) + (.3 * rj.getY())));
		
	}
	
	public void reverseDrive(){
		
		if(ej.getIsXbox() == true){
		
		
		
			ld.setSpeed(-((.6 * Math.pow(lj.getY(), 3)) + (.1 * Math.pow(lj.getY(), 2)) + (.3 * lj.getY())));
			rd.setSpeed((.6 * Math.pow(rj.getY(), 3)) + (.1 * Math.pow(rj.getY(), 2)) + (.3 * rj.getY()));
			
		}
			}
	
	/*public double ASR(double angel, double startSpeed){
		
		double rightSpeed = startSpeed;
		
		
		
		if (gyro1.getAngle() < angel) {
			
			rightSpeed -= .05;
			
		}
		else if(gyro1.getAngle() > angel){
			
			rightSpeed += .05;
			
		}
		
		return rightSpeed;
	
	}*/
	
	public void autoRotDrive(double passTurn){
		if(passTurn>0){//rotate right until alignment
			ld.setSpeed(.5);
			rd.setSpeed(-.5);
		}
		else if(passTurn<0){//rotate left until alignment
			ld.setSpeed(-.5);
			rd.setSpeed(.5);
		}
		
	}
	/*public void autoAngularDrive(double angle){//THIS NEEDS TO BE FINISHED!!!!!!
		
		if(!((gyro1.getAngle() < angle+5)&&(gyro1.getAngle() > angle-5))){
			//if((SimpleDrive.convertAngle(gyro1.getAngle() + 180) > angle)||(SimpleDrive.convertAngle(gyro1.getAngle()) < angle))
			//{
			
				ld.setSpeed(-.5);
				rd.setSpeed(.5);
			//}
			//else{
				//ld.setSpeed(.5);
				//rd.setSpeed(-.5);
			//}
		}
		else{
			ld.stopMotor();
			rd.stopMotor();
		}
	}*/
	
	private static double convertAngle(double angle){
		if(angle > 360){
			
				angle -= 360;
			
		}
		else if(angle < 0){
			
				angle += 360;
			
		}
		return angle;
	}
	
}
