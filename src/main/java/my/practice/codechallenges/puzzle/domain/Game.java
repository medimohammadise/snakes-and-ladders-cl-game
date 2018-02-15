package my.practice.codechallenges.puzzle.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import my.practice.codechallenges.puzzle.io.Colors;
import my.practice.codechallenges.puzzle.io.GameConfigurationPorcessor;
import my.practice.codechallenges.puzzle.io.UserInputProcessor;
import my.practice.codechallenges.puzzle.manager.AsciiArtManager;
import my.practice.codechallenges.puzzle.manager.GameStateManager;
import my.practice.codechallenges.puzzle.manager.MenuManager;

public class Game {
	private LinkedList<Player> players = new LinkedList<Player>();
	private Board board = null;
	private Player winner;
	private UserInputProcessor userInputProcessor;
	private Map<String, Object> gameConfiguration;

	public Game(Map<String, Object> gameConfiguration, Player player) {
		this.gameConfiguration = gameConfiguration;
		int numSquares = Integer.valueOf(gameConfiguration.get("numberOfSqures").toString());
		makeBoard(numSquares, (Map) gameConfiguration.get("ladders"), (Map) gameConfiguration.get("sankes"));
		makePlayers(player);
		userInputProcessor = new UserInputProcessor();
	}

	private void makeBoard(int numSquares, Map ladders, Map snakes) {
		board = new Board(numSquares, ladders, snakes);
	}

	private void makePlayers(Player player) {
		players.add(player);
	}

	public void play(MenuManager menuManager, boolean resume) {
		int roll = 0;
		boolean stopped = false;
		assert !players.isEmpty() : "No players to play";
		assert board != null : "No scoreboard to play";
		Dice dice = new Dice();
		if (resume)
			resumeGame(this.players.get(0));
		else
			startGame();
		System.out.println(board.mapView());

		while (notOver() && !stopped) {
			int gameChoce = menuManager.dispalySnakesandLadderMenu();
			switch (gameChoce) {
			case 1: // DICE
				roll = dice.roll();
				AsciiArtManager.printAsciArt("Dice-" + roll);

				movePlayer(roll, currentPlayer());
				System.out.println(board.mapView());

				break;
			case 2:// Show Map
				board.setShowMap(!board.isShowMap());
				break;
			case 3: //Show legend
				System.out.println(showMapLegend());
				break;
			case 4: //Main Menu
				menuManager.dispalyMainMenu(this, players.getFirst(), false);
				break;	
			case 5: //save configuration
				this.gameConfiguration = GameStateManager.svaePosition(gameConfiguration,
						players.get(0).getCurrentPosition());

				exitGame(true);
				stopped = true;

				break;
			default:
			}

		}
		if (!stopped) {
			AsciiArtManager.printAsciArt("GreateYouWon");
			GameStateManager.saveAsWinner(gameConfiguration);

			exitGame(true);
			stopped = true;

		}

	}

	private String showMapLegend() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("****************************\n");
		sb.append(String.format("%-5" + "s", Colors.YELLOW_BG.format("P")) + "   " + "Player\n");
		sb.append(String.format("%5" + "s", Colors.RED.format("S->")) + "   " + "Snake's Head\n");
		sb.append(String.format("%5" + "s", Colors.RED.format("-S")) + "   " + "Snake's Tail\n");
		sb.append(String.format("%5" + "s", Colors.GREEN.format("L^")) + "   " + "Ladder's Head\n");
		sb.append(String.format("%5" + "s", Colors.GREEN.format("L")) + "   " + "Ladder's Tail\n");
		sb.append("All snakes and ladders soecified by unique");
		return sb.toString();
	}

	private void movePlayer(int roll, Player player) {
		// Player currentPlayer = players.remove(); // from the head ?
		// currentPlayer.moveForward(roll);
		player.moveForward(roll);
		// players.add(currentPlayer); // to the tail
		if (player.wins())
			winner = player;

	}

	private Player currentPlayer() {
		assert players.size() > 0;
		return players.peek();
	}

	private void startGame() {
		placePlayersAtFirstSquare();
		winner = null;

	}

	private void resumeGame(Player palyer) {
		placePlayersAtPreviouseSquare(palyer);
		winner = null;

	}

	private void placePlayersAtFirstSquare() {
		for (Player player : players) {
			board.firstSquare().enter(player);
		}

	}

	private void placePlayersAtPreviouseSquare(Player palyer) {

		board.findSquare(GameStateManager.getPreviousePosition(gameConfiguration)).enter(palyer);
		;

	}

	private boolean notOver() {
		return winner == null;
	}

	public void exitGame(boolean saveBeforeExit) {
		if (saveBeforeExit)
			GameConfigurationPorcessor.saveStateIntoJson("configuration", players.getFirst().getId() + ".json",
					this.gameConfiguration, players.getFirst().getId());

		System.out.println("You game sucessfull saved. Game existing within 20 second...");
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override
	public String toString() {
		String str = new String();
		for (Player player : players) {
			str += player.getName() + " is at square " + (player.position()) + "\n";
		}
		return str;
	}

}
