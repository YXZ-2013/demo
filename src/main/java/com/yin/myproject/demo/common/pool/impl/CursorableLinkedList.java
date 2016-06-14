package com.yin.myproject.demo.common.pool.impl;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CursorableLinkedList<E> implements List<E>, Serializable {

	private static final long serialVersionUID = 1L;
	/* 集合中元素的个数 */
	protected transient int _size = 0;
	/* 记录修改的次数 */
	protected transient int _modCount = 0;
	/**/
	protected transient Listable<E> _head = new Listable<E>(null, null, null);
	/**/
	protected transient List<WeakReference<Cursor>> _cursors = new ArrayList<WeakReference<Cursor>>();

	/**
	 * empty constructor
	 */
	public CursorableLinkedList() {
	}

	public int size() {
		return _size;
	}

	public boolean isEmpty() {
		return (0 == _size);
	}

	public boolean contains(Object o) {
		for (Listable<E> elt = _head.next(), past = null; null != elt
				&& past != _head.prev(); elt = (past = elt).next()) {
			if ((null == o && null == elt.value()) || (o != null && o.equals(elt.value()))) {
				return true;
			}
		}
		return false;
	}

	public Iterator<E> iterator() {
		return listIterator(0);
	}

	public Object[] toArray() {
		Object[] array = new Object[_size];
		int i = 0;
		for (Listable<E> elt = _head.next(), past = null; null != elt
				&& past != _head.prev(); elt = (past = elt).next()) {
			array[i++] = elt.value();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < _size) {
			a = (T[]) Array.newInstance(a.getClass().getComponentType(), _size);
		}
		int i = 0;
		for (Listable<E> elt = _head._next, past = null; null != elt
				&& past != _head.prev(); elt = (past = elt).next()) {
			a[i++] = (T) elt.value();
		}
		if (a.length > _size) {
			a[_size] = null;
		}
		return a;
	}

	public boolean add(E e) {
		insertListable(_head.prev(), null, e);
		return true;
	}

	public boolean remove(Object o) {
		for (Listable<E> elt = _head.next(), past = null; null != elt
				&& past != _head.prev(); elt = (past = elt).next()) {
			if (null == o && null == elt.value()) {
				removeListable(elt);
				return true;
			} else if (o != null && o.equals(elt.value())) {
				removeListable(elt);
				return true;
			}
		}
		return false;
	}

	private void removeListable(Listable<E> elt) {
		_modCount++;
		_size--;
		if (_head.next() == elt) {
			_head.setNext(elt.next());
		}
		if (null != elt.next()) {
			elt.next().setPrev(elt.prev());
		}
		if (_head.prev() == elt) {
			_head.setPrev(elt.prev());
		}
		if (null != elt.prev()) {
			elt.prev().setNext(elt.next());
		}
		broadcastListableRemoved(elt);
	}

	private void broadcastListableRemoved(Listable<E> elt) {
		Iterator<WeakReference<Cursor>> it = _cursors.iterator();
		while (it.hasNext()) {
			WeakReference<Cursor> ref = it.next();
			Cursor cursor = ref.get();
			if (cursor == null) {
				it.remove();
			} else {
				cursor.listableRemoved(elt);
			}
		}
	}

	public boolean containsAll(Collection<?> c) {
		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			if (!this.contains(it.next())) {
				return false;
			}
		}
		return true;
	}

	public boolean addAll(Collection<? extends E> c) {
		if (c.isEmpty()) {
			return false;
		}
		Iterator<? extends E> iterator = c.iterator();
		while (iterator.hasNext()) {
			insertListable(_head.prev(), null, iterator.next());
		}
		return true;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		if (c.isEmpty()) {
			return false;
		}
		Iterator<? extends E> it = c.iterator();
		while (it.hasNext()) {
			insertListable(_head.prev(), null, it.next());
		}
		return true;
	}

	public boolean removeAll(Collection<?> c) {
		if (0 == c.size() || 0 == _size) {
			return false;
		} else {
			boolean changed = false;
			Iterator<?> it = iterator();
			while (it.hasNext()) {
				if (c.contains(it.next())) {
					it.remove();
					changed = true;
				}
			}
			return changed;
		}
	}

	public boolean retainAll(Collection<?> c) {
		boolean changed = false;
		Iterator<?> it = iterator();
		while (it.hasNext()) {
			if (!c.contains(it.next())) {
				it.remove();
				changed = true;
			}
		}
		return changed;
	}

	public void clear() {
		Iterator<E> it = iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	public E get(int index) {
		return getListableAt(index).value();
	}

	public E set(int index, E element) {
		Listable<E> elt = getListableAt(index);
		E val = elt.setVlaue(element);
		broadcastListableChanged(elt);
		return val;
	}

	private void broadcastListableChanged(Listable<E> elt) {
		Iterator<WeakReference<Cursor>> it = _cursors.iterator();
		while (it.hasNext()) {
			WeakReference<Cursor> ref = it.next();
			Cursor cursor = ref.get();
			if (cursor == null) {
				it.remove();
			} else {
				cursor.listableChanged(elt);
			}
		}
	}

	public void add(int index, E element) {
		if (index == _size) {
			add(element);
		} else {
			if (index < 0 || index > _size) {
				throw new IndexOutOfBoundsException(
						String.valueOf(index) + " < 0 or " + String.valueOf(index) + " > " + _size);
			}
			Listable<E> succ = (isEmpty() ? null : getListableAt(index));
			Listable<E> pred = (null == succ ? null : succ.prev());
			insertListable(pred, succ, element);
		}
	}

	public E remove(int index) {
		Listable<E> elt = getListableAt(index);
		E ret = elt.value();
		removeListable(elt);
		return ret;
	}

	public int indexOf(Object o) {
		int ndx = 0;
		if (null == o) {
			for (Listable<E> elt = _head.next(), past = null; null != elt
					&& past != _head.prev(); elt = (past = elt).next()) {
				if (null == elt.value()) {
					return ndx;
				}
				ndx++;
			}
		} else {
			for (Listable<E> elt = _head.next(), past = null; null != elt
					&& past != _head.prev(); elt = (past = elt).next()) {
				if (o.equals(elt.value())) {
					return ndx;
				}
				ndx++;
			}
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		int ndx = _size - 1;
		if (null == o) {
			for (Listable<E> elt = _head.prev(), past = null; null != elt
					&& past != _head.next(); elt = (past = elt).prev()) {
				if (null == elt.value()) {
					return ndx;
				}
				ndx--;
			}
		} else {
			for (Listable<E> elt = _head.prev(), past = null; null != elt
					&& past != _head.next(); elt = (past = elt).prev()) {
				if (o.equals(elt.value())) {
					return ndx;
				}
				ndx--;
			}
		}
		return -1;
	}

	public ListIterator<E> listIterator() {
		return listIterator(0);
	}

	public ListIterator<E> listIterator(int index) {
		if (index < 0 || index > _size) {
			throw new IndexOutOfBoundsException(index + " < 0 or > " + _size);
		}
		return new ListIter(index);
	}

	public List<E> subList(int i, int j) {
		// TODO:
		return null;
		// if (i < 0 || j > _size || i > j) {
		// throw new IndexOutOfBoundsException();
		// } else if (i == 0 && j == _size) {
		// return this;
		// } else {
		// return new CursorableSubList<E>(this, i, j);
		// }
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
	 * 
	 * @param before
	 * @param after
	 * @param value
	 * @return
	 */
	protected Listable<E> insertListable(Listable<E> before, Listable<E> after, E value) {
		_modCount++;
		_size++;
		Listable<E> elt = new Listable<E>(before, after, value);
		if (null != before) {
			before.setNext(elt);
		} else {
			_head.setNext(elt);
		}

		if (null != after) {
			after.setPrev(elt);
		} else {
			_head.setPrev(elt);
		}
		broadcastListableInserted(elt);
		return elt;
	}

	protected void registerCursor(Cursor cur) {
		for (Iterator<WeakReference<Cursor>> it = _cursors.iterator(); it.hasNext();) {
			WeakReference<Cursor> ref = it.next();
			if (ref.get() == null) {
				it.remove();
			}
		}
		_cursors.add(new WeakReference<Cursor>(cur));
	}

	protected void unregisterCursor(Cursor cur) {
		for (Iterator<WeakReference<Cursor>> it = _cursors.iterator(); it.hasNext();) {
			WeakReference<Cursor> ref = it.next();
			Cursor cursor = ref.get();
			if (cursor == null) {
				it.remove();
			} else if (cursor == cur) {
				ref.clear();
				it.remove();
				break;
			}
		}
	}

	/**
	 * 向。。。通知有特定元素添加进集合,
	 * 
	 * @param elt
	 */
	protected void broadcastListableInserted(Listable<E> elt) {
		Iterator<WeakReference<Cursor>> it = _cursors.iterator();
		while (it.hasNext()) {
			WeakReference<Cursor> ref = it.next();
			Cursor cursor = ref.get();
			if (cursor == null) {
				it.remove();
			} else {
				cursor.listableInserted(elt);
			}
		}
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
			checkForComod();
			return (null != _cur.next() && _cur.prev() != _head.prev());
		}

		public E next() {
			checkForComod();
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				E ret = _cur.next().value();
				_lastReturned = _cur.next();
				_cur.setPrev(_cur.next());
				_cur.setNext(_cur.next().next());
				_nextIndex++;
				return ret;
			}
		}

		public boolean hasPrevious() {
			checkForComod();
			return (null != _cur.prev() && _cur.next() != _head.next());
		}

		public E previous() {
			checkForComod();
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			} else {
				E ret = _cur.prev().value();
				_lastReturned = _cur.prev();
				_cur.setNext(_cur.prev());
				_cur.setPrev(_cur.prev().prev());
				_nextIndex--;
				return ret;
			}
		}

		public int nextIndex() {
			checkForComod();
			if (!hasNext()) {
				return size();
			}
			return _nextIndex;
		}

		public int previousIndex() {
			checkForComod();
			if (!hasPrevious()) {
				return -1;
			}
			return _nextIndex - 1;
		}

		public void remove() {
			checkForComod();
			if (null == _lastReturned) {
				throw new IllegalStateException();
			} else {
				_cur.setNext(_lastReturned == _head.prev() ? null : _lastReturned.next());
				_cur.setPrev(_lastReturned == _head.next() ? null : _lastReturned.prev());
				_lastReturned = null;
				_nextIndex--;
				_expectedModCount++;
			}
		}

		public void set(E e) {
			checkForComod();
			try {
				_lastReturned.setVlaue(e);
			} catch (NullPointerException ex) {
				throw new IllegalSelectorException();
			}
		}

		public void add(E e) {
			checkForComod();
			Listable<E> elt = insertListable(_cur.prev(), _cur.next(), e);
			_cur.setPrev(elt);
			_cur.setNext(elt.next());
			_lastReturned = null;
			_nextIndex++;
			_expectedModCount++;

		}

		protected void checkForComod() {
			if (_expectedModCount != _modCount) {
				throw new ConcurrentModificationException();
			}
		}

	}

	public class Cursor extends ListIter implements ListIterator<E> {
		boolean _valid = false;

		Cursor(int index) {
			super(index);
			_valid = true;
			registerCursor(this);
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(E e) {
			checkForComod();
			Listable<E> elt = insertListable(_cur.prev(), _cur.next(), e);
			_cur.setPrev(elt);
			_cur.setNext(elt.next());
			_lastReturned = null;
			_nextIndex++;
			_expectedModCount++;
		}

		protected void listableRemoved(Listable<E> elt) {
			if (null == _head.prev()) {
				_cur.setNext(null);
			} else if (_cur.next() == elt) {
				_cur.setNext(elt.next());
			}
			if (null == _head.next()) {
				_cur.setPrev(null);
			} else if (_cur.prev() == elt) {
				_cur.setPrev(elt.prev());
			}
			if (_lastReturned == elt) {
				_lastReturned = null;
			}
		}

		protected void listableInserted(Listable<E> elt) {
			if (null == _cur.next() && null == _cur.prev()) {
				_cur.setNext(elt);
			} else if (_cur.prev() == elt.prev()) {
				_cur.setNext(elt);
			}
			if (_cur.next() == elt.next()) {
				_cur.setPrev(elt);
			}
			if (_lastReturned == elt) {
				_lastReturned = null;
			}
		}

		protected void listableChanged(Listable<E> elt) {
			if (_lastReturned == elt) {
				_lastReturned = null;
			}
		}

		@Override
		protected void checkForComod() {
			if (!_valid) {
				throw new ConcurrentModificationException();
			}
		}

		protected void invalidate() {
			_valid = false;
		}

		public void close() {
			if (_valid) {
				_valid = false;
				unregisterCursor(this);
			}
		}

	}
}
