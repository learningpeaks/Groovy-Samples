package wells.listener

import java.util.Observer;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public abstract class Dispatcher implements Observer {
	
	protected ThreadPoolTaskExecutor taskExecutor;
	
	protected static int submissionCount = 0;
	
	public void setTaskExecutor(ThreadPoolTaskExecutor executor) {
		this.taskExecutor = executor;
	}
	
	public void shutdown() {
		while (taskExecutor.getActiveCount() > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		taskExecutor.shutdown();
	}
	
	public int getSubmissionCount(){
		return submissionCount;
	}
}
