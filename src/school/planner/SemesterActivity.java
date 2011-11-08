package school.planner;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;

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
        setListAdapter(new ArrayAdapter<String>(this,
        R.layout.listlayout, R.id.label, NAMES));
        getListView().setTextFilterEnabled(true);
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
     super.onListItemClick(l, v, position, id);
     if(position==0)
     {
     Intent i = new Intent(SemesterActivity.this, ClassInformation.class);
     startActivity(i);
     }
     else if (position==1);
    }
}