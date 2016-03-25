package com.y2w.uikit.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Ŀǰ��û���ṩ�Զ������ȼ����̳߳�
 * ���������̳߳�
 * ִ�����ȼ��ֱ���executUI > executNet > executLow
 *
 * @author maie
 *
 */
public class ThreadPool {
	private ExecutorService uiExecutorService;
	private ExecutorService netExecutorService;
	private ExecutorService lowExecutorService;

	private final int maxThreadNum = 5;

	private static ThreadPool threadPool;

	private ThreadPool(){

	}

	public static ThreadPool getThreadPool(){
		if(threadPool == null){
			threadPool = new ThreadPool();
		}
		return threadPool;
	}

	private void initNetExecutorService(){
		if(netExecutorService == null){
			ThreadFactory factory = new ThreadFactory() {

				@Override
				public Thread newThread(Runnable arg0) {
					Thread thread = new Thread(arg0);
					thread.setPriority(Thread.NORM_PRIORITY - 1);
					android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_FOREGROUND + 2);
					return thread;
				}
			};
			netExecutorService = Executors.newFixedThreadPool(maxThreadNum,factory);
		}
	}

	/**
	 * ��Ҫ������������
	 *
	 * @param netRunable
	 */
	public void executNet(Runnable netRunable){
		if(netRunable == null){
			return;
		}
		initNetExecutorService();
		netExecutorService.submit(netRunable);
	}

	private void initUIExecutorService(){
		if(uiExecutorService == null){
			ThreadFactory factory = new ThreadFactory() {

				@Override
				public Thread newThread(Runnable arg0) {
					Thread thread = new Thread(arg0);
					thread.setPriority(Thread.NORM_PRIORITY + 1);
					android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_FOREGROUND + 1);
					return thread;
				}
			};
			uiExecutorService = Executors.newFixedThreadPool(maxThreadNum,factory);
		}
	}

	/**
	 * ����android ��UI�̣߳�ֻ�Ǳ�ʶ���ȼ����������̳߳أ���Ҫ���������ٵ�UI����ͱ��صĲ���
	 *
	 * @param uiRunable
	 */
	public void executUI(Runnable uiRunable){
		if(uiRunable == null){
			return;
		}
		initUIExecutorService();
		uiExecutorService.submit(uiRunable);
	}

	private void initLowExecutorService(){
		if(lowExecutorService == null){
			ThreadFactory factory = new ThreadFactory() {

				@Override
				public Thread newThread(Runnable arg0) {
					Thread thread = new Thread(arg0);
					thread.setPriority(Thread.NORM_PRIORITY - 2);
					android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
					return thread;
				}
			};
			lowExecutorService = Executors.newFixedThreadPool(maxThreadNum,factory);
		}
	}

	/**
	 * �������ȼ��͵��̲߳���
	 * 
	 * @param lowRunable
	 */
	public void executLow(Runnable lowRunable){
		if(lowRunable == null){
			return;
		}
		initLowExecutorService();
		lowExecutorService.submit(lowRunable);
	}
}
