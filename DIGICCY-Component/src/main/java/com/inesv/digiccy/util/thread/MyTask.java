package com.inesv.digiccy.util.thread;

public abstract class MyTask<T> {
	protected TaskListner<T> listner;
	public abstract void run();
	public void setListner(TaskListner<T> listner) {
		this.listner = listner;
	}
	public TaskListner<T> getlistner() {
		return listner;
	}
}
