package main;

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
public class NiveauClasseTest {

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

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNiveau1() {
		
		//MyListImpl 
		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		//Assert
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		System.out.println("SizeOf the array apres supression" + myListImpl.getSize());
		System.out.println("Le contenu est " + myListImpl.getAt(0));
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));
		
		//SetCalculatorImpl 
		SetCalculatorImpl setCalculatorImpl = new SetCalculatorImpl();
		//Assert
		ArrayList<Object> actualResult = setCalculatorImpl.union(firstObj, secondObj);
		assertEquals("Les objects sont pareils" ,expectedResult, actualResult);
		
		//Elem 
		Elem start = myListImpl.getStart();
		//Assert
		assertEquals("Les objects sont pareils" ,firstObj, start.getContent());
		start.setContent(AddedObj);
		assertEquals("Les objects sont pareils" ,AddedObj, start.getContent());
	}
	
	@Test
	public void testNiveau2() throws IOException {
		
		//ListChaineeImpl
		ListeChaineeImpl listChaineeImpl = new ListeChaineeImpl();
		MyList actualMyList = listChaineeImpl.build("union", firstObj, secondObj, true, 1, 1);
		
		//Assert
		assertEquals("L'object 1 sont pareils" ,expectedMyList.getAt(0), actualMyList.getAt(0));
		assertEquals("L'object 2 sont pareils" ,expectedMyList.getAt(1), actualMyList.getAt(1));
		assertEquals("L'object 3 sont pareils" ,expectedMyList.getAt(2), actualMyList.getAt(2));
	}

}
