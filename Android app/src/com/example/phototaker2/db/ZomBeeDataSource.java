package com.example.phototaker2.db;

import java.util.ArrayList;
import java.util.List;

import com.example.phototaker2.model.Zombees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Columns;
import android.util.Log;

public class ZomBeeDataSource {

   public static final String LOGTAG="Zombee Watch data source method ";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	private static final String[] allColumnsStep1 = {
		ZomBeeDBOpenHelper.COLUMN_DATE1,
		ZomBeeDBOpenHelper.COLUMN_IMAGE1,
		ZomBeeDBOpenHelper.COLUMN_ID,
		ZomBeeDBOpenHelper.COLUMN_LATTITUDE,
		ZomBeeDBOpenHelper.COLUMN_LONGITUDE,
		ZomBeeDBOpenHelper.COLUMN_METHOD,
		ZomBeeDBOpenHelper.COLUMN_NAME,
		ZomBeeDBOpenHelper.COLUMN_NOTES1,
		ZomBeeDBOpenHelper.COLUMN_NUMBERBEES,
		
		};
	
	private static final String[] allColumnsStep2 = {
		
		ZomBeeDBOpenHelper.COLUMN_DATE2,
		ZomBeeDBOpenHelper.COLUMN_IMAGE2,
		ZomBeeDBOpenHelper.COLUMN_ID,
	    ZomBeeDBOpenHelper.COLUMN_NOTES2,
		ZomBeeDBOpenHelper.COLUMN_PUPAE
		};
	
	private static final String[] allColumnsStep3 = {
	
		ZomBeeDBOpenHelper.COLUMN_DATE3,
		ZomBeeDBOpenHelper.COLUMN_IMAGE3,
		ZomBeeDBOpenHelper.COLUMN_ID,
		ZomBeeDBOpenHelper.COLUMN_NOTES3,
		ZomBeeDBOpenHelper.COLUMN_FLIES
		};
	
	private static final String[] allColumnsObservations = {
		
		
		
		ZomBeeDBOpenHelper.COLUMN_ID,
		ZomBeeDBOpenHelper.COLUMN_STEP1_ID,
		ZomBeeDBOpenHelper.COLUMN_STEP2_ID,
		ZomBeeDBOpenHelper.COLUMN_STEP3_ID
		};
	
	
	public ZomBeeDataSource(Context context){
		dbhelper = new ZomBeeDBOpenHelper(context);
	}
	
	public void open() {
		Log.i(LOGTAG, "Database opened");
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		Log.i(LOGTAG, "Database closed");		
		dbhelper.close();
	}
	
