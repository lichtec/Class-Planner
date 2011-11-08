package school.planner;
import school.planner.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.DatePicker;

public class AddAssignment extends Activity
{
	Spinner classList;
	TextView nameText;
	EditText assignName;
	TextView descriptionText;
	EditText assignDes;
	TextView duedateText;
	EditText assignDue;
	TextView completedText;
	CheckBox completedBox;
	DatePicker date;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assignmentadd);
		classList = (Spinner) findViewById(R.id.spinner1);
		nameText = (TextView) findViewById(R.id.textView1);
		assignName = (EditText) findViewById(R.id.editText1);
		descriptionText = (TextView) findViewById(R.id.textView2);
		assignDes = (EditText) findViewById(R.id.editText2);
		duedateText = (TextView) findViewById(R.id.textView3);
		date = (DatePicker) findViewById(R.id.datePicker1);
		assignDue = (EditText) findViewById(R.id.editText3);
		completedText = (TextView) findViewById(R.id.textView4);
		completedBox = (CheckBox) findViewById(R.id.checkBox1);
	}
}
