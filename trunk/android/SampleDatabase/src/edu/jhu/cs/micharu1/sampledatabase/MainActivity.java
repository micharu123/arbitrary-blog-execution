package edu.jhu.cs.micharu1.sampledatabase;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * 
 * @author micharu123
 *
 * This is the main activity, so the first thing you see when launching the app.
 * If you read through the code, you'll notice that we open our data source right
 * away, we check if it has any data, if it does we will bash over the android:hint
 * for the TextView with some text from the database. 
 * 
 * We manage two buttons exclusively. An edit and refresh. The add button throws
 * an intent to start the activity for adding a greeting. The refresh restarts
 * the current activity (this is very hacky) to display a new randomized greeting. 
 * 
 */
public class MainActivity extends Activity {

	// DATE SPECIFIC: This should probably be private... but it works for now. Also note that I have two database connections... one open here
	// and one that I'll open in the AddGreeting activity. However, I will always close that database when leaving AddGreeting.
	public static GreetingDataSource datasource;
	
	public final static String EDIT_MESSAGE_KEY = "edu.jhu.cs.micharu1.SampleDatabase.EditMessage";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// DATABASE SPECIFIC: open up your database right here.
		datasource = new GreetingDataSource(this);
		datasource.open();

		// Useful, this will stop our activity from crashing on launch.
		try {
			TextView ourgreeting = (TextView) findViewById(R.id.greeting_message);
			
			// Yeah, I agree this is really confusing. What I've done is returned a greeting from datasource, and
			// then returned a greeting string from that greeting object.
			ourgreeting.setText(datasource.getRandomGreeting().getGreeting());
		} catch (IllegalArgumentException e) {
			System.err.println("Caught IllegalArgumentException: " + e.getMessage());
		}
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			// Using the actionbar...
			ActionBar actionBar = getActionBar();
			actionBar.setHomeButtonEnabled(false);
		}
	}

	// If you're curious as to how the actionbar works, I recommend taking a look at sample_database_menu.xml,
	// and the next two methods below.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sample_database_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case R.id.menu_add:
	    		Intent addItent = new Intent(this, AddGreeting.class);
	    		startActivity(addItent);
	    		break;
	    	default:
	    		break;
	    }
	    return true;
	  } 

	// Really cool eclipse fact: I changed the method name here from editGreeting to addGreeting because it
	// was more descriptive. It modified the activity_main.xml onclick method string for me automatically!
	public void addGreeting(View view) {
		Intent addIntent = new Intent(this, AddGreeting.class);
		startActivity(addIntent);
	}
	
	// This is really just so I can grab a new random greeting... we could use something like super.recreate() if we were targeting a higher
	// API. I don't really recommend doing it this way, but it works and we can see our greeting randomizer is working.
	@SuppressLint("NewApi")
	public void refreshActivity(View view) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			super.recreate();
		} else {
			finish();
			startActivity(getIntent());
		}
	}
	
	// DATABASE SPECIFIC STUFF RIGHT HERE. For onResume and onPause handle closing and opening the database.
	// note that we only have ONE instance of the database open ever... not like 20 or 30 of them.
	protected void onResume() {
		datasource.open();
		super.onResume();
	}
	
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
	
	protected void onStop() {
		datasource.close();
		super.onStop();
	}
}
