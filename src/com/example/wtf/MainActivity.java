package com.example.wtf;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.example.objLoader.ObjLoaderApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		AdapterView.OnItemClickListener {

	private String Modelpath = new String(Environment
			.getExternalStorageDirectory().getAbsolutePath());

	private List<String> item = null;
	private List<String> path = null;
	public ListView Modellist1;
	private TextView mypath;
	ArrayAdapter<String> listAdapter;
	TextView currentDirText;
	TextView numFilesText;
	Button backButton;
	String rootDir = "/mnt/";
	String directoryName = rootDir;
	Button uploadButton;
	Button DeleteButton;
	File file;
	ArrayList<File> allFiles;
	String viewmodel ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setTitleColor(Color.rgb(65, 183, 216));

		item = new ArrayList<String>();

		allFiles = walkdir(new File("/mnt/"));

		for (int i = 0; i < allFiles.size(); i++) {

			item.add(allFiles.get(i).getName());

		}

		listAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, item);
		Modellist1 = (ListView) findViewById(R.id.Modellist);
		Modellist1.setAdapter(listAdapter);
		Modellist1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> l, View v, int position,
					long id) {
				/*
				 * Object downloadFile = l.getItemAtPosition(position); // get
				 * item at clicked position in list of files
				 */viewmodel = allFiles.get(position).getPath();
				
				Intent ii = new Intent(MainActivity.this, ModelViewActivity.class);
				ii.putExtra("downloadFile", viewmodel);
				startActivity(ii);
			}
		});

		

		DeleteButton = (Button) findViewById(R.id.deletebutton);
		final Intent DeleteIntent = new Intent(this, DeleteFileActivity.class);
		DeleteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.this.startActivity(DeleteIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {

		/*
		 * if(
		 * downloadFile.toString().toLowerCase(Locale.getDefault()).endsWith(
		 * ".obj") ||
		 * downloadFile.toString().toLowerCase(Locale.getDefault()).endsWith
		 * (".obj") ||
		 * downloadFile.toString().toLowerCase(Locale.getDefault()).endsWith
		 * (".obj") ||
		 * downloadFile.toString().toLowerCase(Locale.getDefault()).endsWith
		 * (".obj") ) { // Image file, download using ImageViewActivity Intent
		 * viewObjIntent = new Intent(this,ObjLoaderApp.class);
		 * viewObjIntent.putExtra("downloadFile", downloadFile.toString());
		 * 
		 * MainActivity.this.startActivity(viewObjIntent); }
		 */
	}

	public ArrayList<File> walkdir(File dir) {
		String objPattern = ".obj";

		ArrayList<File> files = new ArrayList<File>();

		File[] listFile = dir.listFiles();

		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {

				if (listFile[i].isDirectory()) {
					files.addAll(walkdir(listFile[i]));
				} else {
					if (listFile[i].getName().endsWith(objPattern)) {
						// Do what ever u want
						files.add(listFile[i]);
					}
				}
			}
		}
		return files;
	}

}
