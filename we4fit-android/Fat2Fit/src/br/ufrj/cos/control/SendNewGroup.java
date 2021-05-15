package br.ufrj.cos.control;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import br.ufrj.cos.R;
import br.ufrj.cos.pojo.User;
import br.ufrj.cos.util.HttpConnection;
import br.ufrj.cos.util.PathServer;
import br.ufrj.cos.view.MenuActivity;

public class SendNewGroup extends AsyncTask<Integer, String, Integer>{
	
	private Context context;
	ProgressDialog dialog;
	
	private String groupName;
	private boolean existGroup = false;
	
	public SendNewGroup(Context cxt) {
        context = cxt;
        dialog = new ProgressDialog(context);
    }
	
	 @Override
     protected void onPreExecute() {
         dialog.setTitle(R.string.msgSendGroup);
         dialog.show();
     }
	 
	 @Override
	protected Integer doInBackground(Integer... params) {
	   String urlExist = PathServer.GROUPWS + "exist/" + groupName ;
	   String urlAdd = PathServer.GROUPWS + "create/"+ groupName + "/" + User.getInstace().getId() ;
		
	   HttpConnection connection = new HttpConnection();
	   String result = connection.getResultString(urlExist);
	   
	   if(result.equals("NAO_EXISTE\n")){
		   existGroup = false;
		   result = connection.getResultString(urlAdd);
	   }else{
		   existGroup = true;
	   }
	   
	   return 1;
		
	}

	@Override
	protected void onPostExecute(Integer result) {
        dialog.dismiss();
        if(existGroup){
        	Toast.makeText(((Activity)context).getApplicationContext(), "Group exist with this name", Toast.LENGTH_SHORT).show();
        }else{
        	Intent intent = new Intent(context, MenuActivity.class);
			((Activity)context).startActivity(intent);
			((Activity)context).finish();
			Toast.makeText(((Activity)context).getApplicationContext(), "Group successfully created", Toast.LENGTH_SHORT).show();
        }
    }

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
