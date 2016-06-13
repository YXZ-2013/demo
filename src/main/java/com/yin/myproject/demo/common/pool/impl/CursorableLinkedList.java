package com.yin.myproject.demo.common.pool.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.pool.impl.CursorableLinkedList.Listable;

class CursorableLinkedList<E> implements List<E>, Serializable {

	private static final long serialVersionUID = 1L;
	/* 集合中元素的个数 */
	protected transient int _size = 0;
	/* 记录修改的次数 */
	protected transient int _modCount = 0;
	/**/
	protected transient Listable<E> _head = new Listable<E>(null, null, null);

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(int index, E element) {
		// TODO Auto-generated method stub

	}

	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回特定索引位置的Listable对象
	 * 
	 * @param index
	 * @return
	 */
	protected Listable<E> getListableAt(int index) {
		if (index < 0 || index >= _size) {
			throw new IndexOutOfBoundsException(
					String.valueOf(index) + " < 0 or " + String.valueOf(index) + " >= " + _size);
		}
		if (index <= _size / 2) {
			Listable<E> elt = _head.next();
			for (int i = 0; i < index; i++) {
				elt = elt.next();
			}
			return elt;
		} else {
			Listable<E> elt = _head.prev();
			for (int i = (_size - 1); i > index; i--) {
				elt = elt.prev();
			}
			return elt;
		}
	}
	
	/**
	 * 在集合中插入新的对象
	 * @param before
	 * @param after
	 * @param value
	 * @return
	 */
	protected Listable<E> insertListable(Listable<E> before,Listable<E> after,E value){
		_modCount++;
		_size++;
		Listable<E> elt = new Listable<E>(before, after, value);
		if(null != before){
			before.setNext(elt);
		}else{
			_head.setPrev(elt);
		}
		
		if(null != after){
			after.setPrev(elt);
		}else{
			_head.setPrev(elt);
		}
		broadcastListableInserted(elt);
		return elt;
	}
	
	/**
	 * 向。。。通知有特定元素添加进集合,
	 * @param elt
	 */
	 protected void broadcastListableInserted(Listable<E> elt) {
		 
	 }

	/**
	 * 内部类
	 */

	/**
	 * 定义Listable对象,用来存放对象节点(包含当前元素,前置节点以及后置节点)
	 * 
	 * @author XunzhiYin
	 *
	 * @param <E>
	 */
	static class Listable<E> implements Serializable {
		private static final long serialVersionUID = 1L;

		private Listable<E> _prev = null;
		private Listable<E> _next = null;
		private E _val = null;

		Listable(Listable<E> prev, Listable<E> next, E val) {
			_prev = prev;
			_next = next;
			_val = val;
		}

		Listable<E> next() {
			return _next;
		}

		Listable<E> prev() {
			return _prev;
		}

		E value() {
			return _val;
		}

		void setNext(Listable<E> next) {
			_next = next;
		}

		void setPrev(Listable<E> prev) {
			_prev = prev;
		}

		E setVlaue(E val) {
			E temp = _val;
			_val = val;
			return temp;
		}
	}

	/**
	 * 自定义迭代器
	 * 
	 * @author XunzhiYin
	 *
	 */
	class ListIter implements ListIterator<E> {
		Listable<E> _cur = null;
		Listable<E> _lastReturned = null;
		int _expectedModCount = _modCount;
		int _nextIndex = 0;

		ListIter(int index) {
			if (index == 0) {
				if (index == 0) {
					_cur = new Listable<E>(null, _head.next(), null);
					_nextIndex = 0;
				} else if (index == _size) {
					_cur = new Listable<E>(_head.prev(), null, null);
					_nextIndex = _size;
				} else {
					Listable<E> temp = getListableAt(index);
					_cur = new Listable<E>(temp.prev(), temp, null);
					_nextIndex = index;
				}
			}
		}

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		public E next() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}

		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		public void remove() {
			// TODO Auto-generated method stub

		}

		public void set(E e) {
			// TODO Auto-generated method stub

		}

		public void add(E e) {
			checkForComod();
			Listable<E> elt = insertListable(_cur.prev(),_cur.next(),e);
			_cur.setPrev(elt);
			_cur.setNext(elt.next());
			_lastReturned = null;
			_nextIndex++;
			_expectedModCount++;
			
		}
		
		protected void checkForComod(){
			if(_expectedModCount != _modCount){
				throw new ConcurrentModificationException();
			}
		}

	}
}