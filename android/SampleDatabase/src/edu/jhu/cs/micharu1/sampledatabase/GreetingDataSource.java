package edu.jhu.cs.micharu1.sampledatabase;

import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @author micharu123
 * 
 * This class will provide data manipulation language, or simply: insertion, deletion, and selection.
 * We will also expose open and close public methods which are handled by the MySQLiteHelper.
 * 
 */
public class GreetingDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper sqliteHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_GREETING };
	
	// Constructor. Essentially, what we want to do is set our private helper to the context provided
	// by the activity. This will allow us to act accordingly (open the database, create it, query it).
	public GreetingDataSource(Context context){
		this.sqliteHelper = new MySQLiteHelper(context);
	}

	// Lets open a database to work with!
	public void open() throws SQLException {
		// getWritableDatabase - creates or opens a database for writing.
		this.database = this.sqliteHelper.getWritableDatabase();
	}
	
	// Never leave a bunch of databases open, you're device's memory will thank you.
	public void close() {
		// You should always close what you open. Close was inherited from SQLiteOpenHelper.
		// In fact, anything that you don't remember from the MySQLiteHelper class was inherited ;).
		this.sqliteHelper.close();
	}
	
	// If you think about your todo project, you could just do a createTODO and write everything about a certain ToDO all at once.
	public void createGreeting(String greeting) {
		// We have to use ContentValues because that's what the database.insert expects!
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_GREETING, greeting);

		// Our ID should auto increment, so no worries there.
		this.database.insert(MySQLiteHelper.TABLENAME, MySQLiteHelper.COLUMN_GREETING, values);
	}
	
	public Greeting getRandomGreeting() {
		// Query the table returning a cursor containing the results.
		// If you're curious of the parameters:
		//                             TABLENAME     allColumns        null=*             null=no args        null=no grouping... you get the rest.
		//    public Cursor query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
		Cursor cursor = database.query(MySQLiteHelper.TABLENAME, allColumns, null, null, null, null, null);
		Greeting newGreeting = new Greeting();
		
		// Since my app is pretty dumb, I'm just going to use getCount to return a count of all the rows, and then set that to max so I can choose a
		// random number between 1 and MAX.
		int max = cursor.getCount();
		Random generator = new Random();
		
		// Our _ids start iterate 1..n.
		String choice = String.valueOf(generator.nextInt(max)+1);
		
		// Okay, lets specify some arguments this time around.
		cursor = database.query(MySQLiteHelper.TABLENAME, allColumns, MySQLiteHelper.COLUMN_ID+" = "+choice, null, null, null, null);
		
		// If you don't do this, you're cursor will blow everything up. This method will point to the first row of returned data.
		// Having not done this the first few times, you'll catch an error like: 
		//    03-03 16:35:37.968: E/AndroidRuntime(846): 	at android.database.AbstractCursor.checkPosition(AbstractCursor.java:580)
		//    03-03 16:35:37.968: E/AndroidRuntime(846): 	at android.database.AbstractWindowedCursor.checkPosition(AbstractWindowedCursor.java:214)
		//    03-03 16:35:37.968: E/AndroidRuntime(846): 	at android.database.AbstractWindowedCursor.getString(AbstractWindowedCursor.java:41)
		cursor.moveToFirst();
		
		// We want the returned value as a greeting so we can return it.
		newGreeting.setGreeting(cursor.getString(1).toString());
		
		// Our cursor has done it's work... close it.
		cursor.close();
				
		return newGreeting;
	}

	
}
