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
		Map<String,Object> gameConfiguration= GameConfigurationPorcessor.readDataFromFile("configuration",player.getId()+".json");
		if (gameConfiguration==null)
		{
			System.out.println("welcome "+player.getName()+ "! We do not have previouse game record , Initializing new game for You.");
			gameConfiguration= GameConfigurationPorcessor.readDataFromFile("configuration","inputConfigFile.json");
		}
		//ArrayList<Map> playListConfiguration=(ArrayList<Map>) gameConfiguration.get("playList");
		playerInfoMap=(Map<String,Object>)gameConfiguration.get("playerInfo");
		if (player.getId().equals(playerInfoMap.get("id")))
		{
		
			if (playerInfoMap.get("currentposition")!=null&& !"".equals(playerInfoMap.get("currentposition")) && Integer.valueOf(playerInfoMap.get("currentposition").toString())>0)
			{
				System.out.println("You have not completed your last challange , We are loading your last status... ");
				mainMenu.dispalyMainMenu(new Game(gameConfiguration,player),true);
				
			}
			else
			{
				System.out.println("You found your profile , We are loading new game for You... ");
				mainMenu.dispalyMainMenu(new Game(gameConfiguration,player),false);
			}
			playerNotFound=false;
		}
		
	    /*while (playerConfigCount<playListConfiguration.size() && playerNotFound)
	    {
	    		
	    		playerConfigCount++;
	    }*/
	   // GameConfigurationPorcessor.saveStateIntoJson("configuration","inputConfigFile.json", (Map<String,Object>)playListConfiguration.get(0),player.getId());
		if (playerNotFound) mainMenu.dispalyMainMenu(new Game(gameConfiguration,player),false);
		
	   
    }
}
