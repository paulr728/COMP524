package main.lisp.evaluator.parallel.pool;

import main.util.parallel.Joiner;

public class BasicJoiner implements Joiner {

	private int count = 0;
	public BasicJoiner() {
		
	}
	public BasicJoiner(int N)
	{
		count = N;
	}
	@Override
	public synchronized void finished() {
		// TODO Auto-generated method stub
		notify();
		count--;
	}

	@Override
	public void join() throws InterruptedException {
		synchronized(this) {
			while(count > 0) {
				wait();
			}
		}
	}

}
