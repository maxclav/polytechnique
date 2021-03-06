package main;

import java.util.ArrayList;

public interface SetCalculator {
	public ArrayList<Object> union(ArrayList<Object> setA, ArrayList<Object> setB);
	public ArrayList<Object> intersection(ArrayList<Object> setA, ArrayList<Object> setB);
	public ArrayList<Object> difference(ArrayList<Object> setA, ArrayList<Object> setB);
	public ArrayList<Object> symDifference(ArrayList<Object> setA, ArrayList<Object> setB);
}
