package org.projectmuks.shareloation;

import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Intent;
import android.telephony.gsm.*;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.net.Uri;
import android.location.*;
import java.util.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onShareLocation(View view){
    	//Intent openBrowser =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.javacodegeeks.com"));
    	//startActivity(openBrowser);
    	LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
    	boolean enabled = service
    			.isProviderEnabled(LocationManager.GPS_PROVIDER);

    	// check if enabled and if not send user to the GSP settings
    	// Better solution would be to display a dialog and suggesting to 
    	// go to the settings
    	if (!enabled) {
    		Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    		startActivity(intent);
    	} 
    	Vector<Double> cord = new Vector<Double>(2);
    	getCord(cord);
    	sendSms("test message");
    }
    
    public void getCord(Vector<Double> cord){
    	cord = new Vector<Double>(2);
    	cord.addElement(new Double(2));
    	cord.addElement(new Double(3));
    }
    
    public void sendSms(String msg){
    	try {
            @SuppressWarnings("deprecation")
			SmsManager smsManager = SmsManager.getDefault();
            String phoneNo="09643363585";
			smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
            Toast.LENGTH_LONG).show();
         } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
            "SMS faild, please try again.",
            Toast.LENGTH_LONG).show();
            e.printStackTrace();
         }
    }
    
}
