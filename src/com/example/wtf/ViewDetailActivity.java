package com.example.wtf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewDetailActivity extends Activity {
	TextView detail;
	TextView modeldirectory;
	String Name;
	String Detailinfo;
	Button BackButton;
	Button UploadButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model_detail);
		detail = (TextView) findViewById(R.id.DetailView);
		modeldirectory = (TextView) findViewById(R.id.directoryview);
		Intent filepath = getIntent();
		Bundle b = filepath.getExtras();
		 if(b!=null)
	        {
			
	            Detailinfo =(String) b.get("downloadFile");
	            Name = Detailinfo.substring(Detailinfo.lastIndexOf("/"), Detailinfo.length());
	            Name = Name.substring(Name.lastIndexOf("/")+1);
	        }
		 detail.setText("ModelName : " + Name);
		 modeldirectory.setText("Model Directory : " + Detailinfo);
		 BackButton = (Button) findViewById(R.id.Backtomodelbutton);
		 final Intent BackIntent = new Intent(this, ModelViewActivity.class);
		 BackIntent.putExtra("downloadFile", Detailinfo);
		    BackButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ViewDetailActivity.this.startActivity(BackIntent);
					
				}
		    }); 
		    UploadButton = (Button) findViewById(R.id.UploadFile);
		    final Intent UploadIntent = new Intent(this, UploadToServer.class);
			UploadIntent.putExtra("downloadFile", Detailinfo);
			UploadButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ViewDetailActivity.this.startActivity(UploadIntent);
					
				}
			});
		    }
	
}
