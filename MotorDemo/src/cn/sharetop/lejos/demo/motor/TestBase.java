package cn.sharetop.lejos.demo.motor;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import lejos.utility.Delay;

public class TestBase {

	public void testOne(){
		//获取马达
        //方法1：标准
        RegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.A);
        motor.setSpeed((int)(motor.getMaxSpeed()*0.6));
        
        //EV3LargeRegulatedMotor设置速度允许使用Float，不知道有什么意义
        //EV3LargeRegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.A);
        //motor.setSpeed(36.5f);
        
       //旋转3圈
       // motor.rotate(3*360, false);
       // motor.rotateTo(270);
        
        motor.resetTachoCount();
        motor.forward();
        
        Delay.msDelay(3000);
        //motor.flt();
        motor.stop();
        
        System.out.println("------>"+motor.getTachoCount()); 
	}
	
	public void testTwo(){
		
		   //方法2：简洁，但注意了吗？它是一个NXTRegulatedMotor。
	       Motor.A.setSpeed((float)(Motor.A.getMaxSpeed()*0.6));
	       //Motor.A.rotate(360, false); 
	       //Motor.A.rotateTo(270);
	       
	       Motor.A.resetTachoCount();
	       Motor.A.forward();
	        
	       //ev3.getKeys().waitForAnyPress();
	       
	       Delay.msDelay(3000);
	       Motor.A.flt(true);
	       //Motor.A.stop();
	       
	       System.out.println("------>"+Motor.A.getTachoCount()); 
	}
	
	public void testThree(){
		
		RegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.A);
        motor.setSpeed((int)(motor.getMaxSpeed()*0.6));
        
        motor.addListener(new RegulatedMotorListener(){

			@Override
			public void rotationStarted(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
				// TODO Auto-generated method stub
				System.out.println("=====start>speed is "+motor.getSpeed()+", tacho is "+tachoCount+", time is "+timeStamp);
			}

			@Override
			public void rotationStopped(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
				// TODO Auto-generated method stub
				System.out.println("=====end>speed is "+motor.getSpeed()+", tacho is "+tachoCount+", time is "+timeStamp);
			}
        	
        });
        
        motor.forward();
        Delay.msDelay(3000);
        motor.flt();
		
	}
	
}
