package school.planner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class courseDBAdapter 
{

	// Database fields
	public static final String KEY_ROWID = "_id";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_COURSENUMBER = "courseNumber";
	public static final String KEY_ROOMNUMBER = "roomNumber";
	public static final String KEY_TIME = "time";
	public static final String KEY_INSTRUCTORNAME = "instructorName";
	public static final String KEY_OFFICE = "office";
	public static final String KEY_OFFICEHOURS = "officeHours";
	public static final String KEY_PHONENUMBER = "phoneNumber";
	public static final String KEY_EMAIL = "email";
	private static final String DATABASE_TABLE = "todo";
	private Context context;
	private SQLiteDatabase database;
	private courseDBHelper dbHelper;

	public courseDBAdapter(Context context) {
		this.context = context;
	}

	public courseDBAdapter open() throws SQLException {
		dbHelper = new courseDBHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	
/**
	 * Create a new todo If the todo is successfully created return the new
	 * rowId for that note, otherwise return a -1 to indicate failure.
	 */

	public long createCourse(String category, String courseNumber, String roomNumber, String time, String instructorName, String office, String officeHours, String phoneNumber, String email) {
		ContentValues initialValues = createContentValues(category, courseNumber, roomNumber, time, instructorName, office, officeHours, phoneNumber, email);

		return database.insert(DATABASE_TABLE, null, initialValues);
	}

	
/**
	 * Update the todo
	 */

	public boolean updateCourse(long rowId, String category, String courseNumber, String roomNumber, String time, String instructorName, String office, String officeHours, String phoneNumber, String email) 
	{
		ContentValues updateValues = createContentValues(category, courseNumber, roomNumber, time, instructorName, office, officeHours, phoneNumber, email);

		return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "="
				+ rowId, null) > 0;
	}

	
/**
	 * Deletes todo
	 */

	public boolean deleteCourse(long rowId) {
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	
/**
	 * Return a Cursor over the list of all courses in the database
	 * 
	 * @return Cursor over all notes
	 */

	public Cursor fetchAllCourses() {
		return database.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_CATEGORY, KEY_COURSENUMBER, KEY_ROOMNUMBER, KEY_TIME, KEY_INSTRUCTORNAME, KEY_OFFICE, KEY_OFFICEHOURS, KEY_PHONENUMBER, KEY_EMAIL}, null, null, null,
				null, null);
	}

	
/**
	 * Return a Cursor positioned at the defined todo
	 */

	public Cursor fetchCourse(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_CATEGORY, KEY_COURSENUMBER, KEY_ROOMNUMBER, KEY_TIME, KEY_INSTRUCTORNAME, KEY_OFFICE, KEY_OFFICEHOURS, KEY_PHONENUMBER, KEY_EMAIL}, null, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private ContentValues createContentValues(String category, String courseNumber, String roomNumber, String time, String instructorName, String office, String officeHours, String phoneNumber, String email) {
		ContentValues values = new ContentValues();
		values.put(KEY_CATEGORY, category);
		values.put(KEY_COURSENUMBER, courseNumber);
		values.put(KEY_ROOMNUMBER, roomNumber);
		values.put(KEY_TIME, time);
		values.put(KEY_OFFICE, office);
		values.put(KEY_OFFICEHOURS, officeHours);
		values.put(KEY_PHONENUMBER, phoneNumber);
		values.put(KEY_EMAIL, email);
		
		
		return values;
	}
}