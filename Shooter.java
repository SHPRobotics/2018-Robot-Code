package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.*;

public class Shooter {
	
	Joystick lj;
	Joystick rj;
	XboxController xbox;
	Victor shoot;
	PowerDistributionPanel ppd;
	
	
	Shooter(Joystick leftJoy, Joystick rightJoy,XboxController xboxController, Victor shooter, PowerDistributionPanel pdp) {
		
		lj = leftJoy;
		rj = rightJoy;
		xbox = xboxController;
		shoot = shooter;
		ppd = pdp;
	}
	
	private double shooterVoltage() {
		
		//Make constant when we can
		double conversionConstant = lj.getRawAxis(3);
		
		return (ppd.getVoltage() * conversionConstant);
		
	}
	
	private double shooterSpeed() {
		
		if (12 <= ppd.getVoltage()) {
			
			return (12 / ppd.getVoltage());
		
		}
		
		else {
			
			return (1.0);
			
		}
	}
	
	public void testMotor() {
		
		if (lj.getRawButton(1)) {
			
			shoot.setSpeed(lj.getRawAxis(3));
			
		}
		
		else {
			
			shoot.setSpeed(0);
		}
		
		System.out.println("Voltage Factor = " + lj.getThrottle());
		System.out.println("Shooter Voltage = " + shooterVoltage());
		
	}
	
	public void constantVoltageShoot() {
		
		if (xbox.getTriggerAxis(GenericHID.Hand.kRight) >= .25) {
			
			shoot.setSpeed(shooterSpeed());
			
		}
		
		else {
			
			shoot.setSpeed(0);
		}
		
	}
	
}
