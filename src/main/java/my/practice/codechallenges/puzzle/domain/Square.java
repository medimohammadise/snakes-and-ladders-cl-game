package my.practice.codechallenges.puzzle.domain;

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
		Square newSquare= board.findSquare(position + moves);
		if (newSquare.getSquareType() instanceof Ladder && newSquare.getFallDownOrGoUpPosition(position + moves)>0)
		{
			System.out.println("You are moving up in the ladder to "+newSquare.getSquareType().getHeadPosition());	
			return board.findSquare(newSquare.getSquareType().getHeadPosition());
		}
		else
		if (newSquare.getSquareType() instanceof Sanke && newSquare.getFallDownOrGoUpPosition(position + moves)<0)
		{
			System.out.println("You are falling down because the snake bit You!" + newSquare.getSquareType().getTailPosition() );	
			return board.findSquare(newSquare.getSquareType().getTailPosition());
				
		}
			
		return newSquare;

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
	public int getFallDownOrGoUpPosition(int position) {
		if (this.getSquareType() instanceof Sanke && this.getSquareType().getHeadPosition()==position)
			return -1*this.getSquareType().getTailPosition();
		if (this.getSquareType() instanceof Ladder && this.getSquareType().getTailPosition()==position)
			return this.getSquareType().getTailPosition();
		
			
		return 0;
	}
	

}
