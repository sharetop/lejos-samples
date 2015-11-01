package cn.sharetop.lejos.demo.sensor;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.DebugMessages;
import lejos.utility.Delay;

public class TestInfraRed {
	
	private static final DebugMessages log = new DebugMessages();
	
	public void test(){
		
		
		EV3IRSensor sensor = new EV3IRSensor(SensorPort.S1);
		//模式一：测距，一个数，越小越近，远了就变成Infinity
		//SensorMode mode = sensor.getDistanceMode();
		//模式二：定位，8个数,4组，每组两个（方位-25~25，距离远了也是Infinity）
		SensorMode mode = sensor.getSeekMode(); 
		
		float[] samples = new float[mode.sampleSize()];
		
		while(  !Button.DOWN.isDown() ){
			mode.fetchSample(samples, 0);
			int k=0;
			for(float v : samples)
				System.out.println("===v["+(k++)+"]="+v);
				//log.echo("v["+(k++)+"]="+v);
			
			Delay.msDelay(1000);
		}
		sensor.close();
	}
	
	public void testRemote(){
		
		EV3IRSensor sensor = new EV3IRSensor(SensorPort.S1);
		
		while(  !Button.DOWN.isDown() ){
			
			int cmd = sensor.getRemoteCommand(2);
			if(cmd!=0)
				log.echo("cmd="+cmd);
			
			byte[] buf = new byte[4];
			sensor.getRemoteCommands(buf, 0, 4);
			int k=0;
			for(float v : buf){
				if(v!=0)
					System.out.println("===v["+(k++)+"]="+v);
				else
					k++;
				
					//log.echo("v["+(k++)+"]="+v);
			}
			Delay.msDelay(1000);
		}
		sensor.close();
	}
}

