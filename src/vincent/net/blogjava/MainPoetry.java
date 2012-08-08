package vincent.net.blogjava;


import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.annotation.SuppressLint;
import android.app.Activity;  
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;  
import android.os.Handler;

import android.util.Log;
import android.view.View;  
import android.widget.Button;  
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;





public class MainPoetry extends Activity {
	public static final String ENCODING = "UTF-8"; 
	private SeekBar skb_audio=null;
    private Button btn_start_audio = null;  
    private Button btn_stop_audio = null;
    Handler handler=new Handler();
	int Duration;
    private MediaPlayer m = null;  
    TextView duration;
    TextView total;
    TextView content;
    TextView poetryName;
       
	public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main_poetry);  
        btn_start_audio = (Button) this.findViewById(R.id.Button01);  
        btn_stop_audio = (Button) this.findViewById(R.id.Button02);  
        btn_start_audio.setOnClickListener(playlis);
        btn_stop_audio.setOnClickListener(pauselis);
        Bundle bundle = getIntent().getExtras();  
		String id = bundle.getString("id");
		Poetry p =PoetryManager.getPoetry(Integer.parseInt(id));
		AssetFileDescriptor afd = null;
		m = new MediaPlayer();
			try {
				afd = this.getResources().getAssets().openFd(p.getNameOfMP3());
				m.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
				m.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
        skb_audio=(SeekBar)this.findViewById(R.id.SeekBar01);
        skb_audio.setOnSeekBarChangeListener(sbLis);
        Duration=m.getDuration();
        skb_audio.setMax(Duration);
        duration = (TextView)this.findViewById(R.id.duration); 
        total = (TextView)this.findViewById(R.id.Total); 
        total.setText(toTime(Duration));
        content = (TextView)this.findViewById(R.id.Content); 
        content.setText(getFromAssets(p.getNameOfText()));
        poetryName = (TextView)this.findViewById(R.id.poetryName); 
        poetryName.setText(p.getName());

    }
      private  OnClickListener playlis=new OnClickListener(){
    		public void onClick(View v) {
    			handler.post(start);
    		}
     
        };
        private Runnable start=new Runnable(){

    		public void run() {
    			m.start();
    			handler.post(updatesb);
    		}
     
        };
        Runnable updatesb =new Runnable(){
     
    		
    		public void run() {
    			skb_audio.setProgress(m.getCurrentPosition());
    			handler.postDelayed(updatesb, 1000);
    			duration.setText(toTime(m.getCurrentPosition()));
    		}
     
        };
         OnClickListener pauselis=new OnClickListener(){
     
    		
    		public void onClick(View v) {
    			m.pause();		
    		}
     
        };
        OnSeekBarChangeListener sbLis=new OnSeekBarChangeListener(){
     
    		
    		public void onProgressChanged(SeekBar seekBar, int progress,
    				boolean fromUser) {
    			duration.setText(toTime(skb_audio.getProgress()));
    		}
     
    		
    		public void onStartTrackingTouch(SeekBar seekBar) {
    			duration.setText(toTime(skb_audio.getProgress()));
     
    		}
     
    		
    		public void onStopTrackingTouch(SeekBar seekBar) {
    			m.seekTo(skb_audio.getProgress());
    			duration.setText(toTime(skb_audio.getProgress()));
    
    		}
     
        };

        public String toTime(int time) {  
            
            time /= 1000;  
            int minute = time / 60;  
            int second = time % 60;  
            minute %= 60;  
            return String.format("%02d:%02d", minute, second);  
        }
        
        public String getFromAssets(String fileName){  
            String result = "";  
                try {  
                    InputStream in = getResources().getAssets().open(fileName);  
                    int lenght = in.available();  
                    byte[]  buffer = new byte[lenght];  
                    in.read(buffer);  
                    result = EncodingUtils.getString(buffer, ENCODING);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                return result;  
        } 
        String TAG="MainPoetry";
        protected void onStart() {  
            super.onStart();  
            Log.i(TAG, "start onStart~~~");  
        }  
          
         
        protected void onRestart() {  
            super.onRestart();  
            Log.i(TAG, "start onRestart~~~");  
        }  
          
         
        protected void onResume() {  
            super.onResume();  
            Log.i(TAG, "start onResume~~~");  
        }  
          
         
        protected void onPause() {  
            super.onPause();  
            Log.i(TAG, "start onPause~~~");  
        }  
          
         
        protected void onStop() {  
            super.onStop();  
            Log.i(TAG, "start onStop~~~");  
        }  
          
         
        protected void onDestroy() {  
            super.onDestroy();  
            Log.i(TAG, "start onDestroy~~~"); 
            m.stop();	
            
        }  
          


    }  
    
    
     
 
  
    

