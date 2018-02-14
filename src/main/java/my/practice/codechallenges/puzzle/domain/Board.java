package my.practice.codechallenges.puzzle.domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import my.practice.codechallenges.puzzle.io.Colors;

public class Board {
	private Square square = null;
	private Map<Integer, Square> squares = null;
	private static int MINNUMSQUARES = 10;
	private int numSquares;

	public Board(int numSquares, ArrayList ladders, ArrayList snakes) {
		assert numSquares > MINNUMSQUARES : "There must be at least " + MINNUMSQUARES + " squares";
		this.numSquares = numSquares;
		squares = new HashMap<>();
		makeSquares(numSquares);
		makeLadders(ladders,"L" );
		makeLadders(snakes,"S");

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

	private void makeLadders(ArrayList ladders,String squareType) {
		int x=0;
		int y=0;
		int ladderCount=0;
		String ladderName="";
		SquareType newRole=null;
		
		for (Object entry :ladders) {
			Map<String,Object> listMap=(Map)entry;
			for (Map.Entry listEntry :listMap.entrySet()) {
				ladderName=(String) listEntry.getKey();
				Map<String,String> cordinateMap=(Map)listEntry.getValue();
				
				for (Map.Entry cordinateEntry :cordinateMap.entrySet()) {
					if (cordinateEntry.getKey().equals("x")) 
					    x=Integer.valueOf(cordinateEntry.getValue().toString());
					else
						y=Integer.valueOf(cordinateEntry.getValue().toString());
				}
				
				if (squareType.equals("L"))
					 newRole=new Ladder(x,y,ladderCount,ladderName);
				else
					 newRole=new Sanke(x,y,ladderCount,ladderName);
				
				squares.get(x).setSquareType(newRole);
				squares.get(y).setSquareType(newRole);	
				ladderCount++;
			}
			
			
			
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
					
				 
					sb.append(String.format("%-5" + "s", Colors.YELLOW_BG.format("P"))+"   ");
				else
				if (squares.get(y).getSquareType() instanceof Sanke)
				{
					
					if (squares.get(y).getFallDownOrGoUpPosition(y)<0)
					    sb.append(String.format("%5" + "s", Colors.RED.format("S"+squares.get(y).getSquareType().getId()+"->"))+"   ");
					else
						sb.append(String.format("%-5" + "s", "-S"+squares.get(y).getSquareType().getId()));
				}
				else
				if (squares.get(y).getSquareType() instanceof Ladder)
				{
						if (squares.get(y).getFallDownOrGoUpPosition(y)>0)
						    sb.append(String.format("%5" + "s", Colors.GREEN.format("L"+squares.get(y).getSquareType().getId()+"^"))+"   ");
						else
							sb.append(String.format("%-5" + "s", "L"+squares.get(y).getSquareType().getId()));
				}	
				else
					sb.append(String.format("%-5" + "d", y));
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
