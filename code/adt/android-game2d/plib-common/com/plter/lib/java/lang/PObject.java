package com.plter.lib.java.lang;

public class PObject {

	public void recycle(){
		ObjectPool.recycle(this);
	}
	
	public final static <T extends PObject> T alloc(Class<T> clazz){
		return ObjectPool.get(clazz);
	}
}
