package my.practice.codechallenges.puzzle.domain;

public class Square {
	private Player player = null;
	private Board board = null;
	private int position;
	private SquareType squareType;
	public Square(int position, Board board) {
		//assert position>=0 : "Square number must be positive or zero" ;
		this.position = position;
		this.board = board;
	}

	public void enter(Player player) { 
		this.setPlayer(player); 
		player.setSquare(this);
	}

	private void setPlayer(Player player) {
		this.player=player;
		
	}

	public void leave(Player palyer) {
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
		
		Square newSquare=this.findRelativeSquare(moves);
			System.out.println("move from "
			+ (this.getPosition()) + " to "
			+ (this.findRelativeSquare(moves).getPosition()));
		return  newSquare.isOccupied() ? newSquare.findLastSquare() : newSquare;
	}

	private boolean isOccupied() {
		return this.getPlayer() != null;
	}

	
	public Player getPlayer() {
		
		return player;
	}

	private Square findRelativeSquare(int moves) {
		Square newSquare= board.findSquare(position + moves);
		if (newSquare.getSquareType() instanceof Ladder && newSquare.getFallDownOrGoUpPosition(position + moves)>0)
		{
			System.out.println("You are moving up in the ladder to "+newSquare.getSquareType().getTailPosition());	
			return board.findSquare(newSquare.getSquareType().getTailPosition());
		}
		else
		if (newSquare.getSquareType() instanceof Sanke && newSquare.getFallDownOrGoUpPosition(position + moves)<0)
		{
			System.out.println("You are falling down because the snake bit You at this square " + newSquare.getSquareType().getHeadPosition() );	
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
		return this.position==findLastSquare().getPosition();
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
		if (this.getSquareType() instanceof Ladder && this.getSquareType().getHeadPosition()==position)
			return this.getSquareType().getTailPosition();
		
			
		return 0;
	}
	

}
