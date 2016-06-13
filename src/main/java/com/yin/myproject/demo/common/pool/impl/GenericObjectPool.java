package com.yin.myproject.demo.common.pool.impl;

import java.nio.channels.IllegalSelectorException;
import java.util.LinkedList;
import java.util.TimerTask;

import org.apache.commons.pool.PoolableObjectFactory;

import com.yin.myproject.demo.common.pool.BaseObjectPool;
import com.yin.myproject.demo.common.pool.ObjectPool;

/**
 * 可配置化得对象池实现类
 * 
 * @author XunzhiYin
 *
 * @param <T>
 */
public class GenericObjectPool<T> extends BaseObjectPool<T> implements ObjectPool<T> {

	/**
	 * 常量
	 */
	public static final byte WHEN_EXHAUSTED_FAIL = 0;
	public static final byte WHEN_EXHAUSTED_BLOCK = 1;
	public static final byte WHEN_EXHAYSTED_GROW = 2;
	public static final int DEFAULT_MAX_IDEL = 8;
	public static final int DEFAULT_MIN_IDEL = 0;
	public static final int DEFAULT_MAX_ACTIVE = 8;
	public static final byte DEFAULT_WHEN_EXHAUSTED_ACTION = WHEN_EXHAUSTED_BLOCK;
	public static final boolean DEFAULT_LIFO = true;
	public static final long DEFAULT_MAX_WAIT = -1L;
	public static final boolean DEFAULT_TEST_ON_BORROW = false;
	public static final boolean DEFAULT_TEST_ON_RETURN = false;
	public static final boolean DEFAULT_TEST_WHILE_IDLE = false;
	public static final long DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = -1L;
	public static final int DEFAULT_NUM_TESTS_PER_EVICTION_RUN = 3;
	public static final long DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = 1000L * 60L * 30L;
	public static final long DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS = -1;
	private static final int DEFAULT_MAX_IDLE = 8;
	private static final int DEFAULT_MIN_IDLE = 8;

	/**
	 * 私有变量
	 */
	private int _maxIdle = DEFAULT_MAX_IDEL;
	private int _minIdel = DEFAULT_MIN_IDEL;
	private int _maxActive = DEFAULT_MAX_ACTIVE;
	private long _maxWait = DEFAULT_MAX_WAIT;
	private byte _whenExhaustedAction = DEFAULT_WHEN_EXHAUSTED_ACTION;
	private volatile boolean _testOnBorrow = DEFAULT_TEST_ON_BORROW;
	private volatile boolean _testOnReturn = DEFAULT_TEST_ON_RETURN;
	private boolean _testWhileIdle = DEFAULT_TEST_WHILE_IDLE;
	private long _timeBetweenEvictionRunsMillis = DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
	private int _numTestsPerEvictionRun = DEFAULT_NUM_TESTS_PER_EVICTION_RUN;
	private long _minEvictableIdleTimeMillis = DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
	private long _softMinEvictableIdleTimeMillis = DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
	private boolean _lifo = DEFAULT_LIFO;
	private CursorableLinkedList<ObjectTimestampPair<T>> _pool = null;
	private PoolableObjectFactory<T> _factory = null;
	private int _numActive = 0;
	private Evictor _evictor = null;
	private int _numInternalProcessing = 0;
	private final LinkedList<Latch<T>> _allocationQueue = new LinkedList<Latch<T>>();

	/*** Constructor ***/
	public GenericObjectPool() {
		this(null, DEFAULT_MAX_ACTIVE, DEFAULT_WHEN_EXHAUSTED_ACTION, DEFAULT_MAX_WAIT, DEFAULT_MAX_IDLE,
				DEFAULT_MIN_IDLE, DEFAULT_TEST_ON_BORROW, DEFAULT_TEST_ON_RETURN,
				DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS, DEFAULT_NUM_TESTS_PER_EVICTION_RUN,
				DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS, DEFAULT_TEST_WHILE_IDLE);
	}

