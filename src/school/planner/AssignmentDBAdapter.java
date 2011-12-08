package school.planner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
* @author christopherlichter
*
*/
public class AssignmentDBAdapter
{
private static final String DATABASE_NAME = "assignments";
private static final String DATABASE_TABLE = "assignmentTable";
private static final int DATABASE_VERSION = 3;


public static final String KEY_CATEGORY = "category";
public static final String KEY_COURSENAME = "courseName";
public static final String KEY_ASSIGNMENTNAME = "assignmentName";
public static final String KEY_DESCRIPTION = "description";
public static final String KEY_DUEDATE = "dueDate";
public static final String KEY_COMPLETED = "completed";

public static final String KEY_ROWID = "_id";
private static final String TAG = "AssignmentDBAdapter";

private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE +" (" + KEY_ROWID + " integer primary key autoincrement, " + KEY_CATEGORY + " text not null, " + KEY_COURSENAME + " text not null, " + KEY_ASSIGNMENTNAME + " text not null, " + KEY_DESCRIPTION + " text not null, " + KEY_DUEDATE + " text not null, " + KEY_COMPLETED + " text not null);";

private final Context context;
private SQLiteDatabase database;
private AssignmentDBHelper dbHelper;

private static class AssignmentDBHelper extends SQLiteOpenHelper
{
AssignmentDBHelper(Context context)
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

public AssignmentDBAdapter(Context context)
{
this.context = context;
}

public AssignmentDBAdapter open() throws SQLException
{
dbHelper = new AssignmentDBHelper(context);
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

public long createCourse(String category, String courseName, String assignmentName, String description, String dueDate, String completed)
{
ContentValues initialValues = new ContentValues();
initialValues.put(KEY_CATEGORY, category);
initialValues.put(KEY_COURSENAME, courseName);
initialValues.put(KEY_ASSIGNMENTNAME, assignmentName);
initialValues.put(KEY_DESCRIPTION, description);
initialValues.put(KEY_DUEDATE, dueDate);
initialValues.put(KEY_COMPLETED, completed);

return database.insert(DATABASE_TABLE, null, initialValues);
}


/**
* Update the todo
*/

public boolean updateCourse(long rowId, String category, String courseName, String assignmentName, String description, String dueDate, String completed)
{
ContentValues args = new ContentValues();
args.put(KEY_CATEGORY, category);
args.put(KEY_COURSENAME, courseName);
args.put(KEY_ASSIGNMENTNAME, assignmentName);
args.put(KEY_DESCRIPTION, description);
args.put(KEY_DUEDATE, dueDate);
args.put(KEY_COMPLETED, completed);

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
return database.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_CATEGORY, KEY_COURSENAME, KEY_ASSIGNMENTNAME, KEY_DESCRIPTION, KEY_DUEDATE, KEY_COMPLETED }, null, null, null,
null, null);
}


/**
* Return a Cursor positioned at the defined todo
*/

public Cursor fetchCourse(long rowId) throws SQLException
{
Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_CATEGORY, KEY_COURSENAME, KEY_ASSIGNMENTNAME, KEY_DESCRIPTION, KEY_DUEDATE, KEY_COMPLETED }, null, null, null,
null, null, null);
if (mCursor != null)
{
mCursor.moveToFirst();
}
return mCursor;
}

}