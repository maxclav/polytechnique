package main;

/**
 * @class	ListeChaineeTestEC
 * @author	maclab
 *		Maxime Clavel			
 *		Étudiant génie logiciel (concentration multimédia)
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

public class ListeChaineeTestEC {
	
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
	public void test1() {	
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
		} catch (Exception e) {
			fail("Erreur test 1");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println(chaineContent + "\n");
		
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
	public void test2() {	
		System.out.println("TEST #2: ");
		
		/* Création du résultat expecté */
		result.add(1);
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(intersection_, val1_1, val2_1);
		} catch (IOException e) {
			fail("Erreur test 2");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println(chaineContent + "\n");
		
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
	public void test3() {	
		System.out.println("TEST #3: ");
		
		/* Création du résultat expecté */
		result.add(2);
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(difference_, val1_1, val2_1);
		} catch (IOException e) {
			fail("Erreur test 3");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println(chaineContent + "\n");
		
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
	public void test4() {	
		System.out.println("TEST #4: ");
		
		/* Création du résultat expecté */
		result.add(2);
		result.add(3);
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(symmetricDifference_, val1_1, val2_1);
		} catch (IOException e) {
			fail("Erreur test 4");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println(chaineContent + "\n");
		
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
	public void test5() {	
		System.out.println("TEST #5: ");
		
		/* Création du résultat expecté */
		result.add("false");
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSubset_, val1_1, val2_1);
		} catch (IOException e) {
			fail("Erreur test 5");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println(chaineContent + "\n");
		
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
	public void test6() {	
		System.out.println("TEST #6: ");
		
		/* Création du résultat expecté */
		result.add("false");
		
		myListExpected_.add(val1_1);
		myListExpected_.add(val2_1);
		myListExpected_.add(result);
		
		try {
			myListActual_ = listeChainee_.build(isSuperset_, val1_1, val2_1);
		} catch (IOException e) {
			fail("Erreur test 6");
		}
		
		for(int i = 0; i < myListExpected_.getSize(); i++){
			chaineContent.append(" " + myListExpected_.getAt(i));
		}	
		System.out.println(chaineContent + "\n");
		
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
	 * TEST #7
	 * val1_2 "op_invalid" val2_2 = resultat
	 * [a, b] "op_invalid" [a, b] = ERREUR (Expected)
	 */
	@Test
	public void test7() {	
		
		System.out.println("TEST #7: ");

		try {
			// Vérifie que les ensembles ne contiennent que des Integer.
			myListActual_ = listeChainee_.build(invalidOp_, val1_2, val2_2);
			assertTrue("Erreur",this.containOnlyInteger(val1_2));
			assertTrue("Erreur",this.containOnlyInteger(val2_2));
		}

		catch (Exception exception) {
			exception.printStackTrace();
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

