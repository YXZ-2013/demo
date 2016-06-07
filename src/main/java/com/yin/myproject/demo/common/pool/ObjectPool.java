package com.yin.myproject.demo.common.pool;

import java.util.NoSuchElementException;

/**
 * 通用对象池接口
 * @description 定义一个简单的对象池接口
 * 
 * @author Administrator
 * @param <T>
 */
public interface ObjectPool<T> {

	/**
	 * 从对象池中获取一个对象实例
	 * @return
	 * @throws Exception
	 * @throws NoSuchElementException
	 * @throws IllegalStateException
	 */
	T borrowObject() throws Exception, NoSuchElementException,IllegalStateException;

	/**
	 * 向对象池中添加一个对象
	 * @param obj
	 * @throws Exception
	 */
	void returnObject(T obj) throws Exception;

	/**
	 * 从对象池中删除一个对象
	 * @param obj
	 * @throws Exception
	 */
	void invalidateObject(T obj) throws Exception;

	/**
	 * 使用PoolableObjectFactory factory 或者其他实现类创建一个对象
	 * @throws Exception
	 * @throws IllegalStateException
	 * @throws UnsupportedOperationException
	 */
	void addObject() throws Exception, IllegalStateException,UnsupportedOperationException;

	/**
	 * 返回对象实例的个数
	 * @return
	 * @throws UnsupportedOperationException
	 */
	int getNumIdle() throws UnsupportedOperationException;

	/**
	 * 返回对象实例的个数
	 * @return
	 * @throws UnsupportedOperationException
	 */
	int getNumActive() throws UnsupportedOperationException;

	/**
	 * 清空对象池中所有闲置的对象，并释放所有与之相关的资源
	 * @throws Exception
	 * @throws UnsupportedOperationException
	 */
	void clear() throws Exception, UnsupportedOperationException;

	/**
	 * 关闭对象池，并释放与之相关的所有资源
	 * @throws Exception
	 */
	void close() throws Exception;

}
