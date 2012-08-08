package vincent.net.blogjava;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;


public class Navigater extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigater);
        ListView listView = (ListView) findViewById(R.id.ListView01);  
        listView.setAdapter((ListAdapter) new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        listView.setOnItemClickListener(new OnItemClickListener() {  
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
                    long arg3) {  
            	Intent it = new Intent(Navigater.this, MainPoetry.class);
	                Bundle bundle = new Bundle();  
	                bundle.putString("id", (arg2+1)+"");  
	                it.putExtras(bundle);  
	                startActivityForResult(it, 0);  
	                overridePendingTransition(R.anim.fade, R.anim.hold);
	                
            }  
        });  
        
    }
     
     
     
    private List<String> getData(){
        List<String> data =PoetryManager.getPoetrysName();
        return data;
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        
        Log.i("MyListView4-click", position+"");
    }   

    
}

