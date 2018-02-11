package my.interview.codechallenges.puzzle.domain;

public  class  SquareType {
	
	private int id;
	private int headPosition;
	private int tailPosition;
	public SquareType(int tailPosition,int headPosition,int id) {
		this.headPosition=headPosition;
		this.tailPosition=tailPosition;
		this.id=id;
	}
	public int getHeadPosition() {
		return headPosition;
	}
	public void setHeadPosition(int headPosition) {
		this.headPosition = headPosition;
	}
	public int getTailPosition() {
		return tailPosition;
	}
	public void setTailPosition(int tailPosition) {
		this.tailPosition = tailPosition;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
