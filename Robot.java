/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        *//* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5992.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	
	// Joystick Object
	Joystick leftJoy = new Joystick(0);
	Joystick rightJoy = new Joystick(1);
	XboxController xboxController = new XboxController(2);
	
	//Drive motors
	Spark leftDrive = new Spark(0);
	Spark rightDrive = new Spark(1);
	
	//end effector
	Victor intaker = new Victor(2);
	Victor elevator = new Victor(3);
	DoubleSolenoid intakePis = new DoubleSolenoid(0,0,1);
	DoubleSolenoid climber = new DoubleSolenoid(0,2,3);
	DoubleSolenoid inOpen = new DoubleSolenoid(0,4,5);
	
	
	//PDP
	PowerDistributionPanel pdp = new PowerDistributionPanel(1);
	
	//Limit Switch
	DigitalInput limitUp = new DigitalInput(0);
	DigitalInput limitDown = new DigitalInput(1);
	
	//Team made class objects
	Drive drive = new Drive(leftJoy, rightJoy, leftDrive, rightDrive);
	Auto auto = new Auto(leftDrive, rightDrive, elevator, intaker);
	Intake intake = new Intake(intaker, xboxController, intakePis);
	Elevator elevate = new Elevator(limitUp, limitDown, xboxController, elevator);
	Climber climb = new Climber(climber, xboxController);
	
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		intakePis.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	
	
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
		
		auto.setTimer();
		auto.setPlatformData();
		
	}

	
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	
	@Override
	public void teleopPeriodic() {

		drive.exponentialDrive();
		//elevate.moveElevator();
		elevate.limOverride();
		climb.climbBar();
		intake.takeCube();
		System.out.println("up " + limitUp.get());
		System.out.println("down " + limitDown.get());
		
	}

	
	@Override
	public void testPeriodic() {
	}
}
