package my.practice.codechallenges.puzzle.manager;
import my.practice.codechallenges.puzzle.domain.Game;
import my.practice.codechallenges.puzzle.domain.Player;
import my.practice.codechallenges.puzzle.io.UserInputProcessor;
import my.practice.codechallenges.puzzle.menu.MainMenuItem;
import my.practice.codechallenges.puzzle.menu.PlayerMenuItem;
import my.practice.codechallenges.puzzle.menu.SanakeLadderMenuItem;
public class MenuManager {
	private static final String TAB = "\t";
	private static final String SEPARATOR = ": ";
	
	 UserInputProcessor userInputProcessor=null;
	int menuChoce=-1;
	 public MenuManager() {
		 
		 userInputProcessor=new UserInputProcessor();
	 }
	 public Player dispalyPlayerInfoMenu() {
		 String playerName,playerId="";
		 System.out.println(TAB+PlayerMenuItem.PLAYER_NAME);
		 playerName=userInputProcessor.readUserInputAsString();
		 System.out.println(TAB+PlayerMenuItem.PLAYER_ID);
		 playerId=userInputProcessor.readUserInputAsString();
		 return new Player(playerName,playerId)	;	 
			 
	}
	 
	 public void dispalyMainMenu(Game game,boolean resume) {
		 int menuKey=1;
		 for (MainMenuItem menuItem:MainMenuItem.values())
		 {
			 System.out.println(TAB+menuKey+SEPARATOR+menuItem);
			 menuKey++;
		 }
		
		 
		 do
		 {
			 menuChoce=userInputProcessor.tryReadingInputAsInt(MainMenuItem.values().length);
			 switch (menuChoce){
				 case 1:
					 game.play(this);
		             break;
		         case 2:
		        	 	System.out.println(menuChoce);
		             break;
		         case 3:
		        	 	System.out.println(menuChoce);
		             break;
		         default:
			 }
		}
		while(menuChoce!=3) ;
		 
	 }
	 
	 public int dispalySnakesandLadderMenu() {
			System.out.println("Welcome to Sanke and Ladder Game");
			 int menuKey=1;
			 for (SanakeLadderMenuItem menuItem:SanakeLadderMenuItem.values())
			 {
				 System.out.println(TAB+menuKey+SEPARATOR+menuItem);
				 menuKey++;
			 }
			 UserInputProcessor userInputProcessor=new UserInputProcessor();
			 
			 do
			 {
				 menuChoce=userInputProcessor.tryReadingInputAsInt(SanakeLadderMenuItem.values().length);
				 return menuChoce;
			}
			while(menuChoce!=3) ;
			 
		 }
}
