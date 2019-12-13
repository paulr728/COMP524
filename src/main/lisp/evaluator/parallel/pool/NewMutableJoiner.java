package main.lisp.evaluator.parallel.pool;

import main.util.parallel.MutableJoiner;

public class NewMutableJoiner implements MutableJoiner {

	int count = 0;
	@Override
	public synchronized void finished() {
		// TODO Auto-generated method stub
		notify();
		count--;
	}

	@Override
	public void join() throws InterruptedException {
		// TODO Auto-generated method stub
		synchronized(this) {
			while(count > 0) {
				wait();
			}
		}
	}

	@Override
	public void setNumThreads(int c) {
		// TODO Auto-generated method stub
		count = c;
	}

}
