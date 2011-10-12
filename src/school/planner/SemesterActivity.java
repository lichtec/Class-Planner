package school.planner;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class SemesterActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablayout);
		setButtonClickListener();
		//TextView tempText = new TextView(this);
		//tempText.setText("Please add functionality to me :D");
		//setContentView(tempText);
	}
	
	private void setButtonClickListener()
	{
		Button class_button1 = (Button)findViewById(R.id.class01);
		class_button1.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v)
			{
				//setContentView(R.layout.main);
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
