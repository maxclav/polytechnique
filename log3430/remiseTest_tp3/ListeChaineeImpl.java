/**
 * @class			: ListeChaineeImpl
 * @interface		: ListeChainee
 * @author	 		: Ons mlouki
 * 					  Ons.mlouki@gmail.com
 */
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class ListeChaineeImpl implements ListeChainee {
	
	public MyList build(String op, ArrayList<Object> val1, ArrayList<Object> val2, int tri, int multVal1, int multVal2) throws IOException{
		
		ArrayList<Object> resultats = new ArrayList<Object>();
		StringBuilder chaineContent=new StringBuilder();
		
		MyList list = new MyListImpl();	
		
		list.add(val1);
		chaineContent.append(""+val1);
			 
		list.add(val2);
		chaineContent.append(" "+val2);
		
		
		// Multiplier l'ensemble val1 par le nombre entier multVal1
		if (multVal1 == 0 || multVal1 == 1 || multVal1 == 2 || multVal1 == 3)
		{
			for(int i = 0 ; i < val1.size() ; i++) {
				Integer value = (Integer)val1.get(i);
				int result = value * multVal1;
				val1.set(i, result);
			}

		}
		else
		{
			// Error
		}

		// Multiplier l'ensemble val2 par le nombre entier multVal2
		if (multVal2 == 0 || multVal2 == 1 || multVal2 == 2 || multVal2 == 3)
		{
			for(int i = 0 ; i < val2.size() ; i++) {
				Integer value = (Integer)val2.get(i);
				int result = value * multVal2;
				val2.set(i, result);
			}
		}
		else
		{
			// Error
		}
		
		
		SetCalculator myCalculator= new SetCalculatorImpl();

		int i=1;
					
		switch (op){
		   case "union":{
			   resultats = myCalculator.union(list.getAt(i-1),list.getAt(i));
			   };
		   break;
		   case "intersection":{
			   resultats = myCalculator.intersection(list.getAt(i-1),list.getAt(i));
			   };
		   break;
		   case "difference":{
			   resultats = myCalculator.difference(list.getAt(i-1),list.getAt(i));
			   };
		   break;
		   case "symDifference":{
			   resultats = myCalculator.symDifference(list.getAt(i-1),list.getAt(i));
			   };
		   break;
		   case "isSubset":{
			   resultats = myCalculator.isSubset(list.getAt(i-1),list.getAt(i));
			   };
		   break; 
		   case "isSuperset":{
			   resultats = myCalculator.isSuperset(list.getAt(i-1),list.getAt(i));
			   };
		   break;
		   default:System.out.println("operateur "+op+" non identifié");
			   break;
		}

		if (tri == 1)
		{
			//Collections.sort(resultats, Collections.);
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
		
		// Trier (selon Boolean tri) en ordre croissant (si tri=1) ou décroissant (si tri=0)
		// to do
		
		return list;
	}
}
