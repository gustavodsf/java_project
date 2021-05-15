package br.ufrj.cos.view;

import br.ufrj.cos.control.GetUserInfoServer;
import android.app.Activity;
import android.os.Bundle;

public class AnswerUserInfoServerActivity  extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GetUserInfoServer server = new GetUserInfoServer(this);
		server.execute();
	}
	
}
