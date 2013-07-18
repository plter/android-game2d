package com.plter.lib.java.lang;


public final class ArrayIterator<ValueType>{
	
	public ArrayIterator(ValueType value) {
		this.setValue(value);
	}
	
	public ArrayIterator<ValueType> preItem() {
		return preItem;
	}
	
	void setPreItem(ArrayIterator<ValueType> preItem) {
		this.preItem = preItem;
	}

	public ArrayIterator<ValueType> nextItem() {
		return nextItem;
	}

	void setNextItem(ArrayIterator<ValueType> nextItem) {
		this.nextItem = nextItem;
	}

	public ValueType value() {
		return value;
	}

	void setValue(ValueType value) {
		this.value = value;
	}

	private ValueType value=null;
	private ArrayIterator<ValueType> preItem = null;
	private ArrayIterator<ValueType> nextItem = null;
}