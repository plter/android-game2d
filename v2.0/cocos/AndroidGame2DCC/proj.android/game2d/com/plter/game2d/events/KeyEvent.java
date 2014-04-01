package com.plter.game2d.events;

public class KeyEvent extends Event {

	public KeyEvent(String name,int keyCode) {
		super(name);
	}
	
	private int keyCode = 0;
	
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
	
	public int getKeyCode() {
		return keyCode;
	}
	
	@Override
	public Event clone() {
		return new KeyEvent(getName(), getKeyCode());
	}

	public static final String KEY_UP = "keyUp";
	public static final String KEY_DOWN = "keyDown";
}
