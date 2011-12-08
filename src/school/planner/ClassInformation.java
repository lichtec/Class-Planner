package school.planner;
import school.planner.R;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

public class ClassInformation extends ExpandableListActivity
{
	private courseDBAdapter dbAdapter;
	private AssignmentDBAdapter adbAdapter;
	private Cursor courseInfo;
	private Cursor assignmentInfo;
	private String courseNumber;
	private String roomNumber;
	private String time;
	private String profName;
	private String office;
	private String officeHours;
	private String phone;
	private String email;
	
	private String courseName;
	private String assignmentTitle;
	private String description;
	private String dueDate;
	private String completed;
	
	private long rowId;
	private long position;
    static final String headers[] =
    	{
		"General Information", "Professor Information", "Assignments (active)"
		};
	String subHeaders[][];
		
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	
        dbAdapter = new courseDBAdapter(this);
        dbAdapter.open();
        adbAdapter = new AssignmentDBAdapter(this);
        adbAdapter.open();
        position = (long)(getIntent().getIntExtra("pos",0));
        courseInfo = dbAdapter.fetchAllCourses();
        assignmentInfo = adbAdapter.fetchAllCourses();
        assignmentInfo.moveToFirst();
        
        if(assignmentInfo.getPosition() == -1)
        {
        	for(long i=0; i<=position; i++)
            {
            	courseInfo.moveToNext();
            }
            rowId = Long.parseLong(courseInfo.getString(0));
            courseNumber = courseInfo.getString(2);
        	roomNumber = courseInfo.getString(3);
        	time = courseInfo.getString(4);
        	profName = courseInfo.getString(5);
        	office = courseInfo.getString(6);
        	officeHours = courseInfo.getString(7);
        	phone = courseInfo.getString(8);
        	email = courseInfo.getString(9);
        	
            
        	subHeaders = new String [][]
            		{
            			{
            				"Course Number:", courseNumber,
            				"Room Number:", roomNumber,
            				"Time:", time
            			},
            			{
            				"Name:", profName,
            				"Office:", office,
            				"Office Hours:", officeHours,
            				"Phone #:", phone,
            				"E-mail:", email
            			},
            			{
            				"No Assignment Has Been Created", "I hope you didn't forget anything!",
            			}
            		};
        	
        }
        else
        {
        for(long i=0; i<=position; i++)
        {
        	courseInfo.moveToNext();
        	assignmentInfo.moveToNext();
        }
        rowId = Long.parseLong(courseInfo.getString(0));
        courseNumber = courseInfo.getString(2);
    	roomNumber = courseInfo.getString(3);
    	time = courseInfo.getString(4);
    	profName = courseInfo.getString(5);
    	office = courseInfo.getString(6);
    	officeHours = courseInfo.getString(7);
    	phone = courseInfo.getString(8);
    	email = courseInfo.getString(9);
    	
    	courseName = assignmentInfo.getString(2);
    	assignmentTitle = assignmentInfo.getString(3);
    	description = assignmentInfo.getString(4);
    	dueDate = assignmentInfo.getString(5);
    	completed = assignmentInfo.getString(6);
    	
        
    	subHeaders = new String [][]
        		{
        			{
        				"Course Number:", courseNumber,
        				"Room Number:", roomNumber,
        				"Time:", time
        			},
        			{
        				"Name:", profName,
        				"Office:", office,
        				"Office Hours:", officeHours,
        				"Phone #:", phone,
        				"E-mail:", email
        			},
        			{
        				"Course Name: ", courseName,
        				"Assignment Title: ", assignmentTitle,
        				"Description: ", description,
        				"Due Date: ", dueDate,
        				"Is it Complete: ", completed
        			}
        		};
        }
        dbAdapter.close();
        adbAdapter.close();
        setContentView(R.layout.expandlistlayout);
		SimpleExpandableListAdapter expListAdapter = new SimpleExpandableListAdapter(
				this,
				createGroupList(),
				R.layout.group_row, //Stylesheet for the text in this row
				new String[] { "headerName" }, 
				new int[] { R.id.groupname },
				createChildList(),
				R.layout.child_row,
				new String[] { "subheaderName", "subheader2Name" },
				new int[] { R.id.childname, R.id.child2name } //references to the "child_row" stylesheet
			);
		setListAdapter( expListAdapter );
    }
// This creates the headers: Class info, Professor info, etc.
	private List createGroupList() 
	{
	  ArrayList result = new ArrayList();
	  for( int i = 0; i < headers.length ; i++ ) 
	  {
		HashMap m = new HashMap();
	    m.put( "headerName",headers[i] );
		result.add( m );
	  }
	  return (List)result;
    }
// And this creates the subcategories: Professor name, room number, etc.
  private List createChildList() {
	ArrayList result = new ArrayList();
	for( int i = 0 ; i < subHeaders.length ; i++ ) 
	{
	  ArrayList secList = new ArrayList();
//Along with the subcategories, this fills in the prewritten information (see the Strings above).
	  for( int n = 0 ; n < subHeaders[i].length ; n += 2 )
	  {
	    HashMap child = new HashMap();
		child.put( "subheaderName", subHeaders[i][n] );
	    child.put( "subheader2Name", subHeaders[i][n+1]);
		secList.add(child);
	  }
	  result.add(secList);
	}
	return result;
  }
  public boolean onCreateOptionsMenu(Menu menu)
  {
  	MenuInflater inflater = getMenuInflater();
  	inflater.inflate(R.menu.menu2, menu);
  	return true;
  }
  	@Override
  	public boolean onOptionsItemSelected(MenuItem item)
  	{
  		switch (item.getItemId())
  	{
  		case R.id.icontext3:
  		{
  			AlertDialog.Builder builder = new AlertDialog.Builder(this);
  			builder.setMessage("Are you sure you want to delete this class?");
  			builder.setCancelable(false);
  			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
  			{
  			           public void onClick(DialogInterface dialog, int id) 
  			           {
  			        	    courseDBAdapter dbAdapter = new courseDBAdapter(ClassInformation.this);
  			        	    dbAdapter.open();
  			        	    dbAdapter.deleteCourse(rowId);
  			        	    dbAdapter.close();
  			                ClassInformation.this.finish();
  			    			Intent i = new Intent(ClassInformation.this, ClassPlanner.class);
  			    			startActivity(i);
  			           }
  			       })
  			       .setNegativeButton("No", new DialogInterface.OnClickListener() {
  			           public void onClick(DialogInterface dialog, int id) {
  			                dialog.cancel();
  			           }
  			       });
  			AlertDialog alert = builder.create();
  			alert.show();
  			break;
  		}
  	}
  		return true;
  	}

}