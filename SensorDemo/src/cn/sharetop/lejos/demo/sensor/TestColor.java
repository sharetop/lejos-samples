package cn.sharetop.lejos.demo.sensor;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.ColorAdapter;
import lejos.utility.DebugMessages;
import lejos.utility.Delay;

public class TestColor {
	private static final DebugMessages log = new DebugMessages();
	
	
	public void test(){
		
		
		EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S1);
		
		//模式一：ColorID，一个数
		//SensorMode mode = sensor.getColorIDMode();
		//模式二：RGB，三个数
		//SensorMode mode = sensor.getRGBMode();
		//模式三：环境光，一个数，大则亮，小则暗
		//SensorMode mode = sensor.getAmbientMode();
		//模式四：测量，一个数，大则近，0则远
		SensorMode mode= sensor.getRedMode();
		
		float[] samples = new float[mode.sampleSize()];
		
		while(  !Button.DOWN.isDown() ){
			mode.fetchSample(samples, 0);
			int k=0;
			for(float v : samples)
				log.echo("v["+(k++)+"]="+v);
		}
		sensor.close();
	}
	
	
	public void testOne(){
		
		ColorAdapter adapter = new ColorAdapter(new EV3ColorSensor(SensorPort.S1));
		
		while(  !Button.DOWN.isDown() ){
			
			Color c = adapter.getColor();
			String str = "r="+c.getRed()+",g="+c.getGreen()+",b="+c.getBlue();
			log.echo(str);
			
			Delay.msDelay(1000);
			if( adapter.getColorID()==Color.BLUE ) break;
		}
	}
	
	
	
}
