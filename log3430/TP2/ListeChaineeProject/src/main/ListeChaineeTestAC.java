package main;

/**
 * @class	ListeChaineeTestEC
 * @author	maclab
 *			Étudiant génie logiciel (concentration multimédia)
 * 
 * Date:	30 janvier 2018
 * Cours:	Méthodes de test et de validation du logiciel (LOG3430)
 * École:	Polytechnique Montréal
 * 
 * Informations:
 * Cette classe test la classe ListeChainee.java
 * en utilisant la technique de test boîte noire catégorie partition EC (Each Choice)
 * et en utilisant le critère AC (All Combinations).
 * ListeChainee prend en entrée 2 objets de type ArrayList et un objet de type String
 * et retourne un objet de type MyList.
 */

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ListeChaineeImpl;
import main.MyList;
import main.MyListImpl;

public class ListeChaineeTestAC {
	
	private ListeChaineeImpl listeChainee_;
	
	private MyList myListExpected_;
	
	private MyList myListActual_;
	ArrayList<Object> result;
	
	/* Attributs valides/invalides de val1 (2 valeurs possibles) */
	private ArrayList<Object> val1_1;
	private ArrayList<Object> val1_2;
	
	/* Attributs valides/invalides de val2 (2 valeurs possibles) */
	private ArrayList<Object> val2_1;
	private ArrayList<Object> val2_2;
	
	/* Attributs valides/invalides de "op" (7 valeurs possibles) */
	final private String union_ = "union";
	final private String intersection_ = "intersection";
	final private String difference_ = "difference";
	final private String symmetricDifference_ = "symDifference";
	final private String isSubset_ = "isSubset";
	final private String isSuperset_ = "isSuperset";
	final private String invalidOp_ = "opérateur_invalide";
	
	// Pour les résultats
	StringBuilder chaineContent;

	@Before
	public void setUp() throws Exception {
		
		listeChainee_ = new ListeChaineeImpl();
		
		myListExpected_ = new MyListImpl();
		
		myListActual_ = new MyListImpl();
		result = new ArrayList<Object>();
		
		val1_1 = new ArrayList<Object>();
		val1_1.add(1);
		val1_1.add(2);
		
		val1_2 = new ArrayList<Object>();
		val1_2.add("a");
		val1_2.add("b");
		
		val2_1 = new ArrayList<Object>();
		val2_1.add(1);
		val2_1.add(3);
		
		val2_2 = new ArrayList<Object>();
		val2_2.add("a");
		val2_2.add("b");
		
		chaineContent = new StringBuilder();
	}
	
	@After
	public void tearDown() throws Exception {
		myListExpected_.reset();
		myListActual_.reset();
		result.clear();
		chaineContent.setLength(0);
	}

