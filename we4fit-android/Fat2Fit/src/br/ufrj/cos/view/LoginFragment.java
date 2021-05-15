package br.ufrj.cos.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.ufrj.cos.R;

import com.facebook.widget.LoginButton;

public class LoginFragment extends Fragment{
	LoginButton authButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.login,container, false);
	    return view;
	}
}
