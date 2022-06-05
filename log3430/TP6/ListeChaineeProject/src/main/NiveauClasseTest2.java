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
public class NiveauClasseTest2 {

	ArrayList<Object> firstObj = new ArrayList<Object>();
	ArrayList<Object> secondObj = new ArrayList<Object>();

	ArrayList<Object> notSortedFirstObj = new ArrayList<Object>();
	ArrayList<Object> notSortedSecondObj = new ArrayList<Object>();

	ArrayList<Object> expectedIntersection = new ArrayList<Object>();
	ArrayList<Object> expectedDiff = new ArrayList<Object>();
	ArrayList<Object> expectedSym = new ArrayList<Object>();

	ArrayList<Object> expectedNotSortedUnion = new ArrayList<Object>();
	ArrayList<Object> expectedSortedUnion = new ArrayList<Object>();

	ArrayList<Object> AddedObj = new ArrayList<Object>();

	MyList expecteUnionSorteddMyList = new MyListImpl();
	MyList expecteUnionNotSorteddMyList = new MyListImpl();

	MyList expecteIntersectiondMyList = new MyListImpl();
	//MyList expecteIntersectionNotSorteddMyList = new MyListImpl();

	MyList expecteDifferenceMyList = new MyListImpl();
	//MyList expecteDifferenceNotSorteddMyList = new MyListImpl();

	MyList expecteSymetricMyList = new MyListImpl();
	//MyList expecteSymetricNotSorteddMyList = new MyListImpl();


