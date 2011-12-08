package school.planner;

import school.planner.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class newCourseActivity extends Activity
{
	private courseDBAdapter courseDB;
	String courseNumber;
	String roomNumber;
	String time;
	String name;
	String office;
	String officeHours;
	String phoneNumber;
	String emailAddress;
	String course = "course";

	public void onCreate(Bundle savedInstanceState) //This method creates a premade tab.
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newcourselayout);
		setButtonClickListener();

	}
	private void setButtonClickListener()
	{
		Button saveCourse = (Button)findViewById(R.id.saveCourseButton);
		saveCourse.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				courseDB = new courseDBAdapter(newCourseActivity.this);
				courseDB.open();
				EditText editCourseNumber = (EditText)findViewById(R.id.courseNumberField);
				EditText editRoomNumber = (EditText)findViewById(R.id.roomNumberField);
				EditText editTime = (EditText)findViewById(R.id.timeField);
				EditText editName = (EditText)findViewById(R.id.nameField);
				EditText editOffice = (EditText)findViewById(R.id.officeField);
				EditText editOfficeHours = (EditText)findViewById(R.id.officeHoursField);
				EditText editPhoneNumber = (EditText)findViewById(R.id.phoneNumberField);
				EditText editEmailAddress = (EditText)findViewById(R.id.emailAddressField);

				courseNumber = editCourseNumber.getText().toString();
				roomNumber = editRoomNumber.getText().toString();
				time = editTime.getText().toString();
				name = editName.getText().toString();
				office = editOffice.getText().toString();
				officeHours = editOfficeHours.getText().toString();
				phoneNumber = editPhoneNumber.getText().toString();
				emailAddress = editEmailAddress.getText().toString();

				courseDB.createCourse(course, courseNumber, roomNumber, time, name, office, 
						officeHours, phoneNumber, emailAddress);
				courseDB.close();
				newCourseActivity.this.finish();
				Intent i = new Intent(newCourseActivity.this, ClassPlanner.class);
				startActivity(i);
			}
		});
	}

}