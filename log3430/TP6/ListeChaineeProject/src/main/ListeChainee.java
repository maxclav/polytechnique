package main;

import java.io.IOException;
import java.util.ArrayList;

public interface ListeChainee {
	public MyList build(Integer op, ArrayList<Object> val1, ArrayList<Object> val2, int tri)throws IOException;
}
