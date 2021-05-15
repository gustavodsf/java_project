package br.ufrj.cos.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.ufrj.cos.R;
import br.ufrj.cos.control.GetUserInfoFacebook;

public class LogadoFragment extends Fragment{
		
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
	    super.onCreateView(inflater, container, savedInstanceState);
	    View view = inflater.inflate(R.layout.logado,container, false);
	    
	    
	    return view;
	}
	
	@Override
	public void onStart(){
		super.onStart();
		GetUserInfoFacebook info = new GetUserInfoFacebook(getActivity());
	    info.execute();
	}
}
