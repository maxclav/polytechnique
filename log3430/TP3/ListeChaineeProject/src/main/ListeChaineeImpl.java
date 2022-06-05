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
	
	public MyList build(String op,ArrayList<Object> val1, ArrayList<Object> val2, Boolean tri, Integer multVal1, Integer multVal2) throws IOException{
		
		
		StringBuilder chaineContent=new StringBuilder();
		
		MyList list = new MyListImpl();	
		
		for(int i=0 ; i<val1.size() ; i++) {
			Integer value = (Integer)val1.get(i);
			int result = value*multVal1;
			val1.set(i, result);
		}
		
		for(int i=0 ; i<val2.size() ; i++) {
			Integer value = (Integer)val2.get(i);
			int result = value*multVal2;
			val2.set(i, result);
		}
		
		list.add(val1);
		chaineContent.append(""+val1);
			 
		list.add(val2);
		chaineContent.append(" "+val2);
		
		SetCalculator myCalculator= new SetCalculatorImpl();
		 
		int i=1;
					
		switch (op){
		   case "union":{
			   list.add(myCalculator.union(list.getAt(i-1),list.getAt(i)));
			   i++;
			   chaineContent.append(" "+list.getAt(i));
			   
			   };
		   break;
		   case "intersection":{
			   list.add(myCalculator.intersection(list.getAt(i-1),list.getAt(i)));
			   i++;
			   chaineContent.append(" "+list.getAt(i)); 
			   };
		   break;
		   case "difference":{
			   list.add(myCalculator.difference(list.getAt(i-1),list.getAt(i)));
			   i++;
			   chaineContent.append(" "+list.getAt(i)); 
			   };
		   break;
		   case "symDifference":{
			   list.add(myCalculator.symDifference(list.getAt(i-1),list.getAt(i)));
			   i++;
			   chaineContent.append(" "+list.getAt(i));
			   };
		   break;
		   case "isSubset":{
			   list.add(myCalculator.isSubset(list.getAt(i-1),list.getAt(i)));
			   i++;
			   chaineContent.append(" "+list.getAt(i)); 
			   };
		   break; 
		   case "isSuperset":{
			   list.add(myCalculator.isSuperset(list.getAt(i-1),list.getAt(i)));
			   i++;
			   chaineContent.append(" "+list.getAt(i)); 
			   };
		   break;
		   default:System.out.println("operateur "+op+" non identifié");
			   break;
		}
		
		return list;
	}
}
