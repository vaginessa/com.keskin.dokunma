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
					if (hiz >= 15 ){
					    start.setBackgroundColor(Color.GREEN);
						green=green+1;
					}
					if (hiz<15 && hiz>10){
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
