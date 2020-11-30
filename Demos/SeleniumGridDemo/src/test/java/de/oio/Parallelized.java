package de.oio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;

public class Parallelized extends Parameterized {
	
	public Parallelized(Class<?> klass) throws Throwable {
		super(klass);
		setScheduler(new ThreadPoolScheduler());
	}

	private static class ThreadPoolScheduler implements RunnerScheduler {
		private ExecutorService executor;

		public ThreadPoolScheduler() {
			executor = Executors.newFixedThreadPool(3);
		}

		public void finished() {
			executor.shutdown();
			try {
				executor.awaitTermination(10, TimeUnit.MINUTES);
			} catch (InterruptedException exc) {
				throw new RuntimeException(exc);
			}
		}

		public void schedule(Runnable childStatement) {
			executor.submit(childStatement);
		}
	}
}