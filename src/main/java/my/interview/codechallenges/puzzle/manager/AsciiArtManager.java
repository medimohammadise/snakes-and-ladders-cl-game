package my.interview.codechallenges.puzzle.manager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;



public class AsciiArtManager {
	 private static String readAsString(String basePath, String filename) {
	        InputStream resourceAsStream = AsciiArtManager.class.getResourceAsStream("/"+basePath+"/"+filename);
	        if (resourceAsStream!=null) 
	        	   return new BufferedReader(new InputStreamReader(resourceAsStream)).lines().collect(Collectors.joining("\n"));
	        else
	        	   return null;
	  }
	 public static void printAsciArt(String basePath,String filename) {
		 System.out.println(readAsString(basePath,filename));
	 }
	 
}
