package edu.jhu.cs.micharu1.sampledatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author micharu123
 * For the sake of brevity in this example, I wanted to do less OOP and more databases. However, I think
 * by doing the separation of DDL and DML (defined below), it will help you more in the long run of your app.
 * 
 * This class contains a set of methods for creating the database and upgrading it if need be. We will extend
 * the SQLiteOpenHelper which means we have to provide concrete implementations for both onCreate and onUpgrade
 * methods.
 * 
 * If I had time, I would rename this GreetingSQLiteHelper. That'd be more useful. Expect more arguing with
 * myself throughout the code comments.
 *
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
	
	// Let's define some generic parameters that define our database.
	
	// Database name.
	public static final String DATABASE_NAME = "sample_database.db";
	
	// Database version, because we might have more than one and thus want to drop out the older version.
	public static final int DATABASE_VERSION = 1;

	// Table name to hold our greetings.
	public static final String TABLENAME = "greetings";
	
	// A column for maintaining an id... this will be store an integer per row.
	public static final String COLUMN_ID = "_id";
	
	// This column will store our greeting that we will willy-nilly select at random for display on MainActivity.
	public static final String COLUMN_GREETING = "greeting";
	
	// We will need to define the string that represents our data definition language (creating the tables).
	// Also mind your spaces... or it will give you an AndroidRunTime error similar to: syntax error: create tables [blah].
	private static final String DATABASE_CREATE = "create table "
			+ TABLENAME + "(" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_GREETING + " text not null);";
	
	// Useful for when we update, dropping the old database if it exists.
	private static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + TABLENAME;
	/* 
	 * REFERENCE THE ABOVE STRING: 
	 * If you were to print that string to standard out, it'd look something like:
	 * create table greetings (_id integer primary key autoincrement, greeting text not null);
	 * 
	 * What does this mean? Well, it means you now have an id that is an integery, it's your primary key so its
	 * always unique and you can index it directly, and it automatically increments per insertion of new data.
	 * Our greeting is set to not null, because we do not want an empty greeting inserted!
	 * 
	 */
	
	
	// Our constructor with a content context object.
	public MySQLiteHelper(Context context) {
		// Create a database file (*.db) with our defined name and version.
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Abstract method from SQLiteOpenHelper.
	// On the creation of a SQLiteDatabase, we will want to execute our data definition language for table
	// creation as defined above. Simply, make the greeting table.
	@Override
	public void onCreate(SQLiteDatabase database) {
		// Execute a single sql statement that will not return data (e.g., a tuple from a select statement)
		database.execSQL(DATABASE_CREATE);
	}

	// Another Abstract method from SQLiteOpenHelper.
	// When upgrading the database, a comparison between two version numbers, we have to define what to do.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DATABASE_DROP);
		// We dropped it... lets recreate the new version.
		onCreate(db);
	}
}
