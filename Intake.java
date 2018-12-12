package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Intake {
	
	Joystick rj = null;
	Joystick ej = null;
	
	Victor invic = null;
	
	public Intake(Joystick rightJoy, Joystick endJoy, Victor intake){
		
		rj = rightJoy;
		ej = endJoy;
		invic = intake;
		
	}
	
	public void collect(){
		
		if (ej.getIsXbox() == true){
			
			if (ej.getRawAxis(3) >= .5){
				
				invic.setSpeed(-1);
				
			}else{
				
				invic.set(0);
			}
			
		}
		
	}
	
	public void collectTest(){
		
		if (rj.getRawButton(2) == true){
			
			invic.setSpeed(-1);
			
		}else{
			
			invic.setSpeed(0);
		}
		
	}
	
}
