package com.example.basiclauncher;

import android.app.Activity;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {

	GPSTracker gps;
	
	public AndroidGPSTrackingActivity(){
		gps = new GPSTracker(AndroidGPSTrackingActivity.this);
		
		if(gps.canGetLocation()){
			
			double latitude = gps.getLatitude();
			double longitude = gps.getLatitude();
			
			Toast.makeText(getApplicationContext(), "Location is recorded: " + latitude + ", " + longitude, Toast.LENGTH_LONG);
		}
		else{
			gps.showSettingsAlert();	
		}
	}
}
