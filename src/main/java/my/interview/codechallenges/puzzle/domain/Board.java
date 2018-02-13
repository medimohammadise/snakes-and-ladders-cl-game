package my.interview.codechallenges.puzzle.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
	private Square square = null;
	private Map<Integer, Square> squares = null;
	private static int MINNUMSQUARES = 10;
	private int numSquares;

	public Board(int numSquares, int[][] ladders, int[][] snakes) {
		assert numSquares > MINNUMSQUARES : "There must be at least " + MINNUMSQUARES + " squares";
		this.numSquares = numSquares;
		squares = new HashMap<>();
		makeSquares(numSquares);
		makeLadders(ladders);
		makeSnakes(snakes);

	}

	private void makeSquares(int numSquares) {
		//System.out.println("There are " + numSquares + " squares");
		int squareCount = 0;
		for (int i = 0; i < numSquares; i++) {
			for (int j = 0; j < numSquares; j++) {
				Square square = new Square(squareCount, this);
				squares.put(squareCount, square);
				squareCount++;
			}
		}

	}

	private void makeLadders(int[][] ladders) {
		for (int i = 0;i<ladders.length;i++)
		{
			squares.get(ladders[i][0]).setSquareType(new Ladder(ladders[i][0],ladders[i][1],i));
			squares.get(ladders[i][1]).setSquareType(new Ladder(ladders[i][0],ladders[i][1],i));
		}
	}

	private void makeSnakes(int[][] snakes) {
		for (int i = 0;i<snakes.length;i++)
		{
			squares.get(snakes[i][0]).setSquareType(new Sanke(snakes[i][0],snakes[i][1],i));
			squares.get(snakes[i][1]).setSquareType(new Sanke(snakes[i][0],snakes[i][1],i));
		}
	}

	public Square firstSquare() {
		return squares.get(0);
	}

	public Square lastSquare() {
		return squares.get(squares.size()-1);

	}

	public Square findSquare(int position) {
		assert (position > 0) && (position < squares.size()) : "inexistent square";
		return squares.get(position);
	}

	public String mapView() {
		
		int y = 0;
		StringBuilder sb = new StringBuilder();
		// Location[][] map = world.getMap();
		sb.append("\n");
		//appendColsHeader(sb, numSquares);
		while (y < squares.size()) {
			sb.append(String.format("%-4" + "s", " "));
			for (int col = 0; col < numSquares; col++) {
				if (squares.get(y).getPlayer()!=null)
					sb.append(String.format("%-4" + "s", "P"));
				else
				if (squares.get(y).getSquareType() instanceof Sanke)
				{
					
					if (squares.get(y).getFallDownOrGoUpPosition()<0)
					    sb.append(String.format("%-4" + "s", "S"+squares.get(y).getSquareType().getId()+"->"));
					else
						sb.append(String.format("%-4" + "s", "-S"+squares.get(y).getSquareType().getId()));
				}
				else
				if (squares.get(y).getSquareType() instanceof Ladder)
				{
						if (squares.get(y).getFallDownOrGoUpPosition()>0)
						    sb.append(String.format("%-4" + "s", "L"+squares.get(y).getSquareType().getId()));
						else
							sb.append(String.format("%-4" + "s", "L"+squares.get(y).getSquareType().getId()+"^"));
				}	
				else
					sb.append(String.format("%-4" + "s", y));
				y++;
			}
			sb.append("\n");
			/*
			 * for (int x = 0; x < map[y].length; x++) { if (playerIsHere(player, x, y))
			 * appendPlayerMark(sb); else appendLocationMark(sb, map[x][y]); }
			 */

		}

		// appendRowsHeader(sb, y); sb.append("\n"); } appendColsHeader(sb,
		// map[0].length);

		return sb.toString();

	}

	private void appendColsHeader(StringBuilder sb, int colsNumber) {
		sb.append(String.format("%-4" + "s", " "));
		for (int i = 0; i < colsNumber; i++) {
			sb.append(String.format("%-4" + "d", i));
		}
		sb.append("\n");
	}

}
