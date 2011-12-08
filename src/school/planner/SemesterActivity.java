package school.planner;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;

public class SemesterActivity extends ListActivity
{
private courseDBAdapter dbAdapter;
private Cursor courseInfo;
private String[] name;
@Override
    public void onCreate(Bundle savedInstanceState)
{

        super.onCreate(savedInstanceState);
        dbAdapter = new courseDBAdapter(this);
        dbAdapter.open();
        courseInfo = dbAdapter.fetchAllCourses();
        int courseCount = courseInfo.getCount();
        if (!(courseInfo.moveToFirst()))
        {
            name = new String[1];
        	name[0] = "Please create a new course";
        }
        else
        {
        	courseInfo.moveToFirst();
            name = new String[courseCount];
        	for(int i=0; i<courseCount; i++)
        	{
        		name[i] = courseInfo.getString(2);
        		if(!courseInfo.isLast())
        		{
        			courseInfo.moveToNext();
        		}
        	}
        }
    	setListAdapter(new ArrayAdapter<String>(this, R.layout.listlayout, R.id.label, name));
    	getListView().setTextFilterEnabled(true);
        dbAdapter.close();
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
     super.onListItemClick(l, v, position, id);
    	 if ((courseInfo.moveToFirst()))
    	 {
    		 Intent i = new Intent(SemesterActivity.this, ClassInformation.class);
    		 i.putExtra("pos", (position));
    		 startActivity(i);
    	 }
    	 else
    	 {
    		 Intent i = new Intent(SemesterActivity.this, newCourseActivity.class);
    		 startActivity(i);
    	 }
    }
}