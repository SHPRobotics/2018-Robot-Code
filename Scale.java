package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Scale {
	
	Joystick lj = null;
	Joystick ej = null;
	
	Spark cv = null;
	
	public Scale(Joystick leftJoy, Joystick endJoy, Spark climber){
		
		lj = leftJoy;
		ej = endJoy;
		cv = climber;
		
	}
	
	public void climb(){
		
		if(ej.getIsXbox() == true){
			
			cv.set(ej.getRawAxis(1));
			
		}
		
	}

	public void climbTest(){
		
		if(lj.getRawButton(1) == true){
			
			cv.setSpeed(.5);
			
		}
		
	}
	
}
