package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Intake {

	Victor in;
	XboxController xbox;
	
	Intake (Victor intaker, XboxController xboxController) {
		
		in = intaker;
		xbox = xboxController;
	}
	
	public void takeCube () {
		
		if (xbox.getXButton()) {
			
			in.setSpeed(.5);
			
		}
		
		else {
			
			in.setSpeed(0);
			
		}
		
	}
}

