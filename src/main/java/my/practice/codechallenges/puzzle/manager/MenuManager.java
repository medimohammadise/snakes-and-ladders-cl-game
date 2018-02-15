package my.practice.codechallenges.puzzle.manager;

import static my.practice.codechallenges.puzzle.setting.Constants.MENU_SEPARATOR;
import static my.practice.codechallenges.puzzle.setting.Constants.NEW_LINE;
import static my.practice.codechallenges.puzzle.setting.Constants.TAB;
import static my.practice.codechallenges.puzzle.setting.Constants.YES;

import java.util.Map;

import my.practice.codechallenges.puzzle.domain.Game;
import my.practice.codechallenges.puzzle.domain.Player;
import my.practice.codechallenges.puzzle.io.GameConfigurationPorcessor;
import my.practice.codechallenges.puzzle.io.UserInputProcessor;
import my.practice.codechallenges.puzzle.menu.MainMenuItem;
import my.practice.codechallenges.puzzle.menu.PlayerMenuItem;
import my.practice.codechallenges.puzzle.menu.SanakeLadderMenuItem;
public class MenuManager {
	UserInputProcessor userInputProcessor = null;
	int menuChoce = -1;

	public MenuManager() {

		userInputProcessor = new UserInputProcessor();
	}

	private void printMenuItem(String menuItem) {
		System.out.print(TAB + menuItem);
	}
    
	public boolean resumePreviousGame() {
		printMenuItem(PlayerMenuItem.RESUME_PREVIOUS_GAME .toString() + MENU_SEPARATOR);
		return (userInputProcessor.readUserInputAsString().equalsIgnoreCase(YES)?true:false);
	}
	public Player dispalyPlayerInfoMenu() {
		String playerName, playerId = "";
		printMenuItem(PlayerMenuItem.PLAYER_NAME.toString() + MENU_SEPARATOR);
		playerName = userInputProcessor.readUserInputAsString();
		
		printMenuItem(PlayerMenuItem.PLAYER_ID.toString() + MENU_SEPARATOR);
		playerId = userInputProcessor.readUserInputAsString();
		return new Player(playerName, playerId);
	}

	public void dispalyMainMenu(Game game,Player player, boolean resume) {
		if (!resume) {
			int menuKey = 1;
			for (MainMenuItem menuItem : MainMenuItem.values()) {
				printMenuItem(menuKey + MENU_SEPARATOR + menuItem + NEW_LINE);
				menuKey++;
			}
			do {
				menuChoce = userInputProcessor.tryReadingInputAsInt(MainMenuItem.values().length);
				switch (menuChoce) {
				case 1: //PLAY
					game.play(this, resume);
					break;
				case 2: //LOAD PREVIOUSE ONE 
					Map<String, Object> gameConfiguration = GameConfigurationPorcessor.readDataFromFile(player.getId(), false);
					new Game(gameConfiguration, player).play(this, true);
				break;
				case 3: //CHANGE CONFIG
					System.out.println("Sorry , This feature is not avilable in this relaese , You can do this by changig Player json file, It is crystal clear!");
					break;
				case 4: //DISPALY WINNERS
					System.out.println("****Name*************ID ***********STAR**");
					for (Player winner:GameConfigurationPorcessor.getWinnersList())
						System.out.println(winner.getName() + "  "+winner.getId() +" " +winner.getStar());
					break;	
				case 5: //EXIT
					game.exitGame(false);
					break;
				default:
				}
			} while (menuChoce != 3);
		} else

			game.play(this, resume);

	}

	public int dispalySnakesandLadderMenu() {
		int menuKey = 1;
		for (SanakeLadderMenuItem menuItem : SanakeLadderMenuItem.values()) {
			printMenuItem(menuKey + MENU_SEPARATOR + menuItem + NEW_LINE);
			menuKey++;
		}
		UserInputProcessor userInputProcessor = new UserInputProcessor();

		do {
			menuChoce = userInputProcessor.tryReadingInputAsInt(SanakeLadderMenuItem.values().length);
			return menuChoce;
		} while (menuChoce != 3);

	}
}
