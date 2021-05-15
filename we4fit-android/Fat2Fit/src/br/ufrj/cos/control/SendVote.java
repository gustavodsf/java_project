package br.ufrj.cos.control;

import br.ufrj.cos.R;
import br.ufrj.cos.pojo.User;
import br.ufrj.cos.util.HttpConnection;
import br.ufrj.cos.util.PathServer;
import br.ufrj.cos.view.DishEvaluateActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class SendVote extends AsyncTask<Integer, String, Integer> {
	
	private Context context;
	ProgressDialog dialog;
	
	private String photoID;
	private String grade;
	
	
	
	public SendVote(Context cxt) {
        context = cxt;
        dialog = new ProgressDialog(context);
    }
	
	 @Override
     protected void onPreExecute() {
		 dialog.setTitle(R.string.msgSendVote);
		 dialog.show();
     }
	 
	 @Override
	protected Integer doInBackground(Integer... params) {
		 
		 String url = PathServer.VOTEWS + "grade/" + User.getInstace().getId() +"/" + photoID.replace("\n", "") + "/" + grade +"/";
		 HttpConnection connection = new HttpConnection();
		 connection.getResultString(url);
		 return 0;
	}

	@Override
	protected void onPostExecute(Integer result) {
        dialog.dismiss();
        Intent intent = new Intent(context, DishEvaluateActivity.class);
		((Activity)context).startActivity(intent);
		((Activity)context).finish();
    }

	public String getPhotoID() {
		return photoID;
	}

	public void setPhotoID(String photoID) {
		this.photoID = photoID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
}