	public Zombees createStep1(Zombees zombee){
		Log.i(LOGTAG, "Create got called");
		ContentValues values = new ContentValues();
		values.put(ZomBeeDBOpenHelper.COLUMN_NAME, zombee.getTitle());
		values.put(ZomBeeDBOpenHelper.COLUMN_NUMBERBEES, zombee.getNumberofbees());
		values.put(ZomBeeDBOpenHelper.COLUMN_METHOD, zombee.getMethod());
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE1, zombee.getImage1());
		//values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE2, zombee.getImage2());
		//values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE3, zombee.getImage3());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES1, zombee.getNotes1());
		//values.put(ZomBeeDBOpenHelper.COLUMN_NOTES2, zombee.getNotes2());
		//values.put(ZomBeeDBOpenHelper.COLUMN_NOTES3, zombee.getNotes3());
		values.put(ZomBeeDBOpenHelper.COLUMN_LATTITUDE, zombee.getLattitude());
		values.put(ZomBeeDBOpenHelper.COLUMN_LONGITUDE, zombee.getLongitude());
		//values.put(ZomBeeDBOpenHelper.COLUMN_PUPAE, zombee.getPuape());
		//values.put(ZomBeeDBOpenHelper.COLUMN_FLIES, zombee.getFlies());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE1, zombee.getDate1());
		//values.put(ZomBeeDBOpenHelper.COLUMN_DATE2, zombee.getDate2());
		//values.put(ZomBeeDBOpenHelper.COLUMN_DATE3, zombee.getDate3());
		long insertid = database.insert(ZomBeeDBOpenHelper.TABLE_Zombees_Step1, null, values);
		zombee.setId(insertid);
		return zombee;
	}
	
	public Zombees createStep2(Zombees zombee){
		Log.i(LOGTAG, "Create step 2 got called");
		ContentValues values = new ContentValues();
		
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE2, zombee.getImage2());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES2, zombee.getNotes2());
	    values.put(ZomBeeDBOpenHelper.COLUMN_PUPAE, zombee.getPuape());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE2, zombee.getDate2());

		long insertid = database.insert(ZomBeeDBOpenHelper.TABLE_Zombees_Step2, null, values);
		zombee.setId(insertid);
		return zombee;
	}
	
	public Zombees createStep3(Zombees zombee){
		Log.i(LOGTAG, "Create  step 3 got called");
		ContentValues values = new ContentValues();
		values.put(ZomBeeDBOpenHelper.COLUMN_IMAGE3, zombee.getImage3());
		values.put(ZomBeeDBOpenHelper.COLUMN_NOTES3, zombee.getNotes3());
		values.put(ZomBeeDBOpenHelper.COLUMN_FLIES, zombee.getFlies());
		values.put(ZomBeeDBOpenHelper.COLUMN_DATE3, zombee.getDate3());
		long insertid = database.insert(ZomBeeDBOpenHelper.TABLE_Zombees_Step3, null, values);
		zombee.setId(insertid);
		return zombee;
	}
	

	
	public void insertStep1id(long id){
		Log.i(LOGTAG, "Create - step 1 id insert got called");
		ContentValues values = new ContentValues();
		values.put(ZomBeeDBOpenHelper.COLUMN_STEP1_ID, id);
		long insertid = database.insert(ZomBeeDBOpenHelper.TABLE_columnids, null, values);
		
	}
	
	public void insertStep2id(long insertid,long step2id){
		Log.i(LOGTAG, "Create - step 2 id insert got called");
		ContentValues values = new ContentValues();
		values.put(ZomBeeDBOpenHelper.COLUMN_STEP2_ID, step2id);
		 database.update(ZomBeeDBOpenHelper.TABLE_columnids, values, ZomBeeDBOpenHelper.COLUMN_ID + "=" + insertid, null);
	}
	
	public void insertStep3id(long insertid,long step3id){
		Log.i(LOGTAG, "Create - step 3 id insert got called");
		ContentValues values = new ContentValues();
		values.put(ZomBeeDBOpenHelper.COLUMN_STEP3_ID, step3id);
		 database.update(ZomBeeDBOpenHelper.TABLE_columnids, values, ZomBeeDBOpenHelper.COLUMN_ID + "=" + insertid, null);
	}

	
	public Cursor fetchAllNotesStep1() {

        return database.query(ZomBeeDBOpenHelper.TABLE_Zombees_Step1, allColumnsStep1 , null, null, null, null, null);
    }
	
	 public boolean deleteStep1Row(long rowId) {

	        return database.delete(ZomBeeDBOpenHelper.TABLE_Zombees_Step1, ZomBeeDBOpenHelper.COLUMN_ID + "=" + rowId, null) > 0;
	    }

	   

	    public Cursor fetchStep1Note(long rowId) throws SQLException {

	        Cursor mCursor =

	            database.query(true, ZomBeeDBOpenHelper.TABLE_Zombees_Step1, allColumnsStep1 , ZomBeeDBOpenHelper.COLUMN_ID + "=" + rowId, null,
	                    null, null, null, null);
	      //  if (mCursor != null) {
	        //    mCursor.moveToFirst();
	        //}
	        return mCursor;

	    }

	    
	    public Cursor fetchAllNotesStep2() {

	        return database.query(ZomBeeDBOpenHelper.TABLE_Zombees_Step2, allColumnsStep2 , null, null, null, null, null);
	    }
		
		 public boolean deleteStep2Row(long rowId) {

		        return database.delete(ZomBeeDBOpenHelper.TABLE_Zombees_Step2, ZomBeeDBOpenHelper.COLUMN_ID + "=" + rowId, null) > 0;
		    }

		   

		    public Cursor fetchStep2Note(long rowId) throws SQLException {

		        Cursor mCursor = database.query(true, ZomBeeDBOpenHelper.TABLE_Zombees_Step2, allColumnsStep2 , ZomBeeDBOpenHelper.COLUMN_ID + "=" + rowId, null,
		                    null, null, null, null);
		     
		      
		        return mCursor;

		    }
		    
		    public Cursor fetchAllNotesStep3() {

		        return database.query(ZomBeeDBOpenHelper.TABLE_Zombees_Step3, allColumnsStep3 , null, null, null, null, null);
		    }
			
			 public boolean deleteStep3Row(long rowId) {

			        return database.delete(ZomBeeDBOpenHelper.TABLE_Zombees_Step3, ZomBeeDBOpenHelper.COLUMN_ID + "=" + rowId, null) > 0;
			    }

			   

			    public Cursor fetchStep3Note(long rowId) throws SQLException {

			        Cursor mCursor =

			            database.query(true, ZomBeeDBOpenHelper.TABLE_Zombees_Step3, allColumnsStep3 , ZomBeeDBOpenHelper.COLUMN_ID + "=" + rowId, null,
			                    null, null, null, null);
			        if (mCursor != null) {
			            mCursor.moveToFirst();
			        }
			        return mCursor;

			    }
			    
			    public Cursor fetchAllObservations() {

			        //return database.query(ZomBeeDBOpenHelper.TABLE_columnids, allColumnsObservations , null, null, null, null, null);
			    	return database.rawQuery("SELECT * FROM "+ ZomBeeDBOpenHelper.TABLE_columnids + " a JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step1+" b ON a."+ZomBeeDBOpenHelper.COLUMN_STEP1_ID+" =  b."+ZomBeeDBOpenHelper.COLUMN_ID, null);
			    }
			    
			    public Cursor fetchAllActiveObservations() {
			    	String query = "SELECT * FROM "+ ZomBeeDBOpenHelper.TABLE_columnids + " a JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step1+" b JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step2+" c JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step3+" d ON a."+ZomBeeDBOpenHelper.COLUMN_STEP1_ID+" =  b."+ZomBeeDBOpenHelper.COLUMN_ID+" AND a."+ZomBeeDBOpenHelper.COLUMN_STEP2_ID+" = c."+ZomBeeDBOpenHelper.COLUMN_ID+" AND a."+ZomBeeDBOpenHelper.COLUMN_STEP3_ID+" = d."+ZomBeeDBOpenHelper.COLUMN_ID;//+" WHERE d."+ZomBeeDBOpenHelper.COLUMN_ID+" IS NULL";
					Log.d("BELLA",query);
			        //return database.query(ZomBeeDBOpenHelper.TABLE_columnids, allColumnsObservations , null, null, null, null, null);
			    	return database.rawQuery(query, null);
			    }
			    
			    
			public int getNextStepfromName(String name){
				Cursor c = database.rawQuery("SELECT * FROM "+ ZomBeeDBOpenHelper.TABLE_columnids + " a JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step1+" b ON a."+ZomBeeDBOpenHelper.COLUMN_STEP1_ID+" =  b."+ZomBeeDBOpenHelper.COLUMN_ID+" WHERE "+ZomBeeDBOpenHelper.COLUMN_NAME+" = ?", new String[]{name});
				while (c.moveToNext())
				{
					Log.d("AAA","Step 1 id"+c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP1_ID)));
					Log.d("AAA","Step 2 id"+c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID)));
					Log.d("AAA","Step 3 id"+c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP3_ID)));
					if(c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID)) == null)
						return 2;
					else if (c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP3_ID)) == null)
						return 3;
				}
				return 0;
			}
			
			public int getPreviousStep1fromName(String name){
				Cursor c = database.rawQuery("SELECT * FROM "+ ZomBeeDBOpenHelper.TABLE_columnids + " a JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step1+" b ON a."+ZomBeeDBOpenHelper.COLUMN_STEP1_ID+" =  b."+ZomBeeDBOpenHelper.COLUMN_ID+" WHERE "+ZomBeeDBOpenHelper.COLUMN_NAME+" = ?", new String[]{name});
				while (c.moveToNext())
				{
					Log.d("AAA","Step1 id "+c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP1_ID)));
					int id = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP1_ID)));
				    Log.d("AAA","returning "+id);
					return id;
				}
				return 0;
			}
			
			public int getPreviousStep2fromName(String name){
				Cursor c = database.rawQuery("SELECT * FROM "+ ZomBeeDBOpenHelper.TABLE_columnids + " a JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step1+" b ON a."+ZomBeeDBOpenHelper.COLUMN_STEP1_ID+" =  b."+ZomBeeDBOpenHelper.COLUMN_ID+" WHERE "+ZomBeeDBOpenHelper.COLUMN_NAME+" = ?", new String[]{name});
				while (c.moveToNext())
				{
					Log.d("AAA","Step2 id "+c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID)));
					int id = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID)));
				    Log.d("AAA","returning "+id);
					return id;
				}
				return 0;
			}

			public int getObservationIdByName(String name){
				Cursor c = database.rawQuery("SELECT * FROM "+ ZomBeeDBOpenHelper.TABLE_columnids + " a JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step1+" b ON a."+ZomBeeDBOpenHelper.COLUMN_STEP1_ID+" =  b."+ZomBeeDBOpenHelper.COLUMN_ID+" WHERE "+ZomBeeDBOpenHelper.COLUMN_NAME+" = ?", new String[]{name});
				if (c.moveToNext())
					return c.getInt(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_ID));
				return 0;

			}

			public Cursor getDataForSubmission(int obsId){
				String query = "SELECT * FROM "+ ZomBeeDBOpenHelper.TABLE_columnids + " a JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step1+" b JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step2+" c JOIN "+ZomBeeDBOpenHelper.TABLE_Zombees_Step3+" d ON a."+ZomBeeDBOpenHelper.COLUMN_STEP1_ID+" =  b."+ZomBeeDBOpenHelper.COLUMN_ID+" AND a."+ZomBeeDBOpenHelper.COLUMN_STEP2_ID+" = c."+ZomBeeDBOpenHelper.COLUMN_ID+" AND a."+ZomBeeDBOpenHelper.COLUMN_STEP3_ID+" = d."+ZomBeeDBOpenHelper.COLUMN_ID+" WHERE a."+ZomBeeDBOpenHelper.COLUMN_ID+" = ?";
				Log.d("QUERY",query);
				Cursor c = database.rawQuery(query, new String[]{Integer.toString(obsId)});
				//if (c.moveToNext()){
					
				
//					Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_NAME)));
//					Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID)));
//					c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID));
//					Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_LATTITUDE)));
//					c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID));
//					Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_PUPAE)));
//					c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID));
//					Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_FLIES)));
//					c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_STEP2_ID));
//					Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_IMAGE1)));
//					Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_DATE2)));
//					Log.i("Submitting  ",c.getString(c.getColumnIndexOrThrow(ZomBeeDBOpenHelper.COLUMN_IMAGE3)));
				//}
				
				return c;
			}
			
			
			
	    
	    
	
}
