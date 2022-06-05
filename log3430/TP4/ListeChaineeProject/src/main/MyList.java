package main;

import java.util.ArrayList;

import main.MyListImpl.Elem;

public interface MyList{
	
	void add(ArrayList<Object> e);
	void removeAt(int pos);
	void removeItem(ArrayList<Object> item);
	void setAt(ArrayList<Object> item, int pos);
	ArrayList<Object> getAt(int pos);
	int getSize();
	void reset();
	public Elem getStart();
	public Elem getCurrent() ;
}