	/*
	 * TEST #1
	 * val1_1 ∪ val2_1 = resultat
	 * [1, 2] ∪ [1, 3] = [1, 2, 3] (Expected)
	 */
	@Test
	public void test1() throws IOException {	
		System.out.println("TEST #1: ");
		
		/* Création du résultat expecté */
		result.add(1);
		result.add(2);
		result.add(3);
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(union_, val1_1, val2_1);
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
		assertEquals("Erreur test1: Premier élément pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxième élément pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisième élément pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1_1));
		assertTrue("Erreur",this.containOnlyInteger(val2_1));
	}
	
	/*
	 * TEST #2
	 * val1_1 ∩ val2_1 = resultat
	 * [1, 2] ∩ [1, 3] = [1] (Expected)
	 */
	@Test
	public void test2() throws IOException {	
		System.out.println("TEST #2: ");
		
		/* Création du résultat expecté */
		result.add(1);
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(intersection_, val1_1, val2_1);
		}
		catch (final Throwable t)  {
			fail("Erreur test 2. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier élément pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxième élément pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisième élément pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1_1));
		assertTrue("Erreur",this.containOnlyInteger(val2_1));
	}
	
	/*
	 * TEST #3
	 * val1_1 \ val2_1 = resultat
	 * [1, 2] \ [1, 3] = [2] (Expected)
	 */
	@Test
	public void test3() throws IOException {	
		System.out.println("TEST #3: ");
		
		/* Création du résultat expecté */
		result.add(2);
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(difference_, val1_1, val2_1);
		}
		catch (final Throwable t)  {
			fail("Erreur test 3. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier élément pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxième élément pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisième élément pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1_1));
		assertTrue("Erreur",this.containOnlyInteger(val2_1));
	}
	
	/*
	 * TEST #4
	 * val1_1 ∆ val2_1 = resultat
	 * [1, 2] ∆ [1, 3] = [2, 3] (Expected)
	 */
	@Test
	public void test4() throws IOException {	
		System.out.println("TEST #4: ");
		
		/* Création du résultat expecté */
		result.add(2);
		result.add(3);
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(symmetricDifference_, val1_1, val2_1);
		}
		catch (final Throwable t)  {
			fail("Erreur test 4. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier élément pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxième élément pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisième élément pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1_1));
		assertTrue("Erreur",this.containOnlyInteger(val2_1));
	}
	
	/*
	 * TEST #5
	 * val1_1 ⊆ val2_1 = resultat
	 * [1, 2] ⊆ [1, 3] = false (Expected)
	 */
	@Test
	public void test5() throws IOException {	
		System.out.println("TEST #5: ");
		
		/* Création du résultat expecté */
		result.add("false");
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSubset_, val1_1, val2_1);
		}
		catch (final Throwable t)  {
			fail("Erreur test 5. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier élément pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxième élément pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisième élément pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1_1));
		assertTrue("Erreur",this.containOnlyInteger(val2_1));
	}
	
	/*
	 * TEST #6
	 * val1_1 ⊇ val2_1 = resultat
	 * [1, 2] ⊇ [1, 3] = false (Expected)
	 */
	@Test
	public void test6() throws IOException {	
		System.out.println("TEST #6: ");
		
		/* Création du résultat expecté */
		result.add("false");
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSuperset_, val1_1, val2_1);
		}
		catch (final Throwable t)  {
			fail("Erreur test 6. ");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println("MyList :" + chaineContent + "\n");
		
		/* Test sur la taille des deux listes. */
		assertEquals("Erreur test1: Taille de la liste", myListExpected_.getSize(), myListActual_.getSize());
		
		/* Test du premier élément des deux listes */
		assertEquals("Erreur test1: Premier élément pas bon (val1).", myListExpected_.getAt(0), myListActual_.getAt(0));
		
		/* Test du deuxième élément des deux listes */
		assertEquals("Erreur test1: Deuxième élément pas bon (val2).", myListExpected_.getAt(1), myListActual_.getAt(1));
		
		/* Test du troisième élément des deux listes */
		assertEquals("Erreur test1: Troisième élément pas bon (resultat).", myListExpected_.getAt(2), myListActual_.getAt(2));
		
		// Vérifie que les ensembles ne contiennent que des Integer.
		assertTrue("Erreur",this.containOnlyInteger(val1_1));
		assertTrue("Erreur",this.containOnlyInteger(val2_1));
	}
	
	/*
	 * TEST #7: CE TEST DEVRAIT DONNER UNE ERREUR
	 * val1_1 "op_invalid" val2_1 = resultat
	 * [1, 2] "op_invalid" [1, 3] = false (Expected)
	 */
	@Test
	public void test7() throws IOException {	
		
		System.out.println("TEST #7: ");

		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_1));
			assertTrue("Erreur",this.containOnlyInteger(val2_1));
			
			myListActual_ = listeChainee_.build(invalidOp_, val1_1, val2_1);
		}

		catch (final Throwable t) {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 7. ");
		}
	}
	
	/*
	 * TEST #8
	 * val1_1 ∪ val2_2 = resultat
	 * [1, 2] ∪ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test8() throws IOException {
		System.out.println("TEST #8: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_1));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(union_, val1_1, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 8. ");
		}
	}
	
	/*
	 * TEST #9
	 * val1_1 ∩ val2_2 = resultat
	 * [1, 2] ∩ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test9() throws IOException {
		System.out.println("TEST #9: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_1));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(intersection_, val1_1, val2_2);
		} catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 9. ");
		}
	}
	
	/*
	 * TEST #10
	 * val1_1 \ val2_2 = resultat
	 * [1, 2] \ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test10() throws IOException {
		System.out.println("TEST #10: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_1));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(difference_, val1_1, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 10. ");
		}
	}
	
	/*
	 * TEST #11
	 * val1_1 ∆ val2_2 = resultat
	 * [1, 2] ∆ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test11() throws IOException {
		System.out.println("TEST #11: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_1));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(symmetricDifference_, val1_1, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 11. ");
		}
	}
	
	/*
	 * TEST #12
	 * val1_1 ⊆ val2_2 = resultat
	 * [1, 2] ⊆ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test12() throws IOException {
		System.out.println("TEST #12: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_1));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(isSubset_, val1_1, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 12. ");
		}
	}
	
	/*
	 * TEST #13
	 * val1_1 ⊇ val2_2 = resultat
	 * [1, 2] ⊇ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test13() throws IOException {		
		System.out.println("TEST #13: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_1));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(isSuperset_, val1_1, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 13. ");
		}
	}
	
	/*
	 * TEST #14
	 * val1_1 "op_invalid" val2_2 = resultat
	 * [1, 2] "op_invalid" [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test14() throws IOException {
		System.out.println("TEST #14: ");

		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_1));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(invalidOp_, val1_1, val2_2);
		} 
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 14. ");
		}
	}
	
	/*
	 * TEST #15
	 * val1_2 ∪ val2_1 = resultat
	 * [a, b] ∪ [1, 2] = ERREUR (Expected)
	 */
	@Test
	public void test15() throws IOException {
		System.out.println("TEST #15: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_1));
			
			myListActual_ = listeChainee_.build(union_, val1_2, val2_1);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 15. ");
		}
	}
	
