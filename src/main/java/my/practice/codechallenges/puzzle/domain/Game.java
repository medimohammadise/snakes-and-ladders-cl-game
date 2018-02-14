package my.practice.codechallenges.puzzle.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import my.practice.codechallenges.puzzle.io.GameConfigurationPorcessor;
import my.practice.codechallenges.puzzle.io.UserInputProcessor;
import my.practice.codechallenges.puzzle.manager.AsciiArtManager;
import my.practice.codechallenges.puzzle.manager.MenuManager;

public class Game {
	private LinkedList<Player> players = new LinkedList<Player>();
	private Board board = null;
	private Player winner;
	private UserInputProcessor userInputProcessor;
    private Map<String,Object> gameConfiguration;
	public Game(Map<String,Object> gameConfiguration,Player player) {
		this.gameConfiguration=gameConfiguration;
		int numSquares = Integer.valueOf(gameConfiguration.get("numberOfSqures").toString());
		makeBoard(numSquares, (ArrayList)gameConfiguration.get("ladders"),  (ArrayList)gameConfiguration.get("sankes"));
		makePlayers(player);
		userInputProcessor = new UserInputProcessor();
	}

	private void makeBoard(int numSquares, ArrayList ladders, ArrayList snakes) {
		board = new Board(numSquares, ladders, snakes);
	}

	private void makePlayers(Player player) {
		players.add(player); 	
	}

	public void play(MenuManager menuManager) {
		int roll=0;
		boolean stopped=false;
		assert !players.isEmpty() : "No players to play";
		assert board != null : "No scoreboard to play";
		Dice dice = new Dice();
		startGame();
		System.out.println(board.mapView());	
		
		while (notOver() || !stopped) {
			int gameChoce=menuManager.dispalySnakesandLadderMenu();
			switch (gameChoce){
			 case 1:
				 roll = dice.roll();
				 movePlayer(roll, currentPlayer());
			     System.out.println(board.mapView());
			    
	             break;
	         case 2:
	        	 	System.out.println("map value");
	             break;
	         case 3:
	        	 	 GameConfigurationPorcessor.saveStateIntoJson("configuration","inputConfigFile.json",this.gameConfiguration);
	        	 	 stopped=true;
	             break;
	         default:
		 }
			
		}
		if (!stopped) 
		{
			AsciiArtManager.printAsciArt("ascii_art", "GreateYouWon");
		     System.out.println(winner + " has won.");
		}
		
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

	private void placePlayersAtFirstSquare() {
		for (Player player : players) {
			board.firstSquare().enter(player);
		}

	}

	private boolean notOver() {
		return winner == null;
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
