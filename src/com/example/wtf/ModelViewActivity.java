package com.example.wtf;

import com.example.objLoader.MyRenderer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ModelViewActivity extends Activity {
	String j;
	
	Button Back;
	Button Detail;
	/** The OpenGL View */


	/**
	 * Initiate the OpenGL View and set our own
	 * Renderer (@see Lesson02.java)
	 */
	TextView numFilesText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Debug.startMethodTracing("calc");
		super.onCreate(savedInstanceState);
	
		
		
		Intent filepath = getIntent();
		Bundle b = filepath.getExtras();
		 if(b!=null)
	        {
			
	             j =(String) b.get("downloadFile");
	   
	        }
		 
		setContentView(new MyRenderer(this,j));
		 LayoutInflater inflater = getLayoutInflater();
		    getWindow().addContentView(inflater.inflate(R.layout.activity_model_view, null),
		                              new ViewGroup.LayoutParams(
		                               ViewGroup.LayoutParams.MATCH_PARENT,
		                               ViewGroup.LayoutParams.MATCH_PARENT));
		    numFilesText = (TextView) findViewById(R.id.textView1);
		    Back = (Button) findViewById(R.id.BackButton);
		    final Intent BackIntent = new Intent(this, MainActivity.class);
		    Back.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ModelViewActivity.this.startActivity(BackIntent);
				}
			}); 
		   Detail = (Button) findViewById(R.id.DetailButton);
		   final Intent DetailIntent = new Intent(this,ViewDetailActivity.class);
		   DetailIntent.putExtra("downloadFile", j);
		   Detail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ModelViewActivity.this.startActivity(DetailIntent);
				
			}
		});
		    		
	}

	/**
	 * Remember to resume the glSurface
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * Also pause the glSurface
	 */
	@Override
	protected void onPause() {
		Debug.stopMethodTracing();
		super.onPause();
	}
	@Override
	protected void onDestroy(){
		Debug.stopMethodTracing();
	}

}
