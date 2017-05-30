package com.sea.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ExecutionPool {
	private static final ScheduledExecutorService cleaner=Executors.newSingleThreadScheduledExecutor();
	private static final Map<Integer,CommandExecutor> pool=new HashMap<>();
	public static ExecutionPool instance=new ExecutionPool();
	private ExecutionPool(){
		init();
	}
	public void init(){
		cleaner.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Iterator<Integer> keys=pool.keySet().iterator();
				while(keys.hasNext()){
					int uid=keys.next();
					CommandExecutor value=pool.get(uid);
					if(value.lastCommitTime< System.currentTimeMillis()-5*60*1000){
						pool.remove(uid);
						value.shutdown();
					}
				}
				
			}
		}, 5, 5, TimeUnit.MINUTES);
	}
	public void execute(int uid,Runnable command){
		CommandExecutor executor=pool.get(uid);
		if(executor==null){
			executor=new CommandExecutor(uid);
			pool.put(uid, executor);
		}
		executor.execute(command);
	}
	class CommandExecutor{
		ScheduledExecutorService executor;
		public CommandExecutor(final int uid){
			executor=Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
				
				@Override
				public Thread newThread(Runnable r) {
					Thread t=new Thread(r);
					t.setName("CommandExecutor-"+uid);
					return t;
				}
			});
		}
		long lastCommitTime;
		public void execute(Runnable command){
			executor.execute(command);
			lastCommitTime=System.currentTimeMillis();
		}
		public void shutdown(){
			executor.shutdown();
		}
		public long getLastCommitTime() {
			return lastCommitTime;
		}
		
	}
}
