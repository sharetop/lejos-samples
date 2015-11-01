package cn.sharetop.lejos.demo.motor;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

public class TestSynchronize {

	public void testFour(){
		Motor.A.setSpeed(Motor.A.getMaxSpeed()*0.6f);
		Motor.B.setSpeed(Motor.B.getMaxSpeed()*0.6f);
		
		Motor.A.setAcceleration(600);
		Motor.B.setAcceleration(600);
		
		//绑定需要同步的几个马达
		Motor.A.synchronizeWith(new RegulatedMotor[]{Motor.B});
		
		//从startSynchronization/endSynchronization之间的代码将会同步执行
		Motor.A.startSynchronization();
        
        Motor.A.rotate(3*360);
        Motor.B.rotate(3*360);
        
		Motor.A.endSynchronization();
		
		//必须的，否则不动，说明前面只是定义，这儿才真正执行
		Motor.A.waitComplete();
	}
	
	public void testFive(){
		
		//轮子直径2.1，小道宽度4.4，由马达A和B组成，前进
		DifferentialPilot pilot = new DifferentialPilot(2.1f, 4.4f, Motor.A, Motor.B, false);  
		
		pilot.setAcceleration(600);
		pilot.setTravelSpeed(10);
		pilot.setRotateSpeed(10);  
		
		//几个动作
		pilot.travel(50);    
		pilot.rotate(-90);        
		pilot.travel(-50,true);  
		
		while(pilot.isMoving())
			Thread.yield();
		 	
		pilot.rotate(-90);

		pilot.steer(-50,180,true); 
		pilot.steer(100);          
		
		Delay.msDelay(1000);
		pilot.stop();
		
	}
	
}
