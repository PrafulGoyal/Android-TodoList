package com.example.taskmanager;


import com.example.taskmanager.adapter.TaskListAdapter;
import com.example.taskmanager.tasks.Task;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends ListActivity {
	    private Button addButton;
		private TaskManagerApplication app;
		private TaskListAdapter adapter;
		private Button removeButton;

		/** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        setUpViews();
	        
	        app = (TaskManagerApplication) getApplication();
	        adapter = new TaskListAdapter(this, app.getCurrentTasks());
	        setListAdapter(adapter);
	    }

		@Override
		protected void onResume() {
			super.onResume();
			adapter.forceReload();
		}

		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			adapter.toggleTaskCompleteAtPosition(position);
			Task t = adapter.getItem(position);
			app.saveTask(t);
		}
		
		protected void removeCompletedTasks() {
			Long[] ids = adapter.removeCompletedTasks();
			app.deleteTasks(ids);
		}
		
		private void setUpViews() {
			addButton = (Button)findViewById(R.id.add_button);
			removeButton = (Button)findViewById(R.id.remove_button);
			
			addButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
					startActivity(intent);
				}
			});
			removeButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					removeCompletedTasks();
				}
			});

		}
	}