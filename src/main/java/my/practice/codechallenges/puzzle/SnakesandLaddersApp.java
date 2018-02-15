package my.practice.codechallenges.puzzle;

import java.util.Map;
import my.practice.codechallenges.puzzle.domain.Game;
import my.practice.codechallenges.puzzle.domain.Player;
import my.practice.codechallenges.puzzle.io.GameConfigurationPorcessor;
import my.practice.codechallenges.puzzle.manager.AsciiArtManager;
import my.practice.codechallenges.puzzle.manager.GameStateManager;
import my.practice.codechallenges.puzzle.manager.MenuManager;
import static my.practice.codechallenges.puzzle.setting.Constants.ASCII_ART_GAME_NAME;

public class SnakesandLaddersApp {

	public static void main(String[] args) {
		AsciiArtManager.printAsciArt(ASCII_ART_GAME_NAME);
		System.out.println("Welcome to Sanke and Ladder Game");
		MenuManager mainMenu = new MenuManager();
		Player player = mainMenu.dispalyPlayerInfoMenu();

		Map<String, Object> gameConfiguration = GameConfigurationPorcessor.readDataFromFile(player.getId(), false);
		// There is no previous profile for this player
		if (gameConfiguration == null) {
			System.out.println("welcome " + player.getName()
					+ "! We do not have previous game record , Initializing new game for You.");
			gameConfiguration = GameConfigurationPorcessor.readDataFromFile(player.getId(), true);
			new Game(gameConfiguration, player).play(mainMenu, false);
		} else {
			//existingPlayer = GameStateManager.getPlayerInfo(gameConfiguration);
			
				if (GameStateManager.getPreviousePosition(gameConfiguration) > 0) {
					System.out.println(player.getName()+"!  You have not completed your last challange");
					if (mainMenu.resumePreviousGame())
						new Game(gameConfiguration, player).play(mainMenu, true);
					else {
						System.out.println(player.getName()+"!  We are loading new game for You... ");
						mainMenu.dispalyMainMenu(new Game(gameConfiguration, player),player, false);
					}
				}
			}
		

	}
}
