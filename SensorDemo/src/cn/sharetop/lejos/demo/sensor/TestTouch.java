package cn.sharetop.lejos.demo.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.TouchAdapter;
import lejos.utility.DebugMessages;
import lejos.utility.Delay;

public class TestTouch {
	private static final DebugMessages log = new DebugMessages();
	
	public void test(){
		
		//触碰传感器接到1号口
		EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S1);
		SensorMode mode =  sensor.getTouchMode();
		
		float[] samples = new float[mode.sampleSize()];
		System.out.println("====samples size is "+samples.length);
		
		while( true ){
			sensor.fetchSample(samples, 0);
			
//			for(int i=0;i<samples.length;i++)
//				log.echo("sample["+i+"] = "+samples[i]+",");
			
			//如果samples[0]==1.0，说明被触碰了。
			if( samples[0]==1.0){
				break;
			}
			Delay.msDelay(1000);
			
		}
		sensor.close();
	}
	
	public void testOne(){
		
		TouchAdapter adapter = new TouchAdapter(new EV3TouchSensor(SensorPort.S1));
		
//		while(true){
//			
//			log.echo("touch status is "+adapter.isPressed());
//				
//			if( adapter.isPressed() ) break;
//			Delay.msDelay(1000);
//		}
		
		synchronized (adapter) {
	         while ( !adapter.isPressed() )
				try {
					
					log.echo("status="+adapter.isPressed());
					adapter.wait(500);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     }
		
	}
}
