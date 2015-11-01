package cn.sharetop.lejos.demo.motor;

import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import lejos.utility.Delay;

public class SimpleMotor 
{
	
    public static void main( String[] args )
    {
        System.out.println( "Hello Motor!" );
        
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        ev3.getTextLCD().drawString("Hello Motor", 4, 4);
        
        
//        TestBase base = new TestBase();
//        base.testOne();
//        base.testTwo();
//        base.testThree();
        
        TestSynchronize sync = new TestSynchronize();
        //sync.testFour();
        sync.testFive();
    }
}
