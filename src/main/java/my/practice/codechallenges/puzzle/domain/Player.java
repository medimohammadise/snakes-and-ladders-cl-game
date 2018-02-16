package my.practice.codechallenges.puzzle.domain;

public class Player {
	private String id;
	private String name;
	private int currentPosition;
	private int star;
	private Square square = null;
	public Player(String name,String id) {
		this.name=name;
		this.id=id;
	}
	public Player(String name,String id,int star,int currentposition) {
		this.name=name;
		this.id=id;
		this.star=star;
		this.currentPosition=currentposition;		
	}
	public String getName() {
		return name;
	}

	public int position() {
		return this.square.getPosition();
	}

	public void setSquare(Square square) {
		this.square=square;
		
	}

	/*
	*  Main function for moving player 
    */
	public void moveForward(int moves) {
		assert moves>0 : "non-positive moves"; 
		square.leave(this);
		square = square.moveAndLand(moves); 
		square.enter(this);
		setCurrentPosition(square.getPosition());
		
	}

	public boolean wins() {	
		return square.islastSquare();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Square getSquare() {
		return square;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	@Override
	public String toString () {
		return name; 
	}

}
