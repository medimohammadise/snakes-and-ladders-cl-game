package my.interview.codechallenges.puzzle.domain;

public class Palyer {
	private Square square = null;
	private String name;
	public Palyer(String str) {
		this.name=str;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public int position() {
		return this.square.getPosition();
	}

	public void setSquare(Square square) {
		this.square=square;
		
	}
	@Override
	public String toString () {
		return name; 
	}

	public void moveForward(int moves) {
		assert moves>0 : "non-positive moves"; 
		square.leave(this);
		square = square.moveAndLand(moves); 
		square.enter(this);
		
	}

	public boolean wins() {
		
		return square.islastSquare();
	}

}
