package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ListeChaineeImpl;
import main.MyList;
import main.MyListImpl;

public class InteractionTest {

	private ListeChaineeImpl listeChainee_;
	
	private MyList myListExpected_;
	
	private MyList myListActual_;
	ArrayList<Object> result;
	
	/* Attributs valides/invalides de val1 (2 valeurs possibles) */
	private ArrayList<Object> val1;
	private ArrayList<Object> val2;
	
	/* Attributs valides/invalides de "op" (7 valeurs possibles) */
	final private String union_ = "union";
	final private String intersection_ = "intersection";
	final private String difference_ = "difference";
	final private String symmetricDifference_ = "symDifference";
	final private String isSubset_ = "isSubset";
	final private String isSuperset_ = "isSuperset";
	final private String invalidOp_ = "opérateur_invalide";
	
	// Pour les resultats
	StringBuilder chaineContent;
	
	final int trie_TRUE = 1;
	final int trie_FALSE = 0;
	
	int multVal1;
	int multVal2;

	@Before
	public void setUp() throws Exception {
		listeChainee_ = new ListeChaineeImpl();
		
		myListExpected_ = new MyListImpl();
		
		myListActual_ = new MyListImpl();
		result = new ArrayList<Object>();
		
		val1 = new ArrayList<Object>();
		val1.add(1);
		val1.add(2);
		
		val2 = new ArrayList<Object>();
		val2.add(3);
		val2.add(4);
		
		chaineContent = new StringBuilder();
	}

	@After
	public void tearDown() throws Exception {
		myListExpected_.reset();
		myListActual_.reset();
		result.clear();
		chaineContent.setLength(0);
	}

	// ACTS Test Suite Generation: Wed Feb 28 19:24:23 EST 2018
	// Degree of interaction coverage: 2
	// Number of parameters: 4 (We excluded val1 and val2)
	// Maximum number of values per parameter: 6
	// Number of configurations: 24
	
	/* -------------------------------------

	Configuration #1:

	1 = op=union
	2 = tri=false
	3 = multVal1=0
	4 = multVal2=1

	------------------------------------- */

