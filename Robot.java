package org.usfirst.frc.team5992.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot // implements VisionRunner.Listener<Team5992VisionPipeline>{
{	
	//Command autonomousCommand;
	//SendableChooser<Object> autoChooser;
	
	//Sensor Base
	//AnalogInput ultraranger1 = new AnalogInput(0);//WARNING:the analog input will only accept from 0-5V, scale the 0-10V down to prevent overloading
	//I2C i2cBusDevice = new I2C(I2C.Port.kOnboard, 0);//this should give us access to the compass through the I2C bus
	//ADXRS450_Gyro gyro1 = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);//constructs the gyro
	//double tmpAngle = 0;
	
	//joysticks
	
	Joystick leftJoy = new Joystick(0);
	Joystick rightJoy = new Joystick(1);
	Joystick endJoy = new Joystick(2);
	
	//motor control
	Victor leftDrive = new Victor(0);
	Victor rightDrive = new Victor(1);
	Victor shooter = new Victor(2);
	Spark intake = new Spark(4);//these values
	Spark climber = new Spark(3);//have been changed
	//Spark agitamater = new Spark(6);//changed
	
	//pneumatics control
	Compressor compy = new Compressor();
	DoubleSolenoid soleGear = new DoubleSolenoid(0, 0, 1);
	DoubleSolenoid soleTank = new DoubleSolenoid(0, 2, 3);
	
	//extra
	Timer t = new Timer();
	final double kAutonDriveTime = 10;
	int station;
		
	PowerDistributionPanel pdp = new PowerDistributionPanel();	
	double autoSpeed = .6;
	double autoSpeed1 = -.6;
	
	
	//subsystems/objects
	SimpleDrive drive = new SimpleDrive(rightJoy, leftJoy, rightDrive, leftDrive, endJoy);
	Shooter shootShoot = new Shooter(endJoy, leftJoy, rightJoy, shooter, pdp, soleTank);
	//Intake pullItIn = new Intake(rightJoy, endJoy, intake);
	Scale pullUps = new Scale(leftJoy, endJoy, climber);
	//VisionHandler handle = new VisionHandler(drive);
	
	//Auton autonObj = new Auton(leftDrive, rightDrive, soleGear, t, null, drive, shooter, shootShoot);
	//Auton autonObj = new Auton(leftDrive, rightDrive, soleGear, t, ultraranger1, gyro1, drive);
	final boolean kAutonSelect = true; //SELECTS THE AUTONOMOUS MODE, IF CHANGED BETWEEN ROUNDS, UPDATE THE CODE
								 //TRUE = COMPLEX AUTON WITH BALLS --------------RED
								 //FLASE = SIMPLE AUTON WITH DRIVE FORWARD
	
	
	@Override
	public void robotInit() {
		//SimpleDrive drive = new SimpleDrive(rightJoy, leftJoy);		
		//handle.cameraInit();
		
		//CameraServer.getInstance().startAutomaticCapture();
		soleTank.set(DoubleSolenoid.Value.kForward);//start piston blocking balls
		shooter.setSpeed(0);
		intake.set(0);
		climber.set(0);
		//agitamater.set(0);
		compy.start();
		soleGear.set(DoubleSolenoid.Value.kReverse);
		//gyro1.reset();
		station = DriverStation.getInstance().getLocation();
		
		
	}

	
	@Override
	public void autonomousInit() {
		//handle.autoLinDrive
		t.start();
		//gyro1.reset();
		soleTank.set(DoubleSolenoid.Value.kForward);
		soleGear.set(DoubleSolenoid.Value.kReverse);
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		if(kAutonSelect){
			if(t.get() < 5){
				
				leftDrive.setSpeed(-.4);
				rightDrive.setSpeed(.45);
				
				
			}
			else{
				leftDrive.stopMotor();
				rightDrive.stopMotor();
			}
			/*if (t.get() < .5){
				leftDrive.setSpeed(-.6);
				rightDrive.setSpeed(drive.ASR(0, autoSpeed));
				autoSpeed = drive.ASR(0, autoSpeed);
				
				leftDrive.setSpeed(0);
				rightDrive.setSpeed(0);
				//shooter.setSpeed(-1);
				shootShoot.shootVolt(true);
				//agitamater.set(-.5);
			}	
			else if(t.get()<1.5){
				shootShoot.shootVolt(true);
				soleGear.set(DoubleSolenoid.Value.kReverse);
				
			}
			else if (t.get() < 4){
				
				drive.autoAngularDrive(120);
				tmpAngle = gyro1.getAngle();
				System.out.println("Angle: " + tmpAngle);
			}else if(t.get()<5){
		
				leftDrive.setSpeed(-.6);
				rightDrive.setSpeed(drive.ASR(tmpAngle, autoSpeed));
				autoSpeed = drive.ASR(tmpAngle, autoSpeed);
			
			}
			//else if(t.get()<13){
			else{
				leftDrive.setSpeed(-.6);
				rightDrive.setSpeed(drive.ASR(tmpAngle, autoSpeed));
				autoSpeed = drive.ASR(tmpAngle, autoSpeed);
			
				
			
			}
			else{
				leftDrive.setSpeed(.6);
				rightDrive.setSpeed(drive.ASR(tmpAngle, autoSpeed));
			}
			*/
		}
		else{
			if (t.get() < 1){
				
				shootShoot.shootVolt(true);
				
			}
			else if(t.get() < 10){
				
				shootShoot.shootVolt(true);
				soleTank.set(DoubleSolenoid.Value.kReverse);//Open piston, let balls through
				
			}
			/*
			else if(t.get() < 8){
				leftDrive.setSpeed(-.2);
			}
			
			else if(t.get() < 10){
				tmpAngle = gyro1.getAngle();
				drive.autoAngularDrive();
			}*/
			else if(t.get() >= 10){
				
				shooter.setSpeed(0);
				leftDrive.setSpeed(-.65);
				rightDrive.setSpeed(.4);
				//rightDrive.setSpeed(drive.ASR(0, autoSpeed1));
				//autoSpeed1 = drive.ASR(0, autoSpeed1);
				
			}
		}
		
		}
		
		
	
	

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopInit(){
		//autonObj.free();
		//gyro1.reset();
		leftDrive.stopMotor();
		rightDrive.stopMotor();
		soleGear.set(DoubleSolenoid.Value.kReverse);
		}
	
	@Override
	public void teleopPeriodic() {
		
		
		//shootShoot.shootTest();
		
		if(endJoy.getIsXbox() == true){
			
			drive.linDrive();
			//DriverStationLCD.getInstance().println(DriverStationLCD.Line, 1, gyro1.getAngle());
			//DriverStationLCD.getInstance().updateLCD();
			/*if (gyro1.getAngle()>45){
				soleTank.set(DoubleSolenoid.Value.kReverse);
			}*/
			shootShoot.stopper();
			//shootShoot.shoot();
			shootShoot.shootVolt(false);
			//pullItIn.collect();
			//pullUps.climb();
			if (endJoy.getRawAxis(1) >= .25 || endJoy.getRawAxis(1) <= -.25){
				
				intake.set(endJoy.getRawAxis(1));
				climber.set(-endJoy.getRawAxis(1));
				
			}
			else {
				
				intake.set(0);
				climber.set(0);
				
			}
			if (endJoy.getRawButton(2) == true){
				soleGear.set(DoubleSolenoid.Value.kForward);//Open

			}
			else{
				
				soleGear.set(DoubleSolenoid.Value.kReverse);//Closed
		
			}
			
			/*if(rightJoy.getRawButton(11) == true){
				handle.autoAdj();
			}*/
			//agitamater.set(1.0);
		
		
		
		}
	}
		
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

