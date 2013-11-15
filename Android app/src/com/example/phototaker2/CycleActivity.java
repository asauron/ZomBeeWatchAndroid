package com.example.phototaker2;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CycleActivity extends Activity implements LocationListener {
	  private TextView latituteField;
	  private TextView longitudeField;
	  private LocationManager locationManager;
	  private String provider;

	  
	/** Called when the activity is first created. */

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_cycle);
	    latituteField = (TextView) findViewById(R.id.TextView02);
	    longitudeField = (TextView) findViewById(R.id.TextView04);

	    // Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the location provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);

	    // Initialize the location fields
	    if (location != null) {
	      System.out.println("Provider " + provider + " has been selected.");
	      onLocationChanged(location);
	    } else {
	      latituteField.setText("Location not available");
	      longitudeField.setText("Location not available");
	    }
	  }

	  /* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	  /* Remove the location listener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }

	  @Override
	  public void onLocationChanged(Location location) {
	    int lat = (int) (location.getLatitude());
	    int lng = (int) (location.getLongitude());
	    latituteField.setText(String.valueOf(lat));
	    longitudeField.setText(String.valueOf(lng));
	  }

	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();

	  }

	  @Override
	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }
	  
	  public void capturePhoto(View view) {
			// Take photo
		    latituteField = (TextView) findViewById(R.id.TextView02);
		    longitudeField = (TextView) findViewById(R.id.TextView04);
		    String latitude = latituteField.getText().toString();
		    String longitude = longitudeField.getText().toString();
		    
			File sdcard = Environment.getExternalStorageDirectory();
			String photoname = sdcard.getAbsolutePath() + File.separator + latitude +"-" + longitude + "-beesphoto.png";
			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			Uri uriSavedImage = Uri.fromFile(new File(photoname));
			takePictureIntent.putExtra("output", uriSavedImage);
			startActivityForResult(takePictureIntent, 1); /* What is RESULT_OK */	
			
		
		}
	  
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	   	Intent email = new Intent(Intent.ACTION_SEND);
	   	
	    latituteField = (TextView) findViewById(R.id.TextView02);
	    longitudeField = (TextView) findViewById(R.id.TextView04);
	    String latitude = latituteField.getText().toString();
	    String longitude = longitudeField.getText().toString();
	    String subject = "Latitude: " + latitude + " Longitude: " + longitude;
	    
		File sdcard = Environment.getExternalStorageDirectory();
		String photoname = sdcard.getAbsolutePath() + File.separator + latitude +"-" + longitude + "-beesphoto.png";
	   	
		email.setType("message/rfc822");
		email.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
		email.putExtra(Intent.EXTRA_TEXT, "This was captured at: " + subject);
		email.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+ photoname));
		startActivity(Intent.createChooser(email, "Choose an Email client :"));
	  }
	  
} 

