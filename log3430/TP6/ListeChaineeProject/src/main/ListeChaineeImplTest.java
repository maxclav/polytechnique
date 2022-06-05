/**
 * 
 */
package main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import main.MyListImpl.Elem;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListeChaineeImplTest {

	ArrayList<Object> firstObj = new ArrayList<Object>();
	ArrayList<Object> secondObj = new ArrayList<Object>();
	ArrayList<Object> expectedResult = new ArrayList<Object>();
	ArrayList<Object> AddedObj = new ArrayList<Object>();
	MyList expectedMyList = new MyListImpl();
	
	@Before
	public void setUp() throws Exception {
		
		firstObj.add(1);
		firstObj.add(2);
		
		secondObj.add(2);
		secondObj.add(3);
			
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		
		AddedObj.add(12);
		AddedObj.add(13);
		
		expectedMyList.add(firstObj);
		expectedMyList.add(secondObj);
		expectedMyList.add(expectedResult);
	}

	@Test
	public void testNiveau1() {
 
		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		SetCalculator setCalculatorImpl = new SetCalculatorImpl();

		ArrayList<Object> actualResult = setCalculatorImpl.union(firstObj, secondObj);
		assertEquals("Les objects sont pareils" ,expectedResult, actualResult);
		
		Elem start = myListImpl.getStart();

		assertEquals("Les objects sont pareils" ,firstObj, start.getContent());
		start.setContent(AddedObj);
		assertEquals("Les objects sont pareils" ,AddedObj, start.getContent());
	}
	
	@Test
	public void testNiveau2() throws IOException {
		

		MyList myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		System.out.println("SizeOf the array apres supression" + myListImpl.getSize());
		System.out.println("Le contenu est " + myListImpl.getAt(0));
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));
	}
	
	@Test
	public void testNiveau3() throws IOException {
		
		ListeChaineeImpl listChaineeImpl = new ListeChaineeImpl();
		MyList actualMyList = listChaineeImpl.build(1, firstObj, secondObj, 1);
		
		assertEquals("L'object 1 sont pareils" ,expectedMyList.getAt(0), actualMyList.getAt(0));
		assertEquals("L'object 2 sont pareils" ,expectedMyList.getAt(1), actualMyList.getAt(1));
		assertEquals("L'object 3 sont pareils" ,expectedMyList.getAt(2), actualMyList.getAt(2));
	}


}
