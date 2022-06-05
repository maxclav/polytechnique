/**
 * @class	TestMadums
 *
 * Date:	21 mars 2018
 * Cours:	Methodes de test et de validation du logiciel (LOG3430)
 * École:	Polytechnique Montreal
 *
 */
package Test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import main.MyListImpl;
import main.MyListImpl.Elem;

public class TestsMadum {

	@Test
	public void test1_current() {
		
		// SETUP
		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		ArrayList<Object> addedObj = new ArrayList<Object>();
		addedObj.add(5);
		addedObj.add(46);

		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);

		// REPORTEUR
		Elem curentElem = myListImpl.getCurrent();

		assertEquals("Elemnt identiques", curentElem.getContent(), secondObj);

		// TRANSFORMEUR
		myListImpl.add(addedObj);

		// REPORTEUR
		curentElem = myListImpl.getCurrent();
		assertEquals("Elemnt identiques", curentElem.getContent(), addedObj);

		// TRANSFORMEUR
		myListImpl.reset();

		// REPORTEUR
		curentElem = myListImpl.getCurrent();
		assertEquals("Elemnt identiques", curentElem, null);
	}

	@Test
	public void test1_size() {
		
		// SETUP
		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		ArrayList<Object> addedObj = new ArrayList<Object>();
		addedObj.add(5);
		addedObj.add(46);

		// CONSTRUCTEUR
		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);

		// REPORTEUR
		int size = myListImpl.getSize();
		assertEquals("Size identique", 2, size);

		// TRANSFORMEUR
		myListImpl.add(addedObj);

		// REPORTEUR
		size = myListImpl.getSize();
		assertEquals("Size identique", 3, size);

		// TRANSFORMEUR
		myListImpl.removeAt(0);

		// REPORTEUR
		size = myListImpl.getSize();
		assertEquals("Size identique", 2, size);

		// TRANSFORMEUR
		myListImpl.removeItem(addedObj);

		// REPORTEUR
		size = myListImpl.getSize();
		assertEquals("Size identique", 1, size);

		// TRANSFORMEUR
		myListImpl.reset();

		// REPORTEUR
		size = myListImpl.getSize();
		assertEquals("Size identique", 0, size);
	}

	@Test
	public void test1_start() {
		
		// SETUP
		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		ArrayList<Object> addedObj = new ArrayList<Object>();
		addedObj.add(5);
		addedObj.add(6);

		// CONSTRUCTEUR
		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);

		// REPORTEUR
		Elem start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start.getContent(), firstObj);

		// TRANSFORMEUR
		myListImpl.add(addedObj);

		// REPORTEUR
		start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start.getContent(), firstObj);

		// TRANSFORMEUR
		myListImpl.removeAt(0);

		// REPORTEUR
		start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start.getContent(), secondObj);
 
		// TRANSFORMEUR
		myListImpl.removeItem(addedObj);

		// REPORTEUR
		start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start.getContent(), secondObj);

		// TRANSFORMEUR
		myListImpl.reset();

		// REPORTEUR
		start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start, null);

		// TRANSFORMEUR
		myListImpl.add(addedObj);

		// REPORTEUR
		start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start.getContent(), addedObj);

		// Autre
		myListImpl.getAt(0);

		// REPORTEUR
		start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start.getContent(), addedObj);
	}

	@Test
	public void test2_start() {
		
		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		ArrayList<Object> addedObj = new ArrayList<Object>();
		addedObj.add(5);
		addedObj.add(46);

		// CONSTRUCTEUR
		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);

		// REPORTEUR
		Elem start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start.getContent(), firstObj);

		// TRANSFORMEUR
		myListImpl.add(addedObj);

		// REPORTEUR
		start = myListImpl.getStart();
		assertEquals("Elemnt identiques", start.getContent(), firstObj);
		System.out.println("2");

		// TRANSFORMEUR
		myListImpl.removeAt(0);
	}

	/*
	 * Test 4 content de la classe Elem
	 */
	@Test
	public void test1_content() {

		// SETUP
		ArrayList<Object> firstObj = new ArrayList<Object>();
		firstObj.add(1);
		firstObj.add(2);

		ArrayList<Object> secondObj = new ArrayList<Object>();
		secondObj.add(3);
		secondObj.add(4);

		// CONSTRUCTEUR
		MyListImpl myListImpl = new MyListImpl();
		myListImpl.add(firstObj);
		myListImpl.add(secondObj);


		Elem start = myListImpl.getStart();
		
		// REPORTEUR
		ArrayList<Object> content = start.getContent();
		assertEquals("Elemnt identiques", content, firstObj);

		// TRANSFORMEUR
		start.setContent(secondObj);
		
		// REPORTEUR
		content = start.getContent();
		assertEquals("Elemnt identiques", content, secondObj);


	}
}
