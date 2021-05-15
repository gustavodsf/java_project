package br.ufrj.cos.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import br.ufrj.cos.R;
import br.ufrj.cos.control.SendNewGroup;

public class CreateGroupActivity  extends Activity {
	
	private ImageButton imgBtnMenu;
	private ImageButton imgBtnCamera;
	private ImageButton imgBtnEvaluate;
	
	private EditText editTxtGroupName;
	private Button sendNewGroup;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.create_group);
    	
    	editTxtGroupName = (EditText) findViewById(R.id.editTxtGroupName);
    	
    	sendNewGroup = (Button) findViewById(R.id.btnCreateGroup);
    	
    	imgBtnMenu = (ImageButton) findViewById(R.id.imgBtnMenu);
		imgBtnCamera = (ImageButton) findViewById(R.id.imgBtnCamera);
		imgBtnEvaluate = (ImageButton) findViewById(R.id.imgBtnEvaluate);
    	
		sendNewGroup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SendNewGroup newGroup  = new SendNewGroup(v.getContext());
				newGroup.setGroupName(editTxtGroupName.getText().toString());
				newGroup.execute();
			}
		});
		
    	imgBtnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), We4FitActivity.class);
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
    	
    }
}
