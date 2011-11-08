package school.planner;
import school.planner.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.*;


public class newCourse extends TabActivity
{
	public void onCreate(Bundle savedInstanceState) //This method creates a premade tab.
	{
	    super.onCreate(savedInstanceState);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    switch (item.getItemId())  //each option must have a layout defined in menu.xml. Simply add a new "case" below 
	    							//for functionality for a new option. "toast" below is a flashing message for demonstration.
	    {
	        case R.id.icontext:  

	        	//Toast.makeText(this, "You pressed the settings button!", Toast.LENGTH_LONG).show();
	                            //break;
	    }
	    return true;
	}
}