	/*
	 * TEST #16
	 * val1_2 ∩ val2_1 = resultat
	 * [a, b] ∩ [1, 2] = ERREUR (Expected)d)
	 */
	@Test
	public void test16() throws IOException {
		System.out.println("TEST #16: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_1));
			
			myListActual_ = listeChainee_.build(intersection_, val1_2, val2_1);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 16. ");
		}
	}
	
	/*
	 * TEST #17
	 * val1_2 \ val2_1 = resultat
	 * [a, b] \ [1, 2] = ERREUR (Expected)
	 */
	@Test
	public void test17() throws IOException {
		System.out.println("TEST #17: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_1));
			
			myListActual_ = listeChainee_.build(difference_, val1_2, val2_1);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 17. ");
		}
	}
	
	/*
	 * TEST #18
	 * val1_2 ∆ val2_1 = resultat
	 * [a, b] ∆ [1, 2] = ERREUR (Expected)
	 */
	@Test
	public void test18() throws IOException {
		System.out.println("TEST #18: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_1));
			
			myListActual_ = listeChainee_.build(symmetricDifference_, val1_2, val2_1);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 18. ");
		}
	}
	
	/*
	 * TEST #19
	 * val1_2 ⊆ val2_1 = resultat
	 * [a, b] ⊆ [1, 2] = ERREUR (Expected)
	 */
	@Test
	public void test19() throws IOException {
		System.out.println("TEST #19: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_1));
			
			myListActual_ = listeChainee_.build(isSubset_, val1_2, val2_1);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 19. ");
		}
	}
	
	/*
	 * TEST #20
	 * val1_2 ⊇ val2_1 = resultat
	 * [a, b] ⊇ [1, 2] = ERREUR (Expected)
	 */
	@Test
	public void test20() throws IOException {
		System.out.println("TEST #20: ");
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_1));
			
			myListActual_ = listeChainee_.build(isSuperset_, val1_2, val2_1);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 20. ");
		}
	}
	
	/*
	 * TEST #21
	 * val1_2 "op_invalid" val2_1 = resultat
	 * [a, b] "op_invalid" [1, 2] = ERREUR (Expected)
	 */
	@Test
	public void test21() throws IOException {
		System.out.println("TEST #21: ");

		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_1));
			
			myListActual_ = listeChainee_.build(invalidOp_, val1_2, val2_1);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 21. ");
		}
	}
	
	/*
	 * TEST #22
	 * val1_2 ∪ val2_2 = resultat
	 * [a, b] ∪ [a, b] = ERREUR (Expected))
	 */
	@Test
	public void test22() throws IOException {
		System.out.println("TEST #22: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(union_, val1_2, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 22. ");
		}
	}
	
	/*
	 * TEST #23
	 * val1_2 ∩ val2_2 = resultat
	 * [a, b] ∩ [a, b] = ERREUR (Expected))
	 */
	@Test
	public void test23() throws IOException {
		System.out.println("TEST #23: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(intersection_, val1_2, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 23. ");
		}
	}
	
	/*
	 * TEST #24
	 * val1_2 \ val2_2 = resultat
	 * [a, b] \ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test24() throws IOException {
		System.out.println("TEST #24: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(difference_, val1_2, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 24. ");
		}
	}
	
	/*
	 * TEST #25
	 * val1_2 ∆ val2_2 = resultat
	 * [a, b] ∆ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test25() throws IOException {
		System.out.println("TEST #25: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(symmetricDifference_, val1_2, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 25. ");
		}
	}
	
	/*
	 * TEST #26
	 * val1_2 ⊆ val2_2 = resultat
	 * [a, b] ⊆ [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test26() throws IOException {
		System.out.println("TEST #26: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(isSubset_, val1_2, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 26. ");
		};
	}
	
	/*
	 * TEST #27
	 * val1_1 ⊇ val2_2 = resultat
	 * [1, 2] ⊇ [a, b] = ERREUR (Expected))
	 */
	@Test
	public void test27() throws IOException {
		System.out.println("TEST #27: ");
		
		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(isSuperset_, val1_2, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 27. ");
		}
	}
	
	/*
	 * TEST #28
	 * val1_2 "op_invalid" val2_2 = resultat
	 * [a, b] "op_invalid" [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test28() throws IOException {
		System.out.println("TEST #28: ");

		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
			
			myListActual_ = listeChainee_.build(invalidOp_, val1_2, val2_2);
		}
		catch (final Throwable t)  {
			System.out.println("[Erreur détecté]" + "\n");
			fail("Erreur test 28. ");
		}
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
