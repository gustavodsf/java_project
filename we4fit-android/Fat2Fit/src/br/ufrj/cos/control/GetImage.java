package br.ufrj.cos.control;

import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;
import br.ufrj.cos.R;
import br.ufrj.cos.pojo.User;
import br.ufrj.cos.util.HttpConnection;
import br.ufrj.cos.util.PathServer;
import br.ufrj.cos.view.MenuActivity;

public class GetImage extends AsyncTask<Integer, String, Integer> {
	
	private Bitmap mImageBitmap;
	private Context context;
	ProgressDialog dialog;
	
	boolean dontHavePhoto = true;
	private String photoID;

	
	public GetImage(Context cxt) {
        context = cxt;
        dialog = new ProgressDialog(context);
    }
	
	 @Override
     protected void onPreExecute() {
         dialog.setTitle(R.string.msgGetImage);
         dialog.show();
     }
	 
	 @Override
	protected Integer doInBackground(Integer... params) {
		String urlExistPhotoEvaluate = PathServer.PHOTOWS + "exist/" + User.getInstace().getId()+"/";
		String urlGetPhoto =  PathServer.PHOTOWS + "get/" + User.getInstace().getId() + "/";
		
		HttpConnection connection = new HttpConnection();
		
		String result = connection.getResultString(urlExistPhotoEvaluate);
		if(result.equals("NAO_TEM\n")){
			dontHavePhoto = true;
			return 0;
		}else{
			photoID = result;
			dontHavePhoto = false;
			InputStream  inputStream = connection.getResultInputStream(urlGetPhoto);
			mImageBitmap = BitmapFactory.decodeStream(inputStream);
			return 1;
		}
	}

	@Override
	protected void onPostExecute(Integer result) {
        if(dontHavePhoto){
        	Intent intent = new Intent(((Activity)context),MenuActivity.class);
    		((Activity)context).startActivity(intent);
    		((Activity)context).finish();
    		Toast.makeText(((Activity)context).getApplicationContext(), "Don't have photo to evaluate.", Toast.LENGTH_SHORT).show();
        }
        dialog.dismiss();
    }
	
	public Bitmap getmImageBitmap() {
		return mImageBitmap;
	}

	public void setmImageBitmap(Bitmap mImageBitmap) {
		this.mImageBitmap = mImageBitmap;
	}

	public String getPhotoID() {
		return photoID;
	}

	public void setPhotoID(String photoID) {
		this.photoID = photoID;
	}
}
