package com.example.phototaker2;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.phototaker2.db.ZomBeeDataSource;
import com.example.phototaker2.model.Zombees;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.provider.CalendarContract.Events;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;



public class Step1 extends Activity implements LocationListener {
	
	public static final String LOGTAG="bees bees bees   ";
	private static final int MY_DATE_DIALOG_ID = 0;

	  private TextView latituteField;
	  private TextView longitudeField;
	  private LocationManager locationManager;
	  private String provider;
	  
	
	ZomBeeDataSource datasource;
	Zombees currentZombee  = new Zombees();
	
 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        datasource = new ZomBeeDataSource(this);
        datasource.open();
        Log.i(LOGTAG,"WHAT");
     // CreateData();
        latituteField = (TextView) findViewById(R.id.lattiudeEdit);
	    longitudeField = (TextView) findViewById(R.id.longitudeEdit);
        

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_step1, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
   
   public void sendFeedback(View button) {  
        // Do click handling here  
    	Log.i(LOGTAG,"name is ");
    	
    	
    	final EditText nameField = (EditText) findViewById(R.id.EditTextName);  
    	String name = nameField.getText().toString();  
    
    	Log.i(LOGTAG,"name is "+name);
    	if(name==null){
    		Toast.makeText(this, "Please enter sample name ", Toast.LENGTH_SHORT);
    	}
    	currentZombee.setTitle(name);
    		
    	
    	
    	final EditText emailField = (EditText) findViewById(R.id.EditTextEmail);  
    	String email = emailField.getText().toString();  
    	
    	if(email==null){
    		Toast.makeText(this, "Please enter number of bees ", Toast.LENGTH_SHORT);
    	}
    	currentZombee.setNumberofbees(email);
    	
    	
    	final EditText feedbackField = (EditText) findViewById(R.id.EditTextFeedbackBody);  
    	String feedback = feedbackField.getText().toString();
    	
    	if(feedback==null){
    		Toast.makeText(this, "Please enter notes ", Toast.LENGTH_SHORT);
    	}
    	
    	currentZombee.setNotes1(feedback);
    	
    	final Spinner feedbackSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);  
    	String feedbackType = feedbackSpinner.getSelectedItem().toString();
    	
//    	if(feedbackType==null){
//    		Toast.makeText(this, "Please enter the method of collection ", Toast.LENGTH_SHORT);
//    	}
    	
    	currentZombee.setMethod(feedbackType);
    	
    Log.i(LOGTAG,"CLose here");
    //	mDbHelper.createNote(name,email);

     Log.i(LOGTAG,"hoto saved as "+currentZombee.getImage1());   
     Toast.makeText(getApplicationContext(), "hoto saved as "+currentZombee.getImage1(),Toast.LENGTH_LONG).show();
     Toast.makeText(getApplicationContext(), "hoto saved on "+currentZombee.getDate1(),Toast.LENGTH_LONG).show();  
     
     currentZombee = datasource.createStep1(currentZombee);
   		
   		//Log.i(LOGTAG,"failed here");
   		Log.i(LOGTAG,"Zombee created with id"+currentZombee.getId());
   		
   		datasource.insertStep1id(currentZombee.getId());

   
    } 
    
	public void takePhoto(View view) {
		Calendar c = Calendar.getInstance(); 
		Integer seconds = c.get(Calendar.SECOND);
		File sdcard = Environment.getExternalStorageDirectory();
		String photoname = sdcard.getAbsolutePath() + File.separator + "beesphoto"+seconds.toString()+".png";
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri uriSavedImage = Uri.fromFile(new File(photoname));
		takePictureIntent.putExtra("output", uriSavedImage);
		startActivityForResult(takePictureIntent, RESULT_OK); /* What is RESULT_OK */
		
		currentZombee.setImage1(photoname);
		
		Log.d("photo path",photoname);
       
	
	}
	
	public void createCalender(View view){
		Intent calIntent = new Intent(Intent.ACTION_EDIT);
		calIntent.setType("vnd.android.cursor.item/event");

		calIntent.putExtra(Events.TITLE, "Time to take the zombee picture!");
		calIntent.putExtra(Events.DESCRIPTION, "Please mention the stage of the pupae development");
		GregorianCalendar calDate = new GregorianCalendar();
		calIntent.putExtra(Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");
		calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
		calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
		     calDate.getTimeInMillis());
		calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
		     calDate.getTimeInMillis());
		startActivity(calIntent);
		
		
	}
	
	public void onDateDialogButtonClick(View v) {
	     showDialog(MY_DATE_DIALOG_ID);
	}

	protected Dialog onCreateDialog(int id) {
		switch(id) {
		case MY_DATE_DIALOG_ID:
		 DatePickerDialog dateDlg = new DatePickerDialog(this,
		         new DatePickerDialog.OnDateSetListener() {
		         public void onDateSet(DatePicker view, int year,
		                                             int monthOfYear, int dayOfMonth)
		         {
		                    Time chosenDate = new Time();
		                    chosenDate.set(dayOfMonth, monthOfYear, year);
		                    long dtDob = chosenDate.toMillis(true);
		                    CharSequence strDate = DateFormat.format("MMMM dd, yyyy", dtDob);
		                    Log.i(LOGTAG,"date will be saved as"+ strDate.toString());
		                   
		                    currentZombee.setDate1(strDate.toString());
		                    Toast.makeText(Step1.this,
		                         "Date picked: " + strDate, Toast.LENGTH_SHORT).show();
		        }}, 2011,0, 1);
		      dateDlg.setMessage("Enter today's date");
		    
		      return dateDlg;
		}
		return null;
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
	  
	  public void onLocationChanged(Location location) {
	    float lat = (float) (location.getLatitude());
	    float lng = (float) (location.getLongitude());
	    latituteField.setText(String.valueOf(lat));
	    currentZombee.setLattitude(lat);
	    longitudeField.setText(String.valueOf(lng));
	    currentZombee.setLongitude(lng);
	  }

	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }

	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();

	  }

	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }
	
    
	

}
