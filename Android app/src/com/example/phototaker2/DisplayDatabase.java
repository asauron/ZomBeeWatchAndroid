package com.example.phototaker2;

import java.util.List;

import com.example.phototaker2.db.ZomBeeDBOpenHelper;
import com.example.phototaker2.db.ZomBeeDataSource;
import com.example.phototaker2.model.Zombees;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class DisplayDatabase extends Activity {
	
	ZomBeeDataSource datasource;
	
	private EditText beesnumber;
    private EditText samplename;
    private EditText date;
    private EditText method;
    private EditText pupae;
    private EditText flies;
    private ZomBeeDBOpenHelper mDbHelper;
    private Long mRowId;
    
    private static final String LOGTAG = "display database";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_database);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        datasource = new ZomBeeDataSource(this);
		datasource.open();
		
		beesnumber = (EditText) findViewById(R.id.beesnumber);
		samplename = (EditText) findViewById(R.id.sampleName);
		date = (EditText) findViewById(R.id.date1);
		method = (EditText) findViewById(R.id.method);
		pupae = (EditText) findViewById(R.id.pupae);
		flies = (EditText) findViewById(R.id.flies);
		
		
		 Button confirmButton = (Button) findViewById(R.id.confirm);

		
		mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(mDbHelper.COLUMN_ID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(mDbHelper.COLUMN_ID)
									: null;
		}

		populateFields();

        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }

        });
		

    }

    @SuppressWarnings("deprecation")
	private void populateFields() {
    	int sampleno = 3;
    	 mRowId = (long)sampleno;
        if (mRowId != null) {
            Cursor note = datasource.fetchStep1Note(mRowId);
            startManagingCursor(note);
            beesnumber.setText(note.getString(
                    note.getColumnIndexOrThrow(mDbHelper.COLUMN_NUMBERBEES)));
            Log.i(LOGTAG,"BEES NUMBER IS"+ mDbHelper.COLUMN_NUMBERBEES);
            samplename.setText(note.getString(
                    note.getColumnIndexOrThrow(mDbHelper.COLUMN_NAME)));
            Log.i(LOGTAG,"BEES Name IS"+ mDbHelper.COLUMN_NAME);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(mDbHelper.COLUMN_ID, mRowId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }

    private void saveState() {
        String title = beesnumber.getText().toString();
        String body = samplename.getText().toString();

//        if (mRowId == null) {
//            long id = mDbHelper.createNote(title, body);
//            if (id > 0) {
//                mRowId = id;
//            }
//        } 
//        //else {
//          //  mDbHelper.updateNote(mRowId, title, body);
        }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_database, menu);
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
    
    
    

}
