package com.keskin.dokunma;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity 
{
	Double i=0.0;
	int intime=0;
	int scorel=0;
	int hiz=0;
	int red=0;
	int green=0;
	int yellow=0;
	int score=0;
	int hiscore=0;
	Random r = new Random();
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		final Button boost =(Button) findViewById(R.id.t);
		final Button start =(Button) findViewById(R.id.start);
		boost.setEnabled(false);
		final SharedPreferences read = getSharedPreferences("score", MODE_PRIVATE);
		final SharedPreferences.Editor editor = getSharedPreferences("score", MODE_PRIVATE).edit();
		int sayi = read.getInt("sayi",0);
		for (int j=0; j<sayi; j++){
			if ( hiscore >read.getInt(Integer.toString(j),0)){
				hiscore= hiscore+0;
			}
			else{
				hiscore = read.getInt(Integer.toString(j),0);
			}
			setTitle("En yüksek başarı: "+Integer.toString(hiscore));
		}
		final Handler h = new Handler();
		final Runnable u = new Runnable(){

			@Override
			public void run()
			{
			    if (boost.isEnabled()){
			    boost.setText(Integer.toString(score));
				start.setEnabled(false);
				start.setText("Kalan süre: "+Double.toString((1000-i)/100) + "\nDokunma hızınız: "+Integer.toString(hiz));
			    i=i+1;
				intime=intime+1;
		    	
				if (i > 1000){
					boost.setEnabled(false);
					int sayi = read.getInt("sayi",0);
					editor.putInt("sayi",sayi+1);
					editor.putInt(Integer.toString(sayi),score);
					editor.putString(Integer.toString(sayi)+"rgb"," \t \t \t \t K:"+Integer.toString(red)+"\t S:"+Integer.toString(yellow)+"\t Y:"+Integer.toString(green));
					editor.commit();
					start.setEnabled(true);
					start.setText("Başla");
					start.setBackgroundColor(Color.BLUE);
					for (int j=0; j<sayi; j++){
						if ( hiscore >read.getInt(Integer.toString(j),0)){
							hiscore= hiscore+0;
						}
						else{
							hiscore = read.getInt(Integer.toString(j),0);
						}
							setTitle("En yüksek başarı: "+Integer.toString(hiscore));
					}
				}
				if (intime==100){
					intime=0;
					hiz=score-scorel;
					scorel=score;
					if (hiz >= 15){
					    start.setBackgroundColor(Color.GREEN);
						green=green+1;
					}
					if (hiz<15){
					    start.setBackgroundColor(Color.YELLOW);
						yellow=yellow+1;
					}
					if (hiz<10){
					    start.setBackgroundColor(Color.RED);
						red=red+1;
					}
				}
				}
			  h.postDelayed(this,1);
			}
	
		};
		h.removeCallbacks(u);
		h.post(u);
    }
	public int upscore (View view){
		score = score + 1;
		int red = 255-r.nextInt(200);
	    int green =255- r.nextInt(200);
		int blue = 255-r.nextInt(200);
		view.setBackgroundColor(Color.rgb(red,green,blue));
		//view.(Color.rgb(255-red,255-green,255-blue));
		return 0;
	}
	public void boostenable (View view){
		Button boost = (Button) findViewById(R.id.t);
		boost.setEnabled(true);
		score =0;
		i=0.0;
	}
	public void scores (View view){
		Intent  scr= new Intent(this,scores.class);
		startActivity(scr);
	}
	
}
