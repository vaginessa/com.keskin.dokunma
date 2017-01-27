package com.keskin.dokunma;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.preference.*;
import android.os.Vibrator;

public class vibrate extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for max milliseconds
		v.vibrate(2147483647);
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for max milliseconds
		v.vibrate(2147483647);
	}
}

