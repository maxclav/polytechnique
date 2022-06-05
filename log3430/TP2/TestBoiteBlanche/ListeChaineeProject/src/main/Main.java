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
		//suiteChainee.build(op, val1, val2) 
		//op{union, intersection, difference, symDifference, isSubset, isSuperset}
		//val1,val2 are sets
////////////////////////////////////////////////////Build parameter//////////////////////////		
		ArrayList<Object> val1 = new ArrayList<Object>();
		ArrayList<Object> val2 = new ArrayList<Object>();
		
		val1.add(4);
		val1.add(1);
		val2.add(1);
		val2.add(1);
///////////////////////////////////////////////////listeChainee////////////////////////////////////////		
		MyList list = new MyListImpl();	
		ListeChainee suiteChainee= new ListeChaineeImpl();
		
		list = suiteChainee.build("isSuperset", val1, val2);
//////////////////////////////////////////////////listeChainee printer//////////////////////////////////	
		StringBuilder chaineContent=new StringBuilder();
		for(int i=0; i<list.getSize();i++){
			chaineContent.append(" "+list.getAt(i));
		}	
		System.out.println("MaListe :"+chaineContent);
////////////////////////////////////////////////gestion d'une listeChainee			
		list.add(val1);
		list.getAt(0);
		list.removeAt(3);
		list.removeItem(val1);
		list.setAt(val1, 0);
		list.getSize();
		list.reset();
//////////////////////////////////////////////////listeChainee printer//////////////////////////////////
		StringBuilder chaineContent2=new StringBuilder();
		for(int i=0; i<list.getSize();i++){
			chaineContent2.append(" "+list.getAt(i));
		}	
		System.out.println("MaListe :"+chaineContent2);
	}

}
