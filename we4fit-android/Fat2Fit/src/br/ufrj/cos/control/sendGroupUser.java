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

public class sendGroupUser extends AsyncTask<Integer, String, Integer>{
		
		private Context context;
		ProgressDialog dialog;
		
		private String groupName;
		private boolean existGroup = false;
		
		public sendGroupUser(Context cxt) {
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
		   String urlAdd = PathServer.GROUPWS + "add_user/"+ groupName + "/" + User.getInstace().getId() ;
			
		   HttpConnection connection = new HttpConnection();
		   String result = connection.getResultString(urlExist);
		   
		   if(result.equals("NAO_EXISTE\n")){
			   existGroup = false;
		   }else{
			  
			   result = connection.getResultString(urlAdd);
			   existGroup = true;
		   }
		   
		   return 1;
			
		}

		@Override
		protected void onPostExecute(Integer result) {
	        dialog.dismiss();
	        if(existGroup){
	        	Intent intent = new Intent(context, MenuActivity.class);
				((Activity)context).startActivity(intent);
				((Activity)context).finish();
				Toast.makeText(((Activity)context).getApplicationContext(), "You've been added to this group.", Toast.LENGTH_SHORT).show();
	        }else{
	        	Toast.makeText(((Activity)context).getApplicationContext(), "No group exist with this name", Toast.LENGTH_SHORT).show();
	        }
	    }

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
}