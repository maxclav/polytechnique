package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MyListImplTest {

	@Test
	void test() {
		MyListImpl myListImpl = null;
		
		ArrayList<Object> val1 = new ArrayList<Object>();
		MyList mylist = new MyListImpl();
		
		val1.add(4);
		val1.add(1);
		
		mylist.add(val1);
		
		mylist.setAt(val1, 0);
		
		mylist.setAt(val1, 0);
	}

}
