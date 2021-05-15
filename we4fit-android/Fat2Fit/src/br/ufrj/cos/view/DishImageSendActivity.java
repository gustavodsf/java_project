package br.ufrj.cos.view;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import br.ufrj.cos.R;
import br.ufrj.cos.control.SendImage;


public class DishImageSendActivity extends Activity {

	private static final int ACTION_TAKE_PHOTO_S = 2;

	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
	private ImageView mImageView;
	private Bitmap mImageBitmap;
	private SeekBar seekBarGrade;
	
	private ImageButton imgBtnMenu;
	private ImageButton imgBtnCamera;
	private ImageButton imgBtnEvaluate;
	
	private void dispatchTakePictureIntent(int actionCode) {

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePictureIntent, actionCode);
	}

	private void handleSmallCameraPhoto(Intent intent) {
		Bundle extras = intent.getExtras();
		mImageBitmap = (Bitmap) extras.get("data");
		mImageView.setImageBitmap(mImageBitmap);
		mImageView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dish_submit);

		mImageView = (ImageView) findViewById(R.id.dishImageView);
		mImageBitmap = null;

		ImageButton picSBtn = (ImageButton) findViewById(R.id.btnTakePicture);
		ImageButton sendPic = (ImageButton) findViewById(R.id.btnSendToServer);
		
		imgBtnMenu = (ImageButton) findViewById(R.id.imgBtnMenu);
		imgBtnCamera = (ImageButton) findViewById(R.id.imgBtnCamera);
		imgBtnEvaluate = (ImageButton) findViewById(R.id.imgBtnEvaluate);
		
		seekBarGrade = (SeekBar) findViewById(R.id.seekBarGrade);
		
		picSBtn.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dispatchTakePictureIntent(ACTION_TAKE_PHOTO_S);
			}
		});

		
		sendPic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendToServer();
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			handleSmallCameraPhoto(data);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
		outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null) );
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
		mImageView.setImageBitmap(mImageBitmap);
		mImageView.setVisibility(savedInstanceState.getBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY) ? View.VISIBLE : View.INVISIBLE);
	}

	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}
	
	private void sendToServer(){
		if (mImageBitmap != null) {
			executeMultipartPost();
		}
		else{
			Toast.makeText(getApplicationContext(),R.string.selectImage, Toast.LENGTH_SHORT).show();
		}
	}
	
	private void executeMultipartPost(){
		try {
		  SendImage send = new SendImage(this);
		  send.setmImageBitmap(mImageBitmap);
		  String grade =  String.valueOf(seekBarGrade.getProgress());
		  send.setGrade(grade);
		  send.execute().get();
		} catch (Exception ex) {
		   Log.d("ERRO", ex.getMessage());
		}
	     
	}
}