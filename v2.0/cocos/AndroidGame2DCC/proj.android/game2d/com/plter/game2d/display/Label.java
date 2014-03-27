package com.plter.game2d.display;

public class Label extends InteractiveObject {
	
	public Label(String string,String fontName,float fontSize) {
		this.string = string;
		this.fontName = fontName;
		this.fontSize = fontSize;
		
		setNativeObject(createNativeObjectWithString(string,fontName,fontSize));
	}
	
	public Label(String string) {
		this(string, "Courier", 32);
	}
	
	private native long createNativeObjectWithString(String label,String fontName,float fontSize);
	
	public void setString(String string) {
		this.string = string;
		nativeSetString(getNativeObject(), string);
	}
	private native void nativeSetString(long nativeThis,String string);
	
	public String getString() {
		return string;
	}
	
	public void setFontName(String fontName) {
		this.fontName = fontName;
		nativeSetFontName(getNativeObject(), fontName);
	}
	private native void nativeSetFontName(long nativeThis,String fontName);
	
	public String getFontName() {
		return fontName;
	}
	
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
		nativeSetFontSize(getNativeObject(), fontSize);
	}
	private native void nativeSetFontSize(long nativeThis,float fontSize);
	
	public float getFontSize() {
		return fontSize;
	}
	
	private String string=null,fontName=null;
	private float fontSize = 0;
}