	@Before
	public void setUp() throws Exception {

		firstObj.add(2);
		firstObj.add(1);

		secondObj.add(3);
		secondObj.add(2);

		expectedSortedUnion.add(1);
		expectedSortedUnion.add(2);
		expectedSortedUnion.add(3);

		expectedIntersection.add(2);

		expectedDiff.add(2);

		expectedSym.add(2);

		notSortedFirstObj.add(2);
		notSortedFirstObj.add(1);

		notSortedSecondObj.add(3);
		notSortedSecondObj.add(2);

		expectedNotSortedUnion.add(3);
		expectedNotSortedUnion.add(2);
		expectedNotSortedUnion.add(1);

		AddedObj.add(12);
		AddedObj.add(13);

		expecteUnionSorteddMyList.add(firstObj);
		expecteUnionSorteddMyList.add(secondObj);
		expecteUnionSorteddMyList.add(expectedSortedUnion);

		expecteUnionNotSorteddMyList.add(notSortedFirstObj);
		expecteUnionNotSorteddMyList.add(notSortedSecondObj);
		expecteUnionNotSorteddMyList.add(expectedNotSortedUnion);

		expecteIntersectiondMyList.add(firstObj);
		expecteIntersectiondMyList.add(secondObj);
		expecteIntersectiondMyList.add(expectedIntersection);

		expecteDifferenceMyList.add(firstObj);
		expecteDifferenceMyList.add(secondObj);
		expecteDifferenceMyList.add(expectedDiff);


		expecteSymetricMyList.add(firstObj);
		expecteSymetricMyList.add(secondObj);
		expecteSymetricMyList.add(expectedSym);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNiveau1() {

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		//SetCalculatorImpl
		SetCalculator setCalculatorImpl = new SetCalculatorImpl();
		//Assert
		ArrayList<Object> actualResult = setCalculatorImpl.union(firstObj, secondObj);
		System.out.println("Expected union  "+expectedNotSortedUnion);
		System.out.println("actual union  "+actualResult);
		assertEquals("Les objects sont pareils" ,expectedNotSortedUnion.get(0), actualResult.get(2));

		ArrayList<Object> intersection = setCalculatorImpl.intersection(firstObj, secondObj);
		System.out.println("Expected intersection  "+expectedIntersection);
		System.out.println("actual intersection  "+intersection);
		assertEquals("Les objects sont pareils" ,expectedIntersection, intersection);


		//Elem
		Elem start = myListImpl.getStart();
		//Assert
		assertEquals("Les objects sont pareils" ,firstObj, start.getContent());
		start.setContent(AddedObj);
		assertEquals("Les objects sont pareils" ,AddedObj, start.getContent());
	}

	@Test
	public void testNiveau2() throws IOException {

		//MyListImpl
		MyList myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		//Assert
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		System.out.println("SizeOf the array apres supression" + myListImpl.getSize());
		System.out.println("Le contenu est " + myListImpl.getAt(0));
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));
	}

	@Test
	public void testNiveau3() throws IOException {

		//ListChaineeImpl union/not sorted
		ListeChaineeImpl listChaineeImpl = new ListeChaineeImpl();
		MyList firstActualMyList = listChaineeImpl.build(1, firstObj, secondObj, 1);

		//Assert
		assertEquals("L'object 1 sont pareils" ,expecteUnionSorteddMyList.getAt(0), firstActualMyList.getAt(0));
		assertEquals("L'object 2 sont pareils" ,expecteUnionSorteddMyList.getAt(1), firstActualMyList.getAt(1));
		assertEquals("L'object 3 sont pareils" ,expecteUnionSorteddMyList.getAt(2), firstActualMyList.getAt(2));

		System.out.println("Expected union  "+expecteUnionSorteddMyList);
		System.out.println("Expected union  "+firstActualMyList);

		///ListChaineeImpl union/sorted
		MyList firstNotSortedActualMyList = listChaineeImpl.build(1, firstObj, secondObj, 0);

		System.out.println("Expected union  "+expecteUnionNotSorteddMyList.getAt(0));
		System.out.println("Expected union  "+firstNotSortedActualMyList.getAt(0));

		System.out.println("Expected union  "+expecteUnionNotSorteddMyList.getAt(1));
		System.out.println("Expected union  "+firstNotSortedActualMyList.getAt(1));

		System.out.println("Expected union  "+expecteUnionNotSorteddMyList.getAt(2));
		System.out.println("Expected union  "+firstNotSortedActualMyList.getAt(2));

		//Assert
		assertEquals("L'object 1 sont pareils" ,expecteUnionNotSorteddMyList.getAt(0), firstNotSortedActualMyList.getAt(0));
		assertEquals("L'object 2 sont pareils" ,expecteUnionNotSorteddMyList.getAt(1), firstNotSortedActualMyList.getAt(1));
		assertEquals("L'object 3 sont pareils" ,expecteUnionNotSorteddMyList.getAt(2), firstNotSortedActualMyList.getAt(2));



		//ListChaineeImpl intersection
		MyList secondActualMyList = listChaineeImpl.build(2, firstObj, secondObj, 1);

		//Assert
		assertEquals("L'object 1 sont pareils" ,expecteIntersectiondMyList.getAt(0), secondActualMyList.getAt(0));
		assertEquals("L'object 2 sont pareils" ,expecteIntersectiondMyList.getAt(1), secondActualMyList.getAt(1));
		assertEquals("L'object 3 sont pareils" ,expecteIntersectiondMyList.getAt(2), secondActualMyList.getAt(2));


		System.out.println("Expected expecteIntersectiondMyList  "+expecteIntersectiondMyList.getAt(0));
		System.out.println("Expected union  "+secondActualMyList.getAt(0));

		System.out.println("Expected expecteIntersectiondMyList  "+expecteIntersectiondMyList.getAt(1));
		System.out.println("Expected union  "+secondActualMyList.getAt(1));

		System.out.println("Expected expecteIntersectiondMyList  "+expecteIntersectiondMyList.getAt(2));
		System.out.println("Expected union  "+secondActualMyList.getAt(2));

		//ListChaineeImpl difference
		MyList thirdActualMyList = listChaineeImpl.build(2, firstObj, secondObj, 0);

		System.out.println("Expected expecteDifferenceMyList  "+expecteDifferenceMyList.getAt(0));
		System.out.println("Expected union  "+thirdActualMyList.getAt(0));

		System.out.println("Expected expecteDifferenceMyList  "+expecteDifferenceMyList.getAt(1));
		System.out.println("Expected union  "+thirdActualMyList.getAt(1));

		System.out.println("Expected expecteDifferenceMyList  "+expecteDifferenceMyList.getAt(2));
		System.out.println("Expected union  "+thirdActualMyList.getAt(2));

		//Assert
		assertEquals("L'object 1 sont pareils" ,expecteDifferenceMyList.getAt(0), thirdActualMyList.getAt(0));
		assertEquals("L'object 2 sont pareils" ,expecteDifferenceMyList.getAt(1), thirdActualMyList.getAt(1));
		assertEquals("L'object 3 sont pareils" ,expecteDifferenceMyList.getAt(2), thirdActualMyList.getAt(2));



		//ListChaineeImpl symDifference
		MyList forthActualMyList = listChaineeImpl.build(2, firstObj, secondObj, 0);

		System.out.println("Expected expecteSymetricMyList  "+expecteSymetricMyList.getAt(0));
		System.out.println("Expected union  "+forthActualMyList.getAt(0));

		System.out.println("Expected expecteSymetricMyList  "+expecteSymetricMyList.getAt(1));
		System.out.println("Expected union  "+forthActualMyList.getAt(1));

		System.out.println("Expected expecteSymetricMyList  "+expecteSymetricMyList.getAt(2));
		System.out.println("Expected union  "+forthActualMyList.getAt(2));


		//Assert
		assertEquals("L'object 1 sont pareils" ,expecteSymetricMyList.getAt(0), forthActualMyList.getAt(0));
		assertEquals("L'object 2 sont pareils" ,expecteSymetricMyList.getAt(1), forthActualMyList.getAt(1));
		assertEquals("L'object 3 sont pareils" ,expecteSymetricMyList.getAt(2), forthActualMyList.getAt(2));

	}

}
