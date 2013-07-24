package intersection;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	private Date date;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public Time(){
		date= new Date();
	}
	
	public void setTime(){			
		date = new Date();
	}
	
	public float getTime(){
		return date.getTime();
	}
	
	public float getDuration(){
		return ((new Date()).getTime()-date.getTime());
	}
	
	public Date floatToTime(float timeF){
		date = new Date((long)timeF);
		return date;
	}
	
	public String toString(){
		return (sdf.format(date));
	}
}
