package my.practice.codechallenges.puzzle.manager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import static my.practice.codechallenges.puzzle.setting.Constants.ASCII_ART_DIR_NAME;
import my.practice.codechallenges.puzzle.io.ConsoleColors;

public class AsciiArtManager {
	 private static String readAsString(String basePath, String filename) {
	        InputStream resourceAsStream = AsciiArtManager.class.getResourceAsStream("/"+basePath+"/"+filename);
	        if (resourceAsStream!=null) 
	        	   return new BufferedReader(new InputStreamReader(resourceAsStream)).lines().collect(Collectors.joining("\n"));
	        else
	        	   return null;
	  }
	 public static void printAsciArt(String filename) {
		 String artString=readAsString(ASCII_ART_DIR_NAME,filename);
		 System.out.println(ConsoleColors.RED_BOLD+artString);
		 System.out.println(ConsoleColors.RESET);
		 
	 }
}
