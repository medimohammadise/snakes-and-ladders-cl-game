package my.practice.codechallenges.puzzle;


import java.util.ArrayList;
import java.util.Map;

import my.practice.codechallenges.puzzle.domain.Game;
import my.practice.codechallenges.puzzle.domain.Player;
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
		int playerConfigCount=0;
		boolean playerNotFound=true;
		AsciiArtManager.printAsciArt("ascii_art","snakeAndLadder");
		System.out.println("Welcome to Sanke and Ladder Game");
		MenuManager mainMenu=new MenuManager();
		Player player=mainMenu.dispalyPlayerInfoMenu();
		
		
		
		Map<String,Object> playerInfoMap=null;
		Map<String,Object> gameConfiguration= GameConfigurationPorcessor.readDataFromFile("configuration","inputConfigFile.json");
		ArrayList<Map> playListConfiguration=(ArrayList<Map>) gameConfiguration.get("playList");
		
	    while (playerConfigCount<playListConfiguration.size() && playerNotFound)
	    {
	    		playerInfoMap=(Map<String,Object>)playListConfiguration.get(playerConfigCount).get("playerInfo");
	    		if (player.getId().equals(playerInfoMap.get("id")))
	    		{
	    			playerInfoMap=(Map<String,Object>)playListConfiguration.get(playerConfigCount);
	    			if (playerInfoMap.get("currentposition")!=null&&Integer.valueOf(playerInfoMap.get("currentposition").toString())>0)
	    			{
						System.out.println("You have not completed your last challange , We are loading your last status... ");
						mainMenu.dispalyMainMenu(new Game(playerInfoMap,player),true);
						
	    			}
	    			else
	    			{
	    				System.out.println("You found your profile , We are loading new game for You... ");
	    				mainMenu.dispalyMainMenu(new Game(playerInfoMap,player),false);
	    			}
	    			playerNotFound=false;
	    		}
	    		playerConfigCount++;
	    }
	    
		if (playerNotFound) mainMenu.dispalyMainMenu(new Game((Map<String,Object>)playListConfiguration.get(0),player),false);
		
	   
    }
}
