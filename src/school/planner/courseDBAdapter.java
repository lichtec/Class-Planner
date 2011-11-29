package school.planner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class courseDBAdapter
{

private static final String DATABASE_NAME = "data";
private static final String DATABASE_TABLE = "courses";
private static final int DATABASE_VERSION = 3;


public static final String KEY_CATEGORY = "category";
public static final String KEY_COURSENUMBER = "courseNumber";
public static final String KEY_ROOMNUMBER = "roomNumber";
public static final String KEY_TIME = "time";
public static final String KEY_INSTRUCTORNAME = "instructorName";
public static final String KEY_OFFICE = "office";
public static final String KEY_OFFICEHOURS = "officeHours";
public static final String KEY_PHONENUMBER = "phoneNumber";
public static final String KEY_EMAIL = "email";

public static final String KEY_ROWID = "_id";
private static final String TAG = "CourseDBAdapter";

private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE +" (" + KEY_ROWID + " integer primary key autoincrement, " + KEY_CATEGORY + " text not null, " + KEY_COURSENUMBER + " text not null, " + KEY_ROOMNUMBER + " text not null, " + KEY_TIME + " text not null, " + KEY_INSTRUCTORNAME + " text not null, " + KEY_OFFICE + " text not null, " + KEY_OFFICEHOURS + " text not null, " + KEY_PHONENUMBER + " text not null, " + KEY_EMAIL + " text not null);";

private final Context context;
private SQLiteDatabase database;
private CourseDBHelper dbHelper;

private static class CourseDBHelper extends SQLiteOpenHelper
{
CourseDBHelper(Context context)
{
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db)
{
db.execSQL(DATABASE_CREATE);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
{
Log.w(TAG, "Upgrading database from version " + oldVersion + "to " + newVersion + ", which will destroy all old data");
db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
onCreate(db);
}
}

public courseDBAdapter(Context context)
{
this.context = context;
}

public courseDBAdapter open() throws SQLException
{
dbHelper = new CourseDBHelper(context);
database = dbHelper.getWritableDatabase();
return this;
}

public void close()
{
dbHelper.close();
}



/**
* Create a new todo If the todo is successfully created return the new
* rowId for that note, otherwise return a -1 to indicate failure.
*/

public long createCourse(String category, String courseNumber, String roomNumber, String time, String instructorName, String office, String officeHours, String phoneNumber, String email)
{
ContentValues initialValues = new ContentValues();
initialValues.put(KEY_CATEGORY, category);
initialValues.put(KEY_COURSENUMBER, courseNumber);
initialValues.put(KEY_ROOMNUMBER, roomNumber);
initialValues.put(KEY_TIME, time);
initialValues.put(KEY_INSTRUCTORNAME, instructorName);
initialValues.put(KEY_OFFICE, office);
initialValues.put(KEY_OFFICEHOURS, officeHours);
initialValues.put(KEY_PHONENUMBER, phoneNumber);
initialValues.put(KEY_EMAIL, email);

return database.insert(DATABASE_TABLE, null, initialValues);
}


/**
* Update the todo
*/

public boolean updateCourse(long rowId, String category, String courseNumber, String roomNumber, String time, String instructorName, String office, String officeHours, String phoneNumber, String email)
{
ContentValues args = new ContentValues();
args.put(KEY_CATEGORY, category);
args.put(KEY_COURSENUMBER, courseNumber);
args.put(KEY_ROOMNUMBER, roomNumber);
args.put(KEY_TIME, time);
args.put(KEY_INSTRUCTORNAME, instructorName);
args.put(KEY_OFFICE, office);
args.put(KEY_OFFICEHOURS, officeHours);
args.put(KEY_PHONENUMBER, phoneNumber);
args.put(KEY_EMAIL, email);

return database.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
}


/**
* Deletes todo
*/

public boolean deleteCourse(long rowId)
{
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

public Cursor fetchCourse(long rowId) throws SQLException
{
Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_CATEGORY, KEY_COURSENUMBER, KEY_ROOMNUMBER, KEY_TIME, KEY_INSTRUCTORNAME, KEY_OFFICE, KEY_OFFICEHOURS, KEY_PHONENUMBER, KEY_EMAIL}, null, null, null,
null, null, null);
if (mCursor != null)
{
mCursor.moveToFirst();
}
return mCursor;
}
}