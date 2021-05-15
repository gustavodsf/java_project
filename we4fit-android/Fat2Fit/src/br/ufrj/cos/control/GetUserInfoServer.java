package br.ufrj.cos.control;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import br.ufrj.cos.R;
import br.ufrj.cos.pojo.User;
import br.ufrj.cos.util.HttpConnection;
import br.ufrj.cos.util.PathServer;
import br.ufrj.cos.view.UserInformationActivity;

public class GetUserInfoServer extends AsyncTask<Integer, String, Integer> {
	
	private Context context;
	ProgressDialog dialog;
	
	public GetUserInfoServer(Context cxt) {
	    context = cxt;
	    dialog = new ProgressDialog(context);
	}
	
	@Override
	protected void onPreExecute() {
	    dialog.setTitle(R.string.msgGetServerInfo);
	    dialog.show();
	}
	
	@Override
	protected Integer doInBackground(Integer... params) {
		
		String url = PathServer.USERWS + "get/" + User.getInstace().getId();
		
		try {
			HttpConnection connection = new HttpConnection();
			String result  = connection.getResultString(url);
			JSONObject json = new JSONObject(result);
			
			 if(!json.get("height").equals(null)){
				 User.getInstace().setHeight(json.get("height").toString());
			 }else{
				 User.getInstace().setHeight("0");
			 }
		    
		    if(!json.get("weights").equals(null) && !json.get("weights").toString().equals("[]")){
		      JSONArray jsonArray =  json.getJSONArray("weights");
		      
		      long auxTime = 0;
		      for(int i=0; i < jsonArray.length() ; i++){
		    	  JSONObject obj = jsonArray.getJSONObject(i);
		    	  if(auxTime < Long.valueOf(obj.get("insertionDate").toString())){
		    		  auxTime = Long.valueOf(obj.get("insertionDate").toString());
		    		  User.getInstace().setWeight(obj.get("weight").toString());
		    	  }
		      }
			   
		    }else{
		    	User.getInstace().setWeight("0");
		    }
		    
		    URL img_value = new URL("http://graph.facebook.com/" + User.getInstace().getId() + "/picture?type=normal");
		    Bitmap mIcon1 = BitmapFactory.decodeStream(img_value.openConnection().getInputStream());
		    User.getInstace().setFacebookPhoto(mIcon1);
		    
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
        dialog.dismiss();
        Intent intent = new Intent(((Activity)context),UserInformationActivity.class);
		((Activity)context).startActivity(intent);
		((Activity)context).finish();
        
    }

}
