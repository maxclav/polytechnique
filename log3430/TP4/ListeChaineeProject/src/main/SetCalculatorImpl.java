/**
 * @class			: SetCalculatorImpl
 * @interface		: SetCalculator
 * @author	 		: Ons mlouki
 * 					  Ons.mlouki@gmail.com
 * 					  
 */
package main;

import java.util.ArrayList;

public class SetCalculatorImpl implements SetCalculator {
		
	  public ArrayList<Object> union(ArrayList<Object> setA, ArrayList<Object> setB) {
		  ArrayList<Object> tmp = new ArrayList<Object> (setA);
		    for(Object a:setB){
		    	if(!setA.contains(a))
		    		tmp.add(a);
		    }
		    return tmp;
		  }

		  public ArrayList<Object> intersection(ArrayList<Object> setA, ArrayList<Object> setB) {
			  ArrayList<Object> tmp = new ArrayList<Object>();
		    for (Object x : setA)
		      if (setB.contains(x))
		        tmp.add(x);
		    return tmp;
		  }

		  public ArrayList<Object> difference(ArrayList<Object> setA, ArrayList<Object> setB) {
			ArrayList<Object> tmp = new ArrayList<Object>(setA);
		    tmp.removeAll(setB);
		    return tmp;
		  }

		  public ArrayList<Object> symDifference(ArrayList<Object> setA, ArrayList<Object> setB) {
			ArrayList<Object> tmpA;
			ArrayList<Object> tmpB;

		    tmpA = union(setA, setB);
		    tmpB = intersection(setA, setB);
		    return difference(tmpA, tmpB);
		  }

		  public <T> ArrayList<T> isSubset(ArrayList<Object> setA, ArrayList<Object> setB) {
			  ArrayList<T> tmp = new ArrayList<T>();
			  tmp.add((T) String.valueOf(setB.containsAll(setA)));
		    return tmp;
		  }

		  public <T> ArrayList<T> isSuperset(ArrayList<Object> setA, ArrayList<Object> setB) {
			  ArrayList<T> tmp = new ArrayList<T>(); 
			  tmp.add((T) String.valueOf(setA.containsAll(setB)));
			  return tmp;
		  }
		  
		  
//		  public static <T> Set<T> union(Set<T> setA, Set<T> setB) {
//			    Set<T> tmp = new TreeSet<T>(setA);
//			    tmp.addAll(setB);
//			    return tmp;
//			  }
//
//			  public static <T> Set<T> intersection(Set<T> setA, Set<T> setB) {
//			    Set<T> tmp = new TreeSet<T>();
//			    for (T x : setA)
//			      if (setB.contains(x))
//			        tmp.add(x);
//			    return tmp;
//			  }
//
//			  public static <T> Set<T> difference(Set<T> setA, Set<T> setB) {
//			    Set<T> tmp = new TreeSet<T>(setA);
//			    tmp.removeAll(setB);
//			    return tmp;
//			  }
//
//			  public static <T> Set<T> symDifference(Set<T> setA, Set<T> setB) {
//			    Set<T> tmpA;
//			    Set<T> tmpB;
//
//			    tmpA = union(setA, setB);
//			    tmpB = intersection(setA, setB);
//			    return difference(tmpA, tmpB);
//			  }
//
//			  public static <T> boolean isSubset(Set<T> setA, Set<T> setB) {
//			    return setB.containsAll(setA);
//			  }
//
//			  public static <T> boolean isSuperset(Set<T> setA, Set<T> setB) {
//			    return setA.containsAll(setB);
//			  }
	
}
