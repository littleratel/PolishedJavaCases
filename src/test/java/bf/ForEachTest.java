package bf;

import java.util.ArrayList;
import java.util.List;

public class ForEachTest {

	public static void main(String[] args) {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(1);
		lst.add(2);
		lst.add(3);
		lst.add(4);
		lst.add(5);
		
		lst.forEach(i -> {
			if(i==3) {
				lst.add(11);
			}
			
			System.out.println(i);
			i = null;
		});
		
	}

}
