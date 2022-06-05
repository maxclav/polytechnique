/**
 * @class			: ListeChaineeImpl
 * @interface		: ListeChainee
 * @author	 		: Ons mlouki
 * 					  Ons.mlouki@gmail.com
 */
package main;

import java.io.IOException;
import java.util.*;

public class ListeChaineeImpl implements ListeChainee {
	
	public MyList build(Integer op, ArrayList<Object> val1, ArrayList<Object> val2, int tri) throws IOException{

		ArrayList<Object> resultats = new ArrayList<Object>();
		String operator = ""; // Pour print l'operateur
		
		StringBuilder chaineContent = new StringBuilder();
		MyList list = new MyListImpl();	
		
		for(int i = 0 ; i < val1.size() ; i++) {
			Integer value = (Integer)val1.get(i);
			int result = value;
			val1.set(i, result);
		}
		
		for(int i = 0 ; i < val2.size() ; i++) {
			Integer value = (Integer)val2.get(i);
			int result = value;
			val2.set(i, result);
		}
		
		list.add(val1);
		chaineContent.append("" + val1);
			 
		list.add(val2);
		chaineContent.append(" " + val2);
		
		SetCalculator myCalculator = new SetCalculatorImpl();
					
		/*
		 * op = Une valeur entière qui indique l’opérateur utilisé :
		 * 1 pour l’opérateur “union”
		 * 2 pour l’opérateur “intersection”
		 * 3 pour l’opérateur “difference”
		 * 4 pour l’opérateur “symmetric difference”
		 * 
		 */

		int i = 1;
		switch (op){
		
		   case 1:{
			   resultats = myCalculator.union(list.getAt(i-1),list.getAt(i));
			   operator = "union";
			   };
		   break;
		   
		   case 2:{
			   resultats = myCalculator.intersection(list.getAt(i-1),list.getAt(i));
			   operator = "intersection";
			   };
		   break;
		   
		   case 3:{
			   resultats = myCalculator.difference(list.getAt(i-1),list.getAt(i));
			   operator = "difference";
			   };
		   break;
		   
		   case 4:{
			   resultats = myCalculator.symDifference(list.getAt(i-1),list.getAt(i));
			   operator = "symDifference";
			   };
		   break;
		   
		   default:System.out.println("Erreur: operateur " + operator + " #" + op + " non identifie. ");
			   break;
		}
		
		/// Pour trier l'ensemble
		if (tri == 1)
		{
			Collections.sort(resultats, null);
		}
		if (tri == 0)
		{
			Collections.sort(resultats, Collections.reverseOrder()); 
		}
		else
		{
			// Error
		}
		
		list.add(resultats);
		i++;
		chaineContent.append(" "+list.getAt(i)); 
		
		return list;
	}
}
