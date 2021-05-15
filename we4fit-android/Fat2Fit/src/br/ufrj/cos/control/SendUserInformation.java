package br.ufrj.cos.control;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import br.ufrj.cos.R;
import br.ufrj.cos.pojo.User;
import br.ufrj.cos.util.PathServer;
import br.ufrj.cos.view.MenuActivity;

public class SendUserInformation extends AsyncTask<Integer, String, Integer> {
	private Context context;
	ProgressDialog dialog;
	
	private String height;
	private String weight;
	
	public SendUserInformation(Context cxt) {
        context = cxt;
        dialog = new ProgressDialog(context);
    }
	
	 @Override
     protected void onPreExecute() {
         dialog.setTitle(R.string.msgSendInfo);
         dialog.show();
     }
	 
	 @Override
	protected Integer doInBackground(Integer... params) {
	   String url = PathServer.USERWS + "edit/" + User.getInstace().getId() + "/" + weight + "/" + height;
		 
		try {
			URL obj = new URL(url);
			 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 con.setRequestMethod("GET");
			 con.getResponseCode();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
		
	}

	@Override
	protected void onPostExecute(Integer result) {
        dialog.dismiss();
        Intent intent = new Intent(context, MenuActivity.class);
		((Activity)context).startActivity(intent);
		((Activity)context).finish();
		Toast.makeText(((Activity)context).getApplicationContext(), "User info successfully changed", Toast.LENGTH_SHORT).show();
    }

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
}
