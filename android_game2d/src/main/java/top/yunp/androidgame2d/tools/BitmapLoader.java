package top.yunp.androidgame2d.tools;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class BitmapLoader {

	
	public static final Bitmap loadBitmap(Context context,int resId){
		return ((BitmapDrawable)context.getResources().getDrawable(resId)).getBitmap();
	}
	
}
