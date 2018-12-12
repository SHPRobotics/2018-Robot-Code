package org.usfirst.frc.team5992.robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {
	
	private final double kMotorScaling = (12)*(.54); 		//note this is the perfect 12volts times the wanted pwm value for the shooter motor
	
	private Joystick shootStick = null;
	private Joystick rj = null;
	private Joystick lj = null;
	
	private Victor shootVic = null;
	
	//private Spark agit = null;
	
	private PowerDistributionPanel ppd = null;
	private DoubleSolenoid soleTank = null;
	
	
	//private double current;
	//private double totalVoltage;
	
	/*private final int kShooterPDPChannel = 3;	//I don't know what channel this is, change it later when wired */
	
	public Shooter(Joystick endJoy, Joystick leftJoy, Joystick rightJoy, Victor shooter, PowerDistributionPanel pdp, DoubleSolenoid tank){
	//PDP object is passed to obtain a reference to the current and voltage going to the shooter	
		shootStick = endJoy;
		lj = leftJoy;
		rj = rightJoy;
		shootVic = shooter;
		ppd = pdp;
		//agit = agitamater;
		soleTank = tank;
		soleTank.set(DoubleSolenoid.Value.kForward);
		
	}
	public void stopper(){
	
	if(shootStick.getIsXbox() == true){
		
		if (shootStick.getRawButton(1) == true){
			
			soleTank.set(DoubleSolenoid.Value.kForward);
			
		}else{
			
			soleTank.set(DoubleSolenoid.Value.kReverse);
		}
		
	}
	}
	public void shoot() {
		/*current = ppd.getCurrent(kShooterPDPChannel);//abstraction so the channel only needs to be changed once in the code */
		//totalVoltage = ppd.getVoltage();
		
		if(shootStick.getIsXbox() == true){
			
			if(shootStick.getRawAxis(2) >= .5){
				
				//shootVic.setSpeed(.54);
				//shootVic.setSpeed(lj.getThrottle());
				shootVic.setSpeed(-1);	//should adjust the PWM to make up for lost voltage, not perfect, just slightly more accurate
				//agit.set(.5);
			
				
			}
			else{
				shootVic.setSpeed(0);
				//soleTank.set(DoubleSolenoid.Value.kForward);
				//agit.set(0);
				
			}
			
		}
		
	}

	
	public void shootTest(){
		
		if (rj.getRawButton(1) == true){
			
			shootVic.setSpeed(lj.getThrottle());
			//agit.set(-.5);
			soleTank.set(DoubleSolenoid.Value.kReverse);
			
		}else{
			
			shootVic.setSpeed(0);
			//agit.set(0);
			soleTank.set(DoubleSolenoid.Value.kForward);
			
		}
		
		System.out.println(lj.getThrottle());
		
	}
	
	public void shootVolt(boolean auto){
		
		if(((shootStick.getRawAxis(2))>=.5)||(rj.getRawButton(1))|| auto == true){
			
			shootVic.setSpeed(-(6)/ppd.getVoltage()); //11.16 = 12*0.93 idk if that's significant
			//agit.set(.5);
			//soleTank.set(DoubleSolenoid.Value.kReverse);
			
		}else{
			
			shootVic.setSpeed(0);
			//agit.set(0);
			//soleTank.set(DoubleSolenoid.Value.kForward);
		}
		
	}
	
	
	
	
}
