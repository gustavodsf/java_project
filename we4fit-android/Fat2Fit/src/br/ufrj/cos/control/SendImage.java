package br.ufrj.cos.control;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import br.ufrj.cos.R;
import br.ufrj.cos.pojo.User;
import br.ufrj.cos.util.PathServer;
import br.ufrj.cos.view.We4FitActivity;

public class SendImage  extends AsyncTask<Integer, String, Integer> {
	
	private String urlStr= PathServer.PHOTOWS + "send/";
	private Bitmap mImageBitmap;
	private Context context;
	private String grade;
	
	ProgressDialog dialog;
	
	public SendImage(Context cxt) {
        context = cxt;
        dialog = new ProgressDialog(context);
    }
	
	@Override
    protected void onPreExecute() {
        dialog.setTitle(R.string.msgSendImage);
        dialog.show();
    }

	@Override
	protected Integer doInBackground(Integer... params) {
		int erro = 0;
		
		if (mImageBitmap != null){
			try{
			    ByteArrayOutputStream bos = new ByteArrayOutputStream();
			    mImageBitmap.compress(CompressFormat.JPEG, 75, bos);
			    byte[] data = bos.toByteArray();
			    ByteArrayBody bab = new ByteArrayBody(data, User.getInstace().getId()+"_"+GregorianCalendar.getInstance().toString() );
			     
		     	HttpClient httpclient = new DefaultHttpClient();
			    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			    HttpPost httppost = new HttpPost(urlStr);
			    MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create(); 
			    reqEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			    reqEntity.addPart("uploadedFile", bab);
			    reqEntity.addTextBody("userId", User.getInstace().getId());
			    reqEntity.addTextBody("userGrade", grade);
			    
			    httppost.setEntity(reqEntity.build());
	            HttpResponse response = httpclient.execute(httppost);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	            String sResponse;
	            StringBuilder s = new StringBuilder();
	 
	            while ((sResponse = reader.readLine()) != null) {
	                s = s.append(sResponse);
	            }
	            return 1;
			}catch(Exception e){
				return 0;
			}
		}
		return erro;
		
	}
	
	@Override
	protected void onPostExecute(Integer result) {
        dialog.dismiss();
		Intent intent = new Intent(context,We4FitActivity.class);
		((Activity)context).startActivity(intent);
		((Activity)context).finish();
    }
	
	public Bitmap getmImageBitmap() {
		return mImageBitmap;
	}

	public void setmImageBitmap(Bitmap mImageBitmap) {
		this.mImageBitmap = mImageBitmap;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}


