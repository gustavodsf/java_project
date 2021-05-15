package br.ufrj.cos.view;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import br.ufrj.cos.R;
import br.ufrj.cos.control.GetImage;
import br.ufrj.cos.control.SendVote;

public class DishEvaluateActivity extends Activity{
	
	private ImageView imageToEvaluate; 
	
	private ImageButton imgBtnMenu;
	private ImageButton imgBtnCamera;
	private ImageButton imgBtnEvaluate;
	private Button btnNext;
	private SeekBar seekBarEvaluate;
	private GetImage image;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dish_evaluate);
		
		image = new GetImage(this);
		
		imgBtnMenu = (ImageButton) findViewById(R.id.imgBtnMenu);
		imgBtnCamera = (ImageButton) findViewById(R.id.imgBtnCamera);
		imgBtnEvaluate = (ImageButton) findViewById(R.id.imgBtnEvaluate);
		
		btnNext = (Button) findViewById(R.id.btnNext);
		seekBarEvaluate = (SeekBar) findViewById(R.id.seekBarEvaluate);

		try {
			int erro = image.execute().get();
			if(erro == 1){
				
				imageToEvaluate = (ImageView) findViewById(R.id.imageDishEvaluate);
				imageToEvaluate.setImageBitmap(image.getmImageBitmap());
	        	imageToEvaluate.setVisibility(View.VISIBLE);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
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
		
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SendVote vote = new SendVote(v.getContext());
				vote.setPhotoID(image.getPhotoID());
				vote.setGrade(String.valueOf(seekBarEvaluate.getProgress()));
				vote.execute();
			}
		});
		
	}
}
