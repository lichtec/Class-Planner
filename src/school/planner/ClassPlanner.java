package school.planner;
import school.planner.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;


public class ClassPlanner extends TabActivity
{
	public void onCreate(Bundle savedInstanceState) //This method creates a premade tab.
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    //Resources res = getResources(); // Resource object to get Drawables //if we want icons for the tabs.
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent semIntent;
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    semIntent = new Intent();
	    semIntent.setClass(this, SemesterActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Fall '11").setIndicator("Fall '11").setContent(semIntent);
	    tabHost.addTab(spec);
	    spec = tabHost.newTabSpec("Spring '12").setIndicator("Spring '12").setContent(semIntent);
	    tabHost.addTab(spec);
	    tabHost.setCurrentTab(2); //Probably useful later.
	}
}