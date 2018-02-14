package my.practice.codechallenges.puzzle.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import my.practice.codechallenges.puzzle.io.UserInputProcessor;
import my.practice.codechallenges.puzzle.manager.AsciiArtManager;
import my.practice.codechallenges.puzzle.manager.MenuManager;

public class Game {
	private LinkedList<Palyer> players = new LinkedList<Palyer>();
	private Board board = null;
	private Palyer winner;
	private UserInputProcessor userInputProcessor;

	public Game(Map<String,Object> gameConfiguration) {
		String[] playerNames = {"Monica"}; 
		int numSquares = Integer.valueOf(gameConfiguration.get("numberOfSqures").toString());
		// for the user first square is1 at position 1 but
		// internally is at 0
		int[][] snakesFromTo = { {5,11} };
		int[][] laddersFromTo = { {2,6} , {7,9} ,{5,12}};
		
		
		makeBoard(numSquares, (ArrayList)gameConfiguration.get("ladders"),  (ArrayList)gameConfiguration.get("sankes"));
		makePlayers(playerNames);
		userInputProcessor = new UserInputProcessor();
	}

	private void makeBoard(int numSquares, ArrayList ladders, ArrayList snakes) {
		board = new Board(numSquares, ladders, snakes);
	}

	private void makePlayers(String[] playerNames) {
		assert playerNames.length > 0 : "There must be some player";
		for (String str : playerNames) {
			Palyer player = new Palyer(str);
			players.add(player); // adds to the end System.out.println(i + ". " + str); i++;
		}
	}

	public void play(MenuManager menuManager) {
		int roll=0;
		assert !players.isEmpty() : "No players to play";
		assert board != null : "No scoreboard to play";
		Dice dice = new Dice();
		startGame();
		System.out.println(board.mapView());	
		while (notOver()) {
			int gameChoce=menuManager.dispalySnakesandLadderMenu();
			switch (gameChoce){
			 case 1:
				 
				 roll = dice.roll();
	             break;
	         case 2:
	        	 System.out.println("map value");
	             break;
	         case 3:
	        	 	System.out.println("Save and exit");
	             break;
	         default:
		 }
			//userInputProcessor.tryReadingInputAsInt(2);
			
			//System.out.println("Current player is " + currentPlayer() + " and rolls " + roll);
			movePlayer(roll, currentPlayer());
			System.out.println(board.mapView());
			//System.out.println("State : \n" + this);
		}
		
		AsciiArtManager.printAsciArt("ascii_art", "GreateYouWon");
		System.out.println(winner + " has won.");
	}

	private void movePlayer(int roll, Palyer player) {
		// Palyer currentPlayer = players.remove(); // from the head ?
		// currentPlayer.moveForward(roll);
		player.moveForward(roll);
		// players.add(currentPlayer); // to the tail
		if (player.wins())
			winner = player;

	}

	private Palyer currentPlayer() {
		assert players.size() > 0;
		return players.peek();
	}

	private void startGame() {
		placePlayersAtFirstSquare();
		winner = null;

	}

	private void placePlayersAtFirstSquare() {
		for (Palyer player : players) {
			board.firstSquare().enter(player);
		}

	}

	private boolean notOver() {
		return winner == null;
	}

	@Override
	public String toString() {
		String str = new String();
		for (Palyer player : players) {
			str += player.getName() + " is at square " + (player.position()) + "\n";
		}
		return str;
	}
}
