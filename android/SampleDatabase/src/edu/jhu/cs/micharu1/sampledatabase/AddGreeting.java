package edu.jhu.cs.micharu1.sampledatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddGreeting extends Activity {

	public static GreetingDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_greeting);

		// DATABASE SPECIFIC: open up your database right here.
		datasource = new GreetingDataSource(this);
		datasource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_greeting, menu);
		return true;
	}
	
	public void saveGreeting(View view) {
		
		// Save the entered text to the database. 
		EditText greetingText = (EditText) findViewById(R.id.enter_greeting);
		String greeting = greetingText.getText().toString();
		
		// YES - write the greeting to the database.
	    datasource.createGreeting(greeting);
		
		// Ah, return back to the other activity.
		Intent homeIntent = new Intent(this, MainActivity.class);
		
		// I'd like for back to be cleared.
		homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(homeIntent);
		
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
