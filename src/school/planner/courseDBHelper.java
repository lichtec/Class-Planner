package school.planner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class courseDBHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "coursedata";
	private static final int DATABASE_VERSION = 1;
	
	//Database creation sql statement
	private static final String DATABASE_CREATE = "create table course (_id integer primary key autoincrement," + "category tet not null, summary text not null, description text not null);";
	
	public courseDBHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database)
	{
		database.execSQL(DATABASE_CREATE);
	}
	
	//Method is called during an upgrade of the database, e.g. if you increase the db version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		Log.w(courseDBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + " , which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS course");
		onCreate(database);
	}

}