package edu.jhu.cs.micharu1.sampledatabase;

/**
 * 
 * @author micharu123
 *
 * Let's go a head and use an entire class for defining our column data to store in the database.
 * For your assignment, you could make a class similar to this that maintains all of the ToDo details...
 * such as todo_name, todo_priority, todo_description, todo_startdate, and todo_enddate -- heck, make
 * them all strings, load them all into an array, and then sort. 
 * 
 */
public class Greeting {

	// This correlates to the database id column defined in MySQLiteHelper.
	private long id;
	// Ditto to the above, execept this is for column greeting.
	private String greeting;

	// I know that I don't have to explain getters and setters ;).
	public long getID() {
		return id;
	}
	
	public void setID(long id) {
		this.id = id;
	}
	
	public String getGreeting() {
		return greeting;
	}
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
