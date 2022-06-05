package main;

import java.io.IOException;
import java.util.ArrayList;

public interface ListeChainee {
	public MyList build(String op, ArrayList<Object> val1, ArrayList<Object> val2, int tri, int multVal1, int multVal2)throws IOException;
}
