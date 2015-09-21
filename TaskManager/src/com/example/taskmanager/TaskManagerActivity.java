package com.example.taskmanager;

import android.app.Activity;

public class TaskManagerActivity extends Activity {
	public TaskManagerActivity() {
		super();
	}

	protected TaskManagerApplication getStuffApplication() {
		return (TaskManagerApplication)getApplication();
	}
	protected TaskManagerApplication getTaskManagerApplication() {
		TaskManagerApplication tma = (TaskManagerApplication)getApplication();
		return tma;
	}
}


