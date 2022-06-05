/**
 * @class			: ListeChaineeImpl
 * @interface		: ListeChainee
 * @author	 		: Ons mlouki
 * 					  Ons.mlouki@gmail.com
 */
package main;

import java.io.IOException;
import java.util.ArrayList;

public class ListeChaineeImpl implements ListeChainee {
	
	public MyList build(String op,ArrayList<Object> val1, ArrayList<Object> val2) throws IOException{
		
		
		StringBuilder chaineContent=new StringBuilder();
		
		MyList list = new MyListImpl();	
		
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
