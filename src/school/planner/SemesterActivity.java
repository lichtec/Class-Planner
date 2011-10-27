package school.planner;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

<<<<<<< HEAD

public class SemesterActivity extends ListActivity
{  
    
    static final String[] NAMES = new String[]{
    	"Class 1",
    	"Class 2"
    };
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.semactivitylayout);
        setListAdapter(new ArrayAdapter<String>(this,
        R.layout.listlayout, R.id.label, NAMES));
        getListView().setTextFilterEnabled(true);
        
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	if(position==0)
    	{
    	Intent i = new Intent(SemesterActivity.this, ClassInformation.class);
    	startActivity(i);
    	}
    	else if (position==1);
    }
}
=======
public class SemesterActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablayout);
		setButtonClickListener();

	}
	
	private void setButtonClickListener()
	{
		Button class_button1 = (Button)findViewById(R.id.class01);
		class_button1.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v)
			{
				Intent i = new Intent(SemesterActivity.this, ClassInformation.class);
				startActivity(i);
			}
		Button class_button2 = (Button)findViewById(R.id.class02);
		Button class_button3 = (Button)findViewById(R.id.class03);
		Button class_button4 = (Button)findViewById(R.id.class04);
		Button addClass_button = (Button)findViewById(R.id.addClass);
		Button newSemester_button = (Button)findViewById(R.id.newSemester);
		//custom_button.setOnClickListener(new View.OnClickListener()
		});
	}
}
>>>>>>> origin/master
