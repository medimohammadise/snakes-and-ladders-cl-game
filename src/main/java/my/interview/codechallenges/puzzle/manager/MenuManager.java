package my.interview.codechallenges.puzzle.manager;
import my.interview.codechallenges.puzzle.domain.Game;
import my.interview.codechallenges.puzzle.io.UserInputProcessor;
import my.interview.codechallenges.puzzle.menu.MainMenuItem;
import my.interview.codechallenges.puzzle.menu.SanakeLadderMenuItem;
public class MenuManager {
	private static final String TAB = "\t";
	private static final String SEPARATOR = ": ";
	private Game game;
	int menuChoce=-1;
	 public MenuManager(Game game) {
		 this.game=game;
	 }
	 
	 public void dispalyMainMenu() {
		System.out.println("Welcome to Sanke and Ladder Game");
		 int menuKey=1;
		 for (MainMenuItem menuItem:MainMenuItem.values())
		 {
			 System.out.println(TAB+menuKey+SEPARATOR+menuItem);
			 menuKey++;
		 }
		 UserInputProcessor userInputProcessor=new UserInputProcessor();
		 
		 do
		 {
			 menuChoce=userInputProcessor.tryReadingInputAsInt(MainMenuItem.values().length);
			 switch (menuChoce){
				 case 1:
					 
					 this.game.play(this);
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