	public GenericObjectPool(PoolableObjectFactory<T> factory, int maxActive, byte whenExhaustedAction, long maxWait,
			int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis,
			int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle) {
		this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn,
				timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle,
				DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS);
	}

	public GenericObjectPool(PoolableObjectFactory<T> factory, int maxActive, byte whenExhaustedAction, long maxWait,
			int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis,
			int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle,
			long softMinEvictableIdleTimeMillis) {
		this(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn,
				timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle,
				softMinEvictableIdleTimeMillis, DEFAULT_LIFO);
	}

	public GenericObjectPool(PoolableObjectFactory<T> factory, int maxActive, byte whenExhaustedAction, long maxWait,
			int maxIdle, int minIdle, boolean testOnBorrow, boolean testOnReturn, long timeBetweenEvictionRunsMillis,
			int numTestsPerEvictionRun, long minEvictableIdleTimeMillis, boolean testWhileIdle,
			long softMinEvictableIdleTimeMillis, boolean lifo) {
		_factory = factory;
		_maxActive = maxActive;
		_lifo = lifo;
	}

	@Override
	public T borrowObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void returnObject(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void invalidateObject(T obj) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 一个简单的结构体 封装一个对象实例和时间戳
	 * 
	 * @author XunzhiYin
	 *
	 * @param <T>
	 */
	static class ObjectTimestampPair<T> implements Comparable<T> {

		/**
		 * 对象实例
		 */
		T value;

		/**
		 * 时间戳
		 */
		long tstamp;

		/**
		 * 构造函数
		 * 
		 * @param value
		 *            对象实例
		 */
		public ObjectTimestampPair(T value) {
			this(value, System.currentTimeMillis());
		}

		/**
		 * 构造函数
		 * 
		 * @param value
		 *            对象实例
		 * @param time
		 *            时间戳
		 */
		public ObjectTimestampPair(T value, long time) {
			this.value = value;
			this.tstamp = time;
		}

		@Override
		public String toString() {
			return value + ";" + tstamp;
		}

		public int compareTo(Object obj) {
			return compareTo(obj);
		}

		public int compareTo(ObjectTimestampPair<T> other) {
			final long tstampdiff = this.tstamp - other.tstamp;
			if (tstampdiff == 0) {
				return System.identityHashCode(this) - System.identityHashCode(other);
			} else {
				return (int) Math.min(Math.max(tstampdiff, Integer.MIN_VALUE), Integer.MAX_VALUE);
			}
		}

		public T getValue() {
			return value;
		}

		public long getTstamp() {
			return tstamp;
		}
	}

	/**
	 * Latch(闭锁)用于控制向线程中分配对象的顺序以保证公平性。换言之，根据现象对对象实例的获取来给线程分配对象
	 * 
	 * @author XunzhiYin
	 *
	 * @param <T>
	 */
	private static final class Latch<T> {
		/* 对象实例和时间戳一组分配给Latch */
		private ObjectTimestampPair<T> _pair;

		/* Latch(对象)是否可以创建对象实例 */
		private boolean _mayCreate = false;

		private synchronized ObjectTimestampPair<T> getPair() {
			return _pair;
		}

		private synchronized void setPair(ObjectTimestampPair<T> pair) {
			_pair = pair;
		}

		private synchronized boolean mayCreate() {
			return _mayCreate;
		}

		private synchronized void setMayCreate(boolean mayCreate) {
			_mayCreate = mayCreate;
		}
		
		private synchronized void reset(){
			_pair = null;
			_mayCreate = false;
		}
	}
	
	/**
	 * 用于清除空闲的对象(idle object)
	 * @author XunzhiYin
	 *
	 */
	private class Evictor extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void evict() throws Exception{
		assertOpen();
		synchronized(this){
			
		}
	}
	
	protected final void assertOpen() throws IllegalStateException{
		if(isClosed()){
			throw new IllegalSelectorException("Pool not open");
		}
	}
}
