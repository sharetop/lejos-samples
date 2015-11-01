package cn.sharetop.lejos.demo.sensor;

import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;

public class SimpleSensor {

	public static void main(String[] args) {
		
        //TestTouch touch = new TestTouch();
        //touch.test();
        //touch.testOne();
		
		TestColor color = new TestColor();
		//color.test();
		//color.testOne();
		
		TestInfraRed red = new TestInfraRed();
		//red.test();
		red.testRemote();
	}

}
