package com.example.wtf;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DeleteFileActivity extends Activity {
	ListView lsv;
	private List<String> item = null;
	ArrayAdapter<String> listAdapter;
	ArrayList<File> allFiles;
	File file;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		item = new ArrayList<String>();

		allFiles = walkdir(new File("/mnt/"));
//		file = new File("/mnt/shared/Obj-model/");
//		File list[] = file.listFiles(new ModelFilter());
		//Toast.makeText(getApplicationContext(), Environment.getDataDirectory().getPath(),
			//	Toast.LENGTH_SHORT).show();
		for (int i = 0; i < allFiles.size(); i++) {

			item.add(allFiles.get(i).getName());

		}
		final ListView lsv = (ListView) findViewById(R.id.lsvMain);
	
		listAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, item);
		lsv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		lsv.setAdapter(listAdapter);

		Button cmd_SelectAll = (Button) findViewById(R.id.cmdSelectAll);
		Button cmd_Clear = (Button) findViewById(R.id.cmdClear);
		Button cmd_Check = (Button) findViewById(R.id.cmdCheck);
		Button cmd_Delete = (Button) findViewById(R.id.cmdDelete);
		cmd_SelectAll.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i < lsv.getCount(); i++)
					lsv.setItemChecked(i, true);
			}
		});
		cmd_Clear.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i < lsv.getCount(); i++)
					lsv.setItemChecked(i, false);
			}
		});
		cmd_Check.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String str = "";
				int AllListCounts = lsv.getCount();
				SparseBooleanArray arrBool = lsv.getCheckedItemPositions();
				for (int i = 0; i < AllListCounts; i++) {
					if (arrBool.get(i)) {
						str += lsv.getItemAtPosition(i).toString() + "\n";
					}
				}
				if (str != "") {
					Toast.makeText(getApplicationContext(), str,
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "None Selected",
							Toast.LENGTH_SHORT).show();
				}
			}

		});
		cmd_Delete.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String str = "/mnt/shared/Obj-model/";
				Toast.makeText(getApplicationContext(), "Selected",
						Toast.LENGTH_SHORT).show();
				String Checkstr[] = new String[lsv.getCount()];
				int AllListCounts = lsv.getCount();
				SparseBooleanArray arrBool = lsv.getCheckedItemPositions();
				
				boolean noneSelected = true;
				for (int i = 0; i < AllListCounts; i++) {
					if (arrBool.get(i)) {
						noneSelected = false;
//						str = "/mnt/shared/Obj-model/"
//								+ lsv.getItemAtPosition(i).toString();
//						Checkstr[i] = str;
//						Toast.makeText(getApplicationContext(), str,
//								Toast.LENGTH_SHORT).show();
						File file = allFiles.get(i);
						
						boolean deleted = file.delete();
						
						
					}
				}

//				if (Checkstr.length > 0) {
//					Toast.makeText(getApplicationContext(),
//							"if:" + Checkstr[0], Toast.LENGTH_SHORT).show();
//					for (int i = 0; i < Checkstr.length; i++) {
//						if (Checkstr[i] != null) {
//							File file = allFiles.get(i);
//							boolean deleted = file.delete();
//							Toast.makeText(getApplicationContext(),
//									deleted + "", Toast.LENGTH_SHORT).show();
//						}
//					}
//
//				} 
			if(noneSelected) {

					Toast.makeText(getApplicationContext(), "None Selected!!",
							Toast.LENGTH_SHORT).show();
				}
				Checkstr = null;
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

	}

	public class ModelFilter implements FileFilter {

		// only want to see the following audio file types
		private String[] extension = { ".obj" };

		@Override
		public boolean accept(File pathname) {

			if (pathname.isDirectory())
				return false;
			// if we are looking at a directory/file that's not hidden we
			// want to see it so return TRUE
			if (pathname.isDirectory() && !pathname.isHidden()) {
				return true;
			}

			// loops through and determines the extension of all files in
			// the directory
			// returns TRUE to only show the audio files defined in the
			// String[] extension array
			for (String ext : extension) {
				if (pathname.getName().toLowerCase().endsWith(ext)) {
					return true;
				}
			}

			return false;
		}
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
