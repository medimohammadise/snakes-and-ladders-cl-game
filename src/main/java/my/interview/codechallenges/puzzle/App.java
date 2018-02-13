package my.interview.codechallenges.puzzle;


import my.interview.codechallenges.puzzle.domain.Game;
import my.interview.codechallenges.puzzle.io.UserInputProcessor;
import my.interview.codechallenges.puzzle.manager.AsciiArtManager;
import my.interview.codechallenges.puzzle.manager.MenuManager;

/**
 * Hello world!
 *
 */
public class App 
{
    
	public static void main( String[] args )
    {
		// TODO these parameters have to be read and checked for
		String[] playerNames = {"Monica"}; 
		int numSquares = 6;
		// for the user first square is1 at position 1 but
		// internally is at 0
		int[][] snakesFromTo = { {5,11} };
		int[][] laddersFromTo = { {2,6} , {7,9} ,{5,12}};
		AsciiArtManager.printAsciArt("ascii_art","snakeAndLadder");
		MenuManager mainMenu=new MenuManager(new Game(playerNames , numSquares , snakesFromTo , laddersFromTo));
		
		mainMenu.dispalyMainMenu();
	
    }
}
