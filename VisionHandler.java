package org.usfirst.frc.team5992.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class VisionHandler {
	private SimpleDrive autoDrive = null;
	private double centerX = 0.0;
	private final Object imgLock = new Object();
	private VisionThread viThread = null;
	private final int kWIDTH = 640;
	private final int kHEIGHT = 480;
	private Team5992VisionPipeline pirateViPipeline = null;
	private UsbCamera camera=null;
	
	public VisionHandler(SimpleDrive passedDrive){
		autoDrive = passedDrive;
	}
	//public void visionInit(){}
	public void cameraInit(){
		
		camera = new UsbCamera("cam0", 0);//I THINK THIS IS FINALLY CORRECT!!! THIS WAS WHAT WAS CAUSING THE "No code" PROBLEM!!!
			//FOR THE LOVE OF GOD DONT CHANGE IT!!!
		CameraServer server = CameraServer.getInstance();
		server.putVideo("Camera", 640, 480);
		server.startAutomaticCapture();
		
		
		
		if(camera.isConnected()){
			viThread.start();
		}
		//camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(kWIDTH, kHEIGHT);
		camera.setFPS(30);
        pirateViPipeline = new Team5992VisionPipeline();
        viThread = new VisionThread(camera, pirateViPipeline, pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                centerX = r.x + (r.width / 2);
	            }
	        }
	    });
	}
	
	public void autoAdj(){
		
		synchronized (imgLock) {
			centerX = this.centerX;
		}
		if((centerX != 0.0)){}
		
		
		if (centerX != 0.0){
			double turn = centerX - (kWIDTH / 2);
			
			autoDrive.autoRotDrive(turn);
			
			//drive.arcadeDrive(-0.6, turn * 0.005);
		}
		
			
		else{//if it can't see relfective tape, spin until it can
			autoDrive.autoRotDrive(1);
		}
		
		//return;
		
	}
	
	
}
