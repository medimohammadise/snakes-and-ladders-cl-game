package my.interview.codechallenges.puzzle.domain;

public class Square {
	private Palyer player = null;
	private Board board = null;
	private int position;
	private SquareType squareType;
	public Square(int position, Board board) {
		//assert position>=0 : "Square number must be positive or zero" ;
		this.position = position;
		this.board = board;
	}

	public void enter(Palyer player) { 
		this.setPlayer(player); 
		player.setSquare(this);
	}

	private void setPlayer(Palyer player) {
		this.player=player;
		
	}

	public void leave(Palyer palyer) {
		this.setPlayer(null);
		
	}

	public Square moveAndLand(int moves) {
		int lastPosition = this.findLastSquare().getPosition();
		int presentPosition = this.getPosition();
		if (presentPosition+moves>lastPosition) {
			System.out.println("Should go to "
			+ (presentPosition+moves)
			+ " beyond last square " + (lastPosition)
			+ " so don’t move");
			return this;
		}
		else {
			System.out.println("move from "
			+ (this.getPosition()) + " to "
			+ (this.findRelativeSquare(moves).getPosition()));
			
		}
		Square newSquare=this.findRelativeSquare(moves);
		return  newSquare.isOccupied() ? newSquare.findLastSquare() : newSquare;
	}

	private boolean isOccupied() {
		return this.getPlayer() != null;
	}

	
	public Palyer getPlayer() {
		
		return player;
	}

	private Square findRelativeSquare(int moves) {
		return board.findSquare(position + moves);

	}

	public int getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	private Square findLastSquare() {
		return board.lastSquare();
	}
	public Boolean islastSquare() {
		return position==findLastSquare().getPosition();
	}

	public SquareType getSquareType() {
		return squareType;
	}

	public void setSquareType(SquareType squareType) {
		this.squareType = squareType;
	}
	public int getFallDownOrGoUpPosition() {
		if (this.getSquareType() instanceof Sanke && this.getSquareType().getHeadPosition()==getPosition())
			return -1*this.getSquareType().getTailPosition();
		if (this.getSquareType() instanceof Ladder && this.getSquareType().getTailPosition()==getPosition())
			return this.getSquareType().getTailPosition();
		
			
		return 0;
	}
	

}
