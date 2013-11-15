package com.example.phototaker2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

import com.example.phototaker2.db.ZomBeeDataSource;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ObservationAdapter extends ArrayAdapter<Observation> {
	
	Context context;
	int layoutResourceId;
	List<Observation> data = null;
	ZomBeeDataSource datasource;
	
	//process dialog
	ProgressDialog progressDialog;
	
	
	
	public ObservationAdapter(Context context, int layoutResourceId,
		List<Observation> objects) {
		super(context, layoutResourceId, objects);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = objects;
		datasource = new ZomBeeDataSource(context);
        datasource.open();
	
	}
	
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	      
	        ObservationHolder holder = null;
	        
	        if(row == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	            holder = new ObservationHolder();
	           
	            holder.sampleName = (TextView)row.findViewById(R.id.sampleName);
	            holder.stepName = (TextView)row.findViewById(R.id.stepName);
	            //thinking of not having this maybe
	            holder.imageView = (ImageView)row.findViewById(R.id.observationImage);
	            holder.sampleName.setId(1);
	            holder.stepName.setId(2);
	            
	            row.setTag(holder);
	           
	        }
	        else
	        {
	            holder = (ObservationHolder)row.getTag();
	        }
	        
	       Observation observation = data.get(position);
	        holder.sampleName.setText(observation.sampleName);
	        holder.stepName.setText(observation.StepName);
	        Log.d("image path", observation.imagePath);
	      String filepath = observation.imagePath;
	    

	     final int THUMBNAIL_SIZE = 48;

	    FileInputStream fis;
		try {
			fis = new FileInputStream(filepath);
			Bitmap imageBitmap = BitmapFactory.decodeStream(fis);

		    imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		//this is important - keep this
		holder.imageView.setImageBitmap(imageBitmap);
		
		 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	     
	      return row;
	    }
	    
	    static class ObservationHolder
	    {
	     
	        TextView sampleName;
	        TextView stepName;
	        ImageView imageView;
	    }
	    
	  
    
    		   
    
	

}
