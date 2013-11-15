package com.example.phototaker2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ZomBeeDBOpenHelper extends SQLiteOpenHelper {

	
	private static final String LOGTAG = "ZomBee Watch is here";

	private static final String DATABASE_NAME = "ZomBeeWatch.db";
	//updated database version after adding multiple tables to the db
	private static final int DATABASE_VERSION = 6; 
	
	public static final String TABLE_Zombees_Step1 = "Zombees1";
	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_NAME = "title"; //sample name
	public static final String COLUMN_NUMBERBEES = "numberofbees";
	public static final String COLUMN_METHOD = "method";
	public static final String COLUMN_IMAGE1 = "image1";
	public static final String COLUMN_NOTES1 = "notes1";
	public static final String COLUMN_LATTITUDE = "lattitude";
	public static final String COLUMN_LONGITUDE = "longitude";
	public static final String COLUMN_DATE1 = "date1";
	
	public static final String TABLE_Zombees_Step2 = "Zombees2";
	//not sure if i need this
	//public static final String COLUMN_STEP2_ID = "Id";
	public static final String COLUMN_IMAGE2 = "image2";
    public static final String COLUMN_NOTES2 = "notes2";
	public static final String COLUMN_PUPAE = "pupae";
	public static final String COLUMN_DATE2 = "date2";
	
	public static final String TABLE_Zombees_Step3 = "Zombees3";
	//not sure if i need this
	//public static final String COLUMN_STEP3_ID = "Id";
	public static final String COLUMN_IMAGE3 = "image3";
	public static final String COLUMN_NOTES3 = "notes3";
	public static final String COLUMN_FLIES = "flies";
    public static final String COLUMN_DATE3 = "date3";
    
    
    public static final String TABLE_columnids = "columnids";
    public static final String COLUMN_STEP1_ID = "step1id";
    public static final String COLUMN_STEP2_ID = "step2id";
    public static final String COLUMN_STEP3_ID = "step3id";
    
	
	private static final String TABLE_CREATE_STEP1 = 
			"CREATE TABLE " + TABLE_Zombees_Step1 + " (" +
			COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			COLUMN_NAME + " TEXT, " +
			COLUMN_NUMBERBEES + " NUMERIC, " +
			COLUMN_METHOD + " TEXT, " +
			COLUMN_IMAGE1 + " TEXT, " +
			COLUMN_NOTES1 + " TEXT, " +
		    COLUMN_LATTITUDE + " NUMERIC, " +
			COLUMN_LONGITUDE + " NUMERIC, " +
		    COLUMN_DATE1 + " TEXT " +
			")";
	
	
	private static final String TABLE_CREATE_STEP2 = 
			"CREATE TABLE " + TABLE_Zombees_Step2 + " (" +
					COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COLUMN_IMAGE2 + " TEXT, " +
			        COLUMN_NOTES2 + " TEXT, " +
				    COLUMN_PUPAE + " NUMERIC, " +
				    COLUMN_DATE2 + " TEXT " +
				    ")";
	
	private static final String TABLE_CREATE_STEP3 = 
			"CREATE TABLE " + TABLE_Zombees_Step3 + " (" +
					COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COLUMN_IMAGE3 + " TEXT, " +
			        COLUMN_NOTES3 + " TEXT, " +
				    COLUMN_FLIES + " NUMERIC, " +
				    COLUMN_DATE3 + " TEXT " +
				    ")";
	
	private static final String TABLE_CREATE_OBS = 
			"CREATE TABLE " + TABLE_columnids + " (" +
					COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COLUMN_STEP1_ID + " INTEGER, " +
					COLUMN_STEP2_ID + " INTEGER, " +
					COLUMN_STEP3_ID + " INTEGER, " +
					" FOREIGN KEY ("+COLUMN_STEP1_ID+") REFERENCES "+TABLE_Zombees_Step1+" ("+COLUMN_ID+"), "
					+" FOREIGN KEY ("+COLUMN_STEP2_ID+") REFERENCES "+TABLE_Zombees_Step2+" ("+COLUMN_ID+"), "
					+" FOREIGN KEY ("+COLUMN_STEP3_ID+") REFERENCES "+TABLE_Zombees_Step3+" ("+COLUMN_ID+") )";
							
					
	
	
	
	
	public ZomBeeDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(LOGTAG,"in on create method");
		db.execSQL(TABLE_CREATE_STEP1);
		Log.i(LOGTAG,"Table1 has been created");
		db.execSQL(TABLE_CREATE_STEP2);
		Log.i(LOGTAG,"Table2 has been created");
		db.execSQL(TABLE_CREATE_STEP3);
		Log.i(LOGTAG,"Table3 has been created");
		db.execSQL(TABLE_CREATE_OBS);
		Log.i(LOGTAG,"Table4 has been created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i(LOGTAG, "in on upgrade method ");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Zombees_Step1);
		Log.i(LOGTAG,"After step 1");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Zombees_Step2);
		Log.i(LOGTAG,"After step 2");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Zombees_Step3);
		Log.i(LOGTAG,"After step 3");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_columnids);
		onCreate(db);
		
		
		Log.i(LOGTAG, "database has been upgraded from " +oldVersion + "to" + newVersion);
	}

}
