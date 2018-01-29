package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;
import java.lang.Math;

public class Drive {

	Joystick lj;
	Joystick rj;
	Spark ld;
	Spark rd;
	
	Drive(Joystick leftJoy, Joystick rightJoy, Spark leftDrive, Spark rightDrive){
		
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
		
		ld.setSpeed(-((.7*Math.pow(lj.getY(), 3))+(.1*Math.pow(lj.getY(), 2))+(.2*lj.getY())));
		rd.setSpeed(((.7*Math.pow(rj.getY(), 3))+(.1*Math.pow(rj.getY(), 2))+(.2*rj.getY())));
		
	}
	
}

