package my.interview.codechallenges.puzzle.domain;

import java.util.LinkedList;

import my.interview.codechallenges.puzzle.io.UserInputProcessor;

public class Game {
	private LinkedList<Palyer> players = new LinkedList<Palyer>();
	private Board board = null;
	private Palyer winner;
	private UserInputProcessor userInputProcessor;

	public Game(String[] playerNames, int numSquares, int[][] snakes, int[][] ladders) {
		makeBoard(numSquares, ladders, snakes);
		makePlayers(playerNames);
		userInputProcessor=new UserInputProcessor();
	}

	private void makeBoard(int numSquares, int[][] ladders, int[][] snakes) {
		board= new Board(numSquares,ladders, snakes);
	}

	private void makePlayers(String[] playerNames) {
		assert playerNames.length > 0 : "There must be some player";
		System.out.println("Players are : ");
		int i = 1;
		for (String str : playerNames) {
			Palyer player = new Palyer(str);
			players.add(player); // adds to the end System.out.println(i + ". " + str); i++;
		}
	}

	public void play() {
		assert !players.isEmpty() : "No players to play";
		assert board != null : "No scoreboard to play";
		Dice dice = new Dice();
		startGame();
		System.out.println("Initial state : \n" + this);
		while (notOver()) {
			userInputProcessor.tryReadingInputAsInt(2);
			
			int roll = dice.roll();
			
			System.out.println("Current player is " + currentPlayer() + " and rolls " + roll);
			movePlayer(roll,currentPlayer());
			System.out.println(board.mapView());
			System.out.println("State : \n" + this);
		}
		System.out.println(winner + " has won.");
	}

	private void movePlayer(int roll,Palyer player) {
		//Palyer currentPlayer = players.remove(); // from the head ?
		//currentPlayer.moveForward(roll);
		player.moveForward(roll);
		//players.add(currentPlayer); // to the tail
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
