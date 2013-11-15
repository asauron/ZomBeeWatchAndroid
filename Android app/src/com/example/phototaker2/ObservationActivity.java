package com.example.phototaker2;

import java.util.Vector;

import com.example.phototaker2.db.ZomBeeDBOpenHelper;
import com.example.phototaker2.db.ZomBeeDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ObservationActivity extends Activity {

     ZomBeeDataSource datasource;
    private ZomBeeDBOpenHelper database;
    private Long mRowId;
    ListView lv;
    public String dbstuff;
  //  Observation 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_observation);
        datasource = new ZomBeeDataSource(this);
        datasource.open();
        lv = (ListView) findViewById(R.id.list);
        populateList();
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_observation, menu);
        return true;
    }
    

    public void completedObservation(View view) {
		Intent intent = new Intent(this, CompletedObservation.class);
		startActivity(intent);

	}
    
    public void createObservations(View view){
		Intent intent = new Intent(this, Step1.class);
		startActivity(intent);
	}
    
    public void pendingObservations(View view){
		Intent intent = new Intent(this,DisplayDatabase.class);
		startActivity(intent);
	}
    
    private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
    
    public void populateList(){
    	
    	Cursor note = datasource.fetchAllObservations();
        startManagingCursor(note);

        Vector<Observation> vec = new Vector<Observation>();
       
        
        while(note.moveToNext()){

        	
        	Integer nextStep = datasource.getNextStepfromName((note.getString(note.getColumnIndexOrThrow(database.COLUMN_NAME))));
        	//maybe not have this - takes too long to load
        	String imgPath = note.getString(note.getColumnIndexOrThrow(database.COLUMN_IMAGE1));
        	Observation obs = new Observation((note.getString(note.getColumnIndexOrThrow(database.COLUMN_NAME))),nextStep.toString(),imgPath);

        	if(nextStep != 0)
        		vec.add(obs);
        	
        	
        	
        	}
        

        ObservationAdapter adapter = new ObservationAdapter(this, R.layout.observationrow, vec);
        
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			
				
				TextView nextStepTv = (TextView)arg1.findViewById(2);
				TextView sampleNameTv = (TextView)arg1.findViewById(1);
				int nextStep = Integer.valueOf(nextStepTv.getText().toString());
				Log.d("AAAA","next :"+nextStep);
				int observationId = datasource.getObservationIdByName(sampleNameTv.getText().toString());
				if (nextStep == 2){
					
					Toast.makeText(getApplicationContext(),"Step 2 has to be completed", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getApplicationContext(), Step2.class);
					intent.putExtra("obId",observationId);
					intent.putExtra("samplename",sampleNameTv.getText().toString());
					startActivity(intent);
				}
				if (nextStep == 3){
					Toast.makeText(getApplicationContext(),"Step 3 has to be completed", Toast.LENGTH_SHORT);
					Intent intent = new Intent(getApplicationContext(), Step3.class);
					intent.putExtra("obId",observationId);
					intent.putExtra("samplename",sampleNameTv.getText().toString());
					startActivity(intent);
				}
				else
					Toast.makeText(getApplicationContext(), "This sample has been completed and submitted", Toast.LENGTH_SHORT).show();
						
				
			}
			
		});
			
		}
		
    }
    
    
    

