package top.yunp.androidgame2d.display;

public interface IContainer {

	Display add(Display display);
	Display addAt(Display display, int index);
	boolean remove(Display display);
	Display remove(int index);
	Display get(int index);
	void removeAll();
	void swap(Display display, Display display2);
	void swap(int index, int index2);
	boolean contains(Display display);
	
	/**
	 * 获取指定对象在显示列表中的索引，如果没有该对象，则返回-1
	 * @param display
	 * @return
	 */
	int getIndex(Display display);
}
