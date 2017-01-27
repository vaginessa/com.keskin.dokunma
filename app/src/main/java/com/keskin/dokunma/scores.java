package com.keskin.dokunma;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;

public class scores extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores);
		TextView ts = (TextView) findViewById(R.id.scoretext);
		final SharedPreferences read = getSharedPreferences("score", MODE_PRIVATE);
		int i=0;
		String tmp="";
		int sayi = read.getInt("sayi",0);
		for (i=0 ;i<sayi;i++)
		{
			tmp=Integer.toString(read.getInt(Integer.toString(i),0))+read.getString(Integer.toString(i)+"rgb","");
			ts.setText(ts.getText()+Integer.toString(i+1)+" : "+tmp+"\n");
		}
	}
}
