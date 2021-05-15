package br.ufrj.cos.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import br.ufrj.cos.R;
import br.ufrj.cos.control.SendUserInformation;
import br.ufrj.cos.pojo.User;

public class UserInformationActivity extends Activity{
	private ImageView facebookPhoto;
	
	private TextView txtUserName;
	private TextView txtUserBirthday;
	private TextView txtUserSex;
	
	private ImageButton imgBtnMenu;
	private ImageButton imgBtnCamera;
	private ImageButton imgBtnEvaluate;
	
	private Button sendInfo;
	
	private EditText editHeight;
	private EditText editWeight;
	
	private SendUserInformation send;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.user_information);
    	
    	send = new SendUserInformation(this);
    	
		facebookPhoto = (ImageView) findViewById(R.id.facebookPhoto);
		facebookPhoto.setImageBitmap(User.getInstace().getFacebookPhoto());
		facebookPhoto.setVisibility(View.VISIBLE);
		
		txtUserName = (TextView) findViewById(R.id.txtUserName);
		txtUserName.setText(User.getInstace().getName());
		
		txtUserBirthday = (TextView) findViewById(R.id.txtUserBirthday);
		txtUserBirthday.setText(User.getInstace().getBirthday());
		
		txtUserSex = (TextView) findViewById(R.id.txtUserSex);
		txtUserSex.setText(User.getInstace().getGender().equals("male") ? "Male" : "Female");
		
		imgBtnMenu = (ImageButton) findViewById(R.id.imgBtnMenu);
		imgBtnCamera = (ImageButton) findViewById(R.id.imgBtnCamera);
		imgBtnEvaluate = (ImageButton) findViewById(R.id.imgBtnEvaluate);
		
		sendInfo = (Button) findViewById(R.id.sendData);
		
		editHeight = (EditText)findViewById(R.id.editHeight);
		editWeight = (EditText) findViewById(R.id.editWeight);
		
		editHeight.setText(User.getInstace().getHeight());
		editWeight.setText(User.getInstace().getWeight());
		
		imgBtnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MenuActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
		
		imgBtnCamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), DishImageSendActivity.class);
				startActivity(intent);
				finish();
				
			}
		});

		imgBtnEvaluate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), DishEvaluateActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		sendInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String height = editHeight.getText().toString();
				String weight = editWeight.getText().toString();
				
				send.setHeight(height);
				send.setWeight(weight);
				
				send.execute();
			}
		});
	}

}
