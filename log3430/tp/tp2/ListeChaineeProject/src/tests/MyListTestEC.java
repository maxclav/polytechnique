/**
 * @class	MyListTestEC
 * @author	maclab
 *		Maxime Clavel			
 *		Étudiant genie logiciel (concentration multimedia)
 * 
 * Date:	30 janvier 2018
 * Cours:	Methodes de test et de validation du logiciel (LOG3430)
 * École:	Polytechnique Montreal
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import main.MyListImpl;

public class MyListTestEC {

	/*
	 * Test de la fonction removeAt(Index)
	 * CAS DE TEST #1
	 * Test: Taille de la liste �gale � 3
	 * 		 removeAt(2) OK
	 * 		 removeAt(1) OK
	 */
	 //Cas size > pos > 0
	@Test
	public void testRemoveAt12() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		ArrayList<Object> thirdObj = new ArrayList<Object>();
		thirdObj.add(5);
		thirdObj.add(6);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);
		myListImpl.add(thirdObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,3, myListImpl.getSize());

		myListImpl.removeAt(2);

		assertEquals("Les tailles sont egales" ,2, myListImpl.getSize());
		System.out.println("SizeOf the array apres supression" + myListImpl.getSize());

		System.out.println("Le contenu est " + myListImpl.getAt(0));
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));
		System.out.println("Le contenu est " + myListImpl.getAt(1));
		System.out.println("Le contenu de thirdObj est " + secondObj);
		assertEquals("Le contenu est pareil ", secondObj, myListImpl.getAt(1));

		myListImpl.removeAt(1);
		System.out.println("SizeOf the array apres supression" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));

	}

	/*
	* Test de la fonction removeAt(Index)
	* CAS DE TEST #2
	 * Test: Taille de la liste �gale � 3
	 * 		 removeAt(0) OK
	 */
	 //Cas pos == 0
	@Test
	public void testFuncRemoveAt0() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		ArrayList<Object> thirdObj = new ArrayList<Object>();
		thirdObj.add(5);
		thirdObj.add(6);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);
		myListImpl.add(thirdObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,3, myListImpl.getSize());

		myListImpl.removeAt(0);
		System.out.println("SizeOf the array apres supression" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,2, myListImpl.getSize());

	}

	/*
	 * Test de la fonction removeAt(Index)
	 * CAS DE TEST #3
	 * Test: Taille de la liste �gale � 3
	 * 		 removeAt(3) ERROR Out Of range
	 */
	 //Cas size < pos
	@Test
	public void testFuncRemoveAtOutOfRange() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		ArrayList<Object> thirdObj = new ArrayList<Object>();
		thirdObj.add(5);
		thirdObj.add(6);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);
		myListImpl.add(thirdObj);

		try {
			myListImpl.removeAt(3);
		}
		catch(Exception e)
		{
			fail("Out of Range");
		}

	}


	/*
	 * Test de la fonction reset()
	 * CAS DE TEST #1
	 * Test: Taille de la liste �gale � 1
	 * 		 Apr�s l'appel de la fonction reset() la taille �gale � 0
	 */
	@Test
	public void testReset() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());

		myListImpl.reset();
		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,0, myListImpl.getSize());

	}

	/*
	 * Test de la fonction setAt()
	 * CAS DE TEST #1
	 * Test: Taille de la liste �gale � 3
	 * 		 setAt(obj, 1) OK
	 * 		 setAt(obj, 2) OK
	 *     0 < pos < size()
	 */
	@Test
	public void testSetAt12() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		ArrayList<Object> thirdObj = new ArrayList<Object>();
		secondObj.add(4);
		secondObj.add(5);

		ArrayList<Object> replaceFirstObj = new ArrayList<Object>();
		replaceFirstObj.add(6);
		replaceFirstObj.add(7);

		ArrayList<Object> replaceSecondObj = new ArrayList<Object>();
		replaceFirstObj.add(8);
		replaceFirstObj.add(9);

		ArrayList<Object> replaceThirdObj = new ArrayList<Object>();
		replaceFirstObj.add(10);
		replaceFirstObj.add(11);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);
		myListImpl.add(thirdObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,3, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));

		myListImpl.setAt(replaceSecondObj,1);
		assertEquals("Le contenu est pareil ", replaceSecondObj, myListImpl.getAt(1));

		myListImpl.setAt(replaceThirdObj,2);
		assertEquals("Le contenu est pareil ", replaceThirdObj, myListImpl.getAt(2));
	}

	/*
	 * Test de la fonction setAt()
	 * CAS DE TEST #2
	 * Test: Taille de la liste �gale � 1
	 * 		 setAt(obj, 0) OK
	 *     0 == pos
	 */
	@Test
	public void testSetAt0() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> replaceFirstObj = new ArrayList<Object>();
		replaceFirstObj.add(6);
		replaceFirstObj.add(7);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));

		myListImpl.setAt(replaceFirstObj,0);
		assertEquals("Le contenu est pareil ", replaceFirstObj, myListImpl.getAt(0));
	}

	/*
	 * Test de la fonction setAt2()
	 * CAS DE TEST #3
	 * Test: Taille de la liste �gale � 1
	 * 		 setAt(obj, 2) OK
	 *     size() < pos
	 */
	@Test
	public void testSetAtOutOfRange() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> replaceFirstObj = new ArrayList<Object>();
		replaceFirstObj.add(6);
		replaceFirstObj.add(7);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales", 1, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));

		// Out Of Range
		try {
			myListImpl.setAt(replaceFirstObj,2);
			assertEquals("Le contenu est pareil ", replaceFirstObj, myListImpl.getAt(2));
		}
		catch(Exception e)
		{
			fail("Out of range");
		}
	}

	/*
	 * Test de la fonction getAt()
	 * CAS DE TEST #1
	 * Test: Taille de la liste �gale � 1
	 * 		 getAt(0) OK
	 *     pos == 0
	 */
	@Test
	public void testGetAt0() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));
	}

	/*
	 * Test de la fonction getAt()
	 * CAS DE TEST #2
	 * Test: Taille de la liste �gale � 2
	 * 		 getAt(1) OK
	 * 		 0 < pos < size()
	 */
	@Test
	public void testGetAt1() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);


		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(2);
		secondObj.add(3);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,2, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", secondObj, myListImpl.getAt(1));
	}

	/*
	 * Test de la fonction getAt()
	 * CAS DE TEST #3
	 * Test: Taille de la liste �gale � 2
	 * 		 getAt(2) ERROR Out of Range
	 *     pos > size()
	 */
	@Test
	public void testGetAt2() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);


		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(2);
		secondObj.add(3);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,2, myListImpl.getSize());

		try {
			assertEquals("Le contenu est pareil ", secondObj, myListImpl.getAt(2));
		}
		catch(Exception e)
		{
			fail("Out Of Range");
		}

	}

	/*
	 * Test de la fonction removeItem()
	 * CAS DE TEST #1 
	 * Test: Taille de la liste �gale � 1
	 * 		 removeItem(existedObject) OK
	 */
	@Test
	public void testRemoveAtFirst() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));

		try {
			myListImpl.removeItem(firstObj);
		}
		catch(Exception e)
		{
			fail("Out Of Range");
		}
	}
	
	/*
	 * Test de la fonction removeItem()
	 * CAS DE TEST #2  
	 * Test: Taille de la liste �gale � 2
	 * 		 removeItem(secondePosition) OK
	 */
	@Test
	public void testRemoveItemSecondPos() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(7);
		secondObj.add(2);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,2, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", secondObj, myListImpl.getAt(1));

		try {
			myListImpl.removeItem(secondObj);
		}
		catch(Exception e)
		{
			 fail("Out Of Range");
		}
	}

	/*
	 * Test de la fonction removeItem()
	 * CAS DE TEST #3   
	 * Test: Taille de la liste �gale � 1
	 * 		 removeItem(NotexistedObject) ERROR NOTexistedObject
	 */
	@Test
	public void testRemoveItemNotExisted() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> notAddedObj = new ArrayList<Object>();
		firstObj.add(5);
		firstObj.add(6);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));

		try {
			myListImpl.removeItem(notAddedObj);
		}
		catch(Exception e)
		{
			 fail("Out Of Range");
		}
	}
	
	/*
	 * Test de la fonction testAddFirst()
	 * CAS DE TEST #1   
	 * Test: Taille de la liste �gale � 1
	 * 		 add(fistObject) Ok
	 */
	@Test
	public void testAddFirst() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);

		System.out.println("SizeOf the array" + myListImpl.getSize());
		assertEquals("Les tailles sont egales" ,1, myListImpl.getSize());
		assertEquals("Le contenu est pareil ", firstObj, myListImpl.getAt(0));
		
	}
	
	/*
	 * Test de la fonction testAddSecond()
	 * CAS DE TEST #2   
	 * Test: Taille de la liste �gale � 2
	 * 		 add(secondObject) Ok
	 */
	@Test
	public void testAddSecond() {

		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);
		
		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(5);
		secondObj.add(6);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);

		assertEquals("Les tailles sont egales" ,2 , myListImpl.getSize());
		assertEquals("Le contenu est pareil ", secondObj, myListImpl.getAt(1));
		
	}

}
