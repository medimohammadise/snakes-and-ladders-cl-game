package my.practice.codechallenges.puzzle;


import java.util.Map;

import my.practice.codechallenges.puzzle.domain.Game;
import my.practice.codechallenges.puzzle.io.GameConfigurationPorcessor;
import my.practice.codechallenges.puzzle.manager.AsciiArtManager;
import my.practice.codechallenges.puzzle.manager.MenuManager;

/**
 * Hello world!
 *
 */
public class App 
{
    
	public static void main( String[] args )
    {
		// TODO these parameters have to be read and checked for
		
		AsciiArtManager.printAsciArt("ascii_art","snakeAndLadder");
		System.out.println("Welcome to Sanke and Ladder Game");
		Map<String,Object> gameConfiguration= GameConfigurationPorcessor.readDataFromFile("configuration","inputConfigFile.json");
		MenuManager mainMenu=new MenuManager(new Game(gameConfiguration));
		
		mainMenu.dispalyMainMenu();
		
	   
    }
}
