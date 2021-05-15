package br.ufrj.cos.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.ufrj.cos.R;

public class MenuActivity extends Activity{
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
	    Button btnAboutMe  =      (Button)findViewById(R.id.btnAboutMe);
	    Button btnAddGroup =      (Button)findViewById(R.id.btnAddGroup);
	    Button btnEvaluateDish  = (Button)findViewById(R.id.btnEvaluateDish);
	    Button btnFindGroup  =    (Button)findViewById(R.id.btnFindGroup);
	    Button btnSendDish  =     (Button)findViewById(R.id.btnSendDish);
	    Button btnRankings =      (Button)findViewById(R.id.btnRankings);
	    
	    btnAboutMe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), AnswerUserInfoServerActivity.class);
				startActivity(intent);
				finish();
			}
		});
	    
	    btnAddGroup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), CreateGroupActivity.class);
				startActivity(intent);
			}
		});
	    
	    btnEvaluateDish.setOnClickListener(new OnClickListener() {
			
	    	@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), DishEvaluateActivity.class);
				startActivity(intent);
			}
		});
	    
	    btnFindGroup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), FindGroupActivity.class);
				startActivity(intent);
				
			}
		});
	    
	    btnSendDish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), DishImageSendActivity.class);
				startActivity(intent);
				
			}
		});
	    
	    btnRankings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	}
}
