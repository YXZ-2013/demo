package com.yin.myproject.demo.common.pool;

public abstract class BaseObjectPool<T> implements ObjectPool<T> {

	public abstract T borrowObject() throws Exception;

	public abstract void returnObject(T obj) throws Exception;

	public abstract void invalidateObject(T obj) throws Exception;

	public int getNumIdle() throws UnsupportedOperationException {
		return -1;
	}

	public int getNumActive() throws UnsupportedOperationException {
		return -1;
	}

	public void clear() throws Exception, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public void addObject() throws Exception, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public void close() throws Exception {
		closed = true;
	}

	public final boolean isClosed() {
		return closed;
	}

	protected final void assertOpen() throws IllegalStateException {
		if (isClosed()) {
			throw new IllegalStateException("Pool not open");
		}
	}

	/** Whether or not the pool is closed */
	private volatile boolean closed = false;

}
