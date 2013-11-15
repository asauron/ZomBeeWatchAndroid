package com.example.phototaker2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		// Show the Up button in the action bar.
		setupActionBar();
		
		 TextView t2 = (TextView) findViewById(R.id.aboutme);
		 t2.setMovementMethod(LinkMovementMethod.getInstance());
		 
		 addLink(t2, "www.zombeewatch.org", "www.zombeewatch.org");
		 addLink(t2,"abinaya@mail.sfsu.edu","www.gmail.com");
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static void addLink(TextView textView, String patternToMatch,
	        final String link) {
	    Linkify.TransformFilter filter = new Linkify.TransformFilter() {
	        @Override public String transformUrl(Matcher match, String url) {
	            return link;
	        }
	    };
	    Linkify.addLinks(textView, Pattern.compile(patternToMatch), null, null,
	            filter);
	}

}
