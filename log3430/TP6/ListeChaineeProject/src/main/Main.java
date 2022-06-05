/**
 * @class			: Main
 * @author	 		: Ons mlouki
 * 					  Ons.mlouki@gmail.com
 * 
 */
package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// call for main operation
		// suiteChainee.build(op, val1, val2)
		// op is now an Integer (no more a String)
		// op{1 = union, 2 = intersection, 3 = difference, 4 = symDifference}
		// val1,val2 are sets
			
		ArrayList<Object> val1 = new ArrayList<Object>();
		ArrayList<Object> val2 = new ArrayList<Object>();
		MyList list = new MyListImpl();	
		ListeChainee suiteChainee= new ListeChaineeImpl();
		
		/* Ex 1 (Ennonce): SuiteChainee (1, {1, 3}, {2, 3}, 1) doit donner MaListe : {1, 3}, {2, 3}, {1, 2, 3} */
		
		
		val1.add(1);
		val1.add(3);
		val2.add(2);
		val2.add(3);
		
		int tri = 1;

		list = suiteChainee.build(1, val1, val2, tri);

		System.out.println("Exemple 1: ");
		StringBuilder chaineContent1 = new StringBuilder();
		for(int i = 0; i < list.getSize(); i++){
			chaineContent1.append(" "+list.getAt(i));
		}	
		System.out.println("MaListe :" + chaineContent1);
		System.out.println("");
		
		val1.remove(0);
		val1.remove(0);
		val2.remove(0);
		val2.remove(0);

		/* Ex 2 (Ennonce): SuiteChainee (1, {1, 3}, {2, 3}, 0) doit donner MaListe : {1, 3}, {2, 3}, {3, 2, 1} */
		
		val1.add(1);
		val1.add(3);
		val2.add(2);
		val2.add(3);
		
		tri = 0;

		list = suiteChainee.build(1, val1, val2, tri);

		System.out.println("Exemple 2: ");
		StringBuilder chaineContent2 = new StringBuilder();
		for(int i = 0; i < list.getSize(); i++){
			chaineContent2.append(" " + list.getAt(i));
		}	
		System.out.println("MaListe :"+chaineContent2);
	}

}