	@Test
	public void test1() {
		
		multVal1 = 0;
		multVal2 = 1;
		
		/* Creation des resultats expectes */
		result.add(4);
		result.add(3);
		result.add(0);
		result.add(0);
		
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(union_, val1, val2, trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #2:

	1 = op=union
	2 = tri=true
	3 = multVal1=1
	4 = multVal2=2

	------------------------------------- */

	@Test
	public void test2() {
		multVal1 = 1; /* TO DO */
		multVal2 = 2; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add(1);
		result.add(2);
		result.add(6);
		result.add(8);
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(union_, val1, val2, trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #3:

	1 = op=union
	2 = tri=false
	3 = multVal1=2
	4 = multVal2=3

	------------------------------------- */

	@Test
	public void test3() {
		multVal1 = 2; /* TO DO */
		multVal2 = 3; /* TO DO */
		
		/* Creation des resultats expectes */
		
		result.add(12);
		result.add(9);
		result.add(4);
		result.add(2);
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build( union_, val1, val2, trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #4:

	1 = op=union
	2 = tri=true
	3 = multVal1=3
	4 = multVal2=0

	------------------------------------- */

	@Test
	public void test4() {
		multVal1 = 3; /* TO DO */
		multVal2 = 0; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add(0);
		result.add(0);
		result.add(3);
		result.add(6);
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build( union_, val1, val2, trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #5:

	1 = op=intersection
	2 = tri=false
	3 = multVal1=0
	4 = multVal2=2

	------------------------------------- */

	@Test
	public void test5() {
		multVal1 = 0; /* TO DO */
		multVal2 = 2; /* TO DO */
		
		/* Creation des resultats expectes */
		//rien dans result
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(intersection_, val1, val2, trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #6:

	1 = op=intersection
	2 = tri=true
	3 = multVal1=1
	4 = multVal2=3

	------------------------------------- */

	@Test
	public void test6() {
		multVal1 = 1; /* TO DO */
		multVal2 = 3; /* TO DO */
		
		/* Creation des resultats expectes */
		// rien
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build( intersection_, val1, val2, trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #7:

	1 = op=intersection
	2 = tri=false
	3 = multVal1=2
	4 = multVal2=0

	------------------------------------- */

	@Test
	public void test7() {
		multVal1 = 2; /* TO DO */
		multVal2 = 0; /* TO DO */
		
		/* Creation des resultats expectes */
		//rien
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(intersection_, val1, val2, trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #8:

	1 = op=intersection
	2 = tri=true
	3 = multVal1=3
	4 = multVal2=1

	------------------------------------- */

	@Test
	public void test8() {
		multVal1 = 3; /* TO DO */
		multVal2 = 1; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add(3);
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(intersection_, val1, val2,trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #9:

	1 = op=difference
	2 = tri=true
	3 = multVal1=0
	4 = multVal2=3

	------------------------------------- */

	@Test
	public void test9() {
		multVal1 = 0; /* TO DO */
		multVal2 = 3; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add(0);
		result.add(0);
		
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(difference_, val1, val2, trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #10:

	1 = op=difference
	2 = tri=false
	3 = multVal1=1
	4 = multVal2=0

	------------------------------------- */

	@Test
	public void test10() {
		multVal1 = 1; /* TO DO */
		multVal2 = 0; /* TO DO */
		
		/* Creation des resultats expectes */

		result.add(2);
		result.add(1);
		
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build( difference_, val1, val2, trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #11:

	1 = op=difference
	2 = tri=true
	3 = multVal1=2
	4 = multVal2=1

	------------------------------------- */

	@Test
	public void test11() {
		multVal1 = 2; /* TO DO */
		multVal2 = 1; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add(2);
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(difference_, val1, val2,trie_TRUE, multVal1, multVal2);
			
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #12:

	1 = op=difference
	2 = tri=false
	3 = multVal1=3
	4 = multVal2=2

	------------------------------------- */

	@Test
	public void test12() {
		multVal1 = 3; /* TO DO */
		multVal2 = 2; /* TO DO */
		
		/* Creation des resultats expectes */ //3 6 6 8
		
		result.add(3);
		
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(difference_, val1, val2,trie_FALSE, multVal1, multVal2);
			
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList  :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #13:

	1 = op=symDifference
	2 = tri=true
	3 = multVal1=0
	4 = multVal2=0

	------------------------------------- */

	@Test
	public void test13() {
		multVal1 = 0; /* TO DO */
		multVal2 = 0; /* TO DO */
		
		/* Creation des resultats expectes */
		//rien
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(symmetricDifference_, val1, val2, /* TO DO */ trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #14:

	1 = op=symDifference
	2 = tri=false
	3 = multVal1=1
	4 = multVal2=1

	------------------------------------- */

	@Test
	public void test14() {
		multVal1 = 1; /* TO DO */
		multVal2 = 1; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add(4);
		result.add(3);
		result.add(2);
		result.add(1);
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(symmetricDifference_, val1, val2, trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #15:

	1 = op=symDifference
	2 = tri=false
	3 = multVal1=2
	4 = multVal2=2

	------------------------------------- */

	@Test
	public void test15() {
		multVal1 = 2; /* TO DO */
		multVal2 = 2; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add(8);
		result.add(6);
		result.add(4);
		result.add(2);
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(symmetricDifference_, val1, val2, /* TO DO */ trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #16:

	1 = op=symDifference
	2 = tri=false
	3 = multVal1=3
	4 = multVal2=3

	------------------------------------- */

	@Test
	public void test16() {
		multVal1 = 3; /* TO DO */
		multVal2 = 3; /* TO DO */
		
		/* Creation des resultats expectes */

		result.add(12);
		result.add(9);
		result.add(6);
		result.add(3);
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(symmetricDifference_, val1, val2, /* TO DO */ trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #17:

	1 = op=isSubset
	2 = tri=true
	3 = multVal1=0
	4 = multVal2=0

	------------------------------------- */

	@Test
	public void test17() {
		multVal1 = 0; /* TO DO */
		multVal2 = 0; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add("true");
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSubset_, val1, val2, trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #18:

	1 = op=isSubset
	2 = tri=false
	3 = multVal1=1
	4 = multVal2=1

	------------------------------------- */

	@Test
	public void test18() {
		multVal1 = 1; /* TO DO */
		multVal2 = 1; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add("false");
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSubset_, val1, val2, trie_FALSE, multVal1, multVal2);
			
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #19:

	1 = op=isSubset
	2 = tri=true
	3 = multVal1=2
	4 = multVal2=2

	------------------------------------- */

	@Test
	public void test19() {
		multVal1 = 2; /* TO DO */
		multVal2 = 2; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add("false");
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSubset_, val1, val2, /* TO DO */ trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #20:

	1 = op=isSubset
	2 = tri=false
	3 = multVal1=3
	4 = multVal2=3

	------------------------------------- */

	@Test
	public void test20() {
		multVal1 = 3; /* TO DO */
		multVal2 = 3; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add("false");
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSubset_, val1, val2, /* TO DO */ trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #21:

	1 = op=isSuperset
	2 = tri=true
	3 = multVal1=0
	4 = multVal2=0

	------------------------------------- */

	@Test
	public void test21() {
		multVal1 = 0; /* TO DO */
		multVal2 = 0; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add("true");
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSuperset_, val1, val2, /* TO DO */ trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #22:

	1 = op=isSuperset
	2 = tri=false
	3 = multVal1=1
	4 = multVal2=1

	------------------------------------- */

	@Test
	public void test22() {
		multVal1 = 1; /* TO DO */
		multVal2 = 1; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add("false");
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSuperset_, val1, val2, /* TO DO */ trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #23:

	1 = op=isSuperset
	2 = tri=false
	3 = multVal1=2
	4 = multVal2=2

	------------------------------------- */

	@Test
	public void test23() {
		multVal1 = 2; /* TO DO */
		multVal2 = 2; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add("false");
		
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSuperset_, val1, val2, /* TO DO */ trie_FALSE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}

	/* -------------------------------------

	Configuration #24:

	1 = op=isSuperset
	2 = tri=true
	3 = multVal1=3
	4 = multVal2=3

	------------------------------------- */

	@Test
	public void test24() {
		multVal1 = 3; /* TO DO */
		multVal2 = 3; /* TO DO */
		
		/* Creation des resultats expectes */
		result.add("false");
	
		myListExpected_.add(val1);
		myListExpected_.add(val2);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSuperset_, val1, val2, /* TO DO */ trie_TRUE, multVal1, multVal2);
		}
		catch (final Throwable t)  {
			fail("Erreur test 1. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier element pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxieme element pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisieme element pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1));
		assertTrue("Erreur",this.containOnlyInteger(val2));
	}
	
	/*
	 * Cette fonction vérifie si une liste contient d'autres éléments que des Integer.
	*/
	public Boolean containOnlyInteger(ArrayList<Object> list)
	{
		for(int i = 0; i < list.size(); i++){
			if (!(list.get(i) instanceof Integer))
			{
				System.out.println("La liste ne contient pas juste des Integer.");
				return false;
			}
		}
		return true;
	}

}
