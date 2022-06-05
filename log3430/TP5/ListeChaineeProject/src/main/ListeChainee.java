package main;

import java.io.IOException;
import java.util.ArrayList;

public interface ListeChainee {
	public MyList build(String op,ArrayList<Object> val1, ArrayList<Object> val2, Boolean tri, Integer multVal1, Integer multVal2)throws IOException;
}
