package com.nathanielfoster.transform;

import android.text.format.Time;

public class Utils {

	public static String getUniqueImageFilename() {
		String filename = "img_";
		Time now = new Time();
		now.setToNow();
		
		filename += String.valueOf(now.month) + 
					String.valueOf(now.monthDay) + 
					String.valueOf(now.year) + 
					String.valueOf(now.hour) + 
					String.valueOf(now.minute) + 
					String.valueOf(now.second);
		return filename;
	}

}
