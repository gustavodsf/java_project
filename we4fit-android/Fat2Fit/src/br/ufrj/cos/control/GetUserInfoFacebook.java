package br.ufrj.cos.control;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import br.ufrj.cos.pojo.User;
import br.ufrj.cos.util.PathServer;
import br.ufrj.cos.view.MenuActivity;

import com.facebook.Request;
import com.facebook.Session;

public class GetUserInfoFacebook extends AsyncTask<Integer, String, Integer> {
	
	private String urlStr;
	private Context context;
	
	public GetUserInfoFacebook(Context cxt) {
	    context = cxt;
	}
	
	@Override
	protected Integer doInBackground(Integer... params) {
		Session session = Session.getActiveSession();
		Request request = Request.newGraphPathRequest(session, "me", null);
		com.facebook.Response response = Request.executeAndWait(request);
		if(Session.getActiveSession().isOpened()){
			User.getInstace().setId(response.getGraphObject().getProperty("id").toString());
			User.getInstace().setFirstName(response.getGraphObject().getProperty("first_name").toString());
			User.getInstace().setLocale(response.getGraphObject().getProperty("locale").toString());
			User.getInstace().setName(response.getGraphObject().getProperty("name").toString());
			User.getInstace().setLastName(response.getGraphObject().getProperty("last_name").toString());
			User.getInstace().setGender(response.getGraphObject().getProperty("gender").toString());
			User.getInstace().setEmail(response.getGraphObject().getProperty("email").toString());
			User.getInstace().setBirthday(response.getGraphObject().getProperty("birthday").toString());
			try {
				 String url = PathServer.USERWS + "add/" + User.getInstace().getId();
				 URL obj = new URL(url);
				 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				 con.setRequestMethod("GET");
				 con.getResponseCode();
				 
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		if(Session.getActiveSession().isOpened()){
			Intent intent = new Intent(context, MenuActivity.class);
			((Activity)context).startActivity(intent);
		}
    }
	
	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
}
