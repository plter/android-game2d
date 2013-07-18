package com.plter.lib.java.lang;

/**
 * 该数组使用链表实现，高效、安全
 * @author plter
 *
 * @param <T>
 */
public class Array<T> {


	public Array() {
		clear();
	}
	
	
	public final void each(ArrayLoopCallback<T> callback){
		
		ArrayIterator<T> it = begin().nextItem();
		callback.setBreaked(false);
		
		while(it!=end()){
			callback.onRead(it.value());
			if (callback.isBreaked()) {
				break;
			}
			
			it = it.nextItem();
		}
	}
	

	/**
	 * 根据索引获取一项
	 * @param index
	 * @return
	 */
	public final T get(int index){
		ArrayIterator<T> i = getItem(index);
		return i!=null?i.value():null;
	}
	
	/**
	 * 判断数组中是否包含某项
	 * @param obj
	 * @return
	 */
	public final boolean contains(T obj){
		return indexOf(obj)>-1;
	}
	

	/**
	 * 获取某项所在的索引
	 * @param obj
	 * @return
	 */
	public final int indexOf(T obj){
		
		ArrayIterator<T> tmp = _begin;
		
		for (int i = 0; i < length(); i++) {
			tmp = tmp.nextItem();
			
			if (tmp.value()==obj) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * 添加一项
	 * @param obj
	 * @return
	 */
	public final T push(T obj){
		addItemBefore(new ArrayIterator<T>(obj), _end);
		return obj;
	}

	/**
	 * 插入一项
	 * @param obj
	 * @param index
	 * @return
	 */
	public final T insert(T obj,int index){
		ArrayIterator<T> tmp = getItem(index);
		if (tmp!=null) {
			ArrayIterator<T> itemToAdd = new ArrayIterator<T>(obj);
			addItemBefore(itemToAdd, tmp);
			return obj;
		}
		return null;
	}
	
	
	/**
	 * 删除最后一项
	 * @return
	 */
	public final T pop(){
		ArrayIterator<T> tmp = _end.preItem();
		removeItem(tmp);
		return tmp.value();
	}
	
	
	/**
	 * 删除第一项
	 * @return
	 */
	public final T shift(){
		ArrayIterator<T> tmp = _begin.nextItem();
		removeItem(tmp);
		return tmp.value();
	}

	/**
	 * 删除其中一项
	 * @param obj
	 * @return
	 */
	public final T remove(T obj){
		
		ArrayIterator<T> tmp = _begin;
		
		while(tmp.nextItem()!=null){
			tmp = tmp.nextItem();
			if (tmp.value()==obj) {
				removeItem(tmp);
				return obj;
			}
		}
		
		return null;
	}

	/**
	 * 删除指定索引处的该项
	 * @param index
	 * @return
	 */
	public final T remove(int index){
		ArrayIterator<T> i = getItem(index);
		if (i!=null) {
			removeItem(i);
			return i.value();
		}
		return null;
	}

	public final void clear(){
		_begin.setNextItem(_end);
		_end.setPreItem(_begin);
		_length = 0;
	}

	public final int length(){
		return _length;
	}
	
	public final ArrayIterator<T> begin(){
		return _begin;
	}
	
	public final ArrayIterator<T> end(){
		return _end;
	}
	
	private void removeItem(ArrayIterator<T> item){
		item.nextItem().setPreItem(item.preItem());
		item.preItem().setNextItem(item.nextItem());
		_length--;
	}

	private void addItemBefore(ArrayIterator<T> itemToAdd,ArrayIterator<T> item){
		itemToAdd.setNextItem(item);
		itemToAdd.setPreItem(item.preItem());
		itemToAdd.preItem().setNextItem(itemToAdd);
		itemToAdd.nextItem().setPreItem(itemToAdd);
		_length++;
	}
	
	private ArrayIterator<T> getItem(int index){
		
		if (index>=length()||index<0) {
			return null;
		}
		
		ArrayIterator<T> tmp = null;
		
		if (index<length()/2) {
			tmp = _begin;
			for (int i = 0; i <= index; i++) {
				tmp = tmp.nextItem();
			}
		}else{
			tmp = _end;
			for (int i = length(); i > index; i--) {
				tmp = tmp.preItem();
			}
		}
		
		return tmp;
	}

	private int _length = 0;
	private final ArrayIterator<T> _begin = new ArrayIterator<T>(null);
	private final ArrayIterator<T> _end = new ArrayIterator<T>(null);
}
