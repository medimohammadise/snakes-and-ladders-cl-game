package my.interview.codechallenges.puzzle.manager;

import java.util.HashMap;
import java.util.Map;

public class ContextManager {
	 private static final Map<Class, Object> context = new HashMap<Class, Object>();
	 static {
	        loadMenus();
	    }
	 private static void loadMenus() {
		 
	 }
}
