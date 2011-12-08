package school.planner;
import school.planner.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
	TextView dueDateText;
	EditText assignDue;
	TextView completedText;
	CheckBox completedBox;
	DatePicker date;
	
	private AssignmentDBAdapter assignmentDB;
	String courseName;
	String assignmentName;
	String description;
	String dueDate;
	String completed;
	String assignment = "assignment";
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assignmentadd);
		assignmentDB =  new AssignmentDBAdapter(this);
		
		setButtonClickListener();
		
		classList = (Spinner) findViewById(R.id.spinner1);
		nameText = (TextView) findViewById(R.id.textView1);
		assignName = (EditText) findViewById(R.id.editText1);
		descriptionText = (TextView) findViewById(R.id.textView2);
		assignDes = (EditText) findViewById(R.id.editText2);
		dueDateText = (TextView) findViewById(R.id.textView3);
		date = (DatePicker) findViewById(R.id.datePicker1);
		completedBox = (CheckBox) findViewById(R.id.checkBox1);
	}
	
	private void setButtonClickListener()
	{
		Button saveAssignment = (Button)findViewById(R.id.button1);
		saveAssignment.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View v)
			{
				Intent i = new Intent(AddAssignment.this, AssignmentDBAdapter.class);
				startActivity(i);
				assignmentDB.open();

				courseName = classList.getContext().toString();
				if(courseName == null)
				{
					courseName = "N/A";
				}
				assignmentName = assignName.getText().toString();
				description = assignDes.getText().toString();
				dueDate = date.getMonth()+ "/" + date.getDayOfMonth() + "/" + date.getYear();
				if(completedBox.equals(1))
				{
					completed = "true";
				}
				else
				{
					completed = "false";
				}
					
			
				assignmentDB.createCourse(assignment, courseName, assignmentName, description, dueDate, completed);
				i = new Intent(AddAssignment.this, ClassPlanner.class);
				startActivity(i);
				assignmentDB.close();
			}
		});
	}
}