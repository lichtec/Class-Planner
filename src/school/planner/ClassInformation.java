package school.planner;
import school.planner.R;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import android.widget.SimpleExpandableListAdapter;

public class ClassInformation extends ExpandableListActivity
{
    static final String headers[] =
    	{
		"General Information", "Professor Information", "Assignments (active)"
		};

	static final String subHeaders[][] =
		{
			{
				"Course Number:", "CM333",
				"Room Number:", "MO 017",
				"Time:", "11:00 AM T-Th"
			},
			{
				"Name:", "Cecil Shmidt",
				"Office:", "ST 317",
				"Office Hours:", "12-2 M-W",
				"Phone #:", "555-5555",
				"E-mail:", "Cecil.Schmidt@washburn.edu"
			},
			{
				"New Assignment", "This is a new Assignment"
			}
		};
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlayout);
		SimpleExpandableListAdapter expListAdapter = new SimpleExpandableListAdapter(
				this,
				createGroupList(),
				R.layout.group_row,
				new String[] { "headerName" },
				new int[] { R.id.groupname },
				createChildList(),
				R.layout.child_row,
				new String[] { "subheaderName", "subheader2Name" },
				new int[] { R.id.childname, R.id.child2name }
			);
		setListAdapter( expListAdapter );
    }

	private List createGroupList() {
	  ArrayList result = new ArrayList();
	  for( int i = 0 ; i < headers.length ; ++i ) {
		HashMap m = new HashMap();
	    m.put( "headerName",headers[i] );
		result.add( m );
	  }
	  return (List)result;
    }

  private List createChildList() {
	ArrayList result = new ArrayList();
	for( int i = 0 ; i < subHeaders.length ; ++i ) {
// Second-level lists
	  ArrayList secList = new ArrayList();
	  for( int n = 0 ; n < subHeaders[i].length ; n += 2 ) {
	    HashMap child = new HashMap();
		child.put( "subheaderName", subHeaders[i][n] );
	    child.put( "subheader2Name", subHeaders[i][n+1] );
		secList.add( child );
	  }
	  result.add( secList );
	}
	return result;
  }

}