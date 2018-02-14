package my.practice.codechallenges.puzzle.domain;

public  class  SquareType {
	
	private int id;
	private String name;
	private int headPosition;
	private int tailPosition;
	public SquareType(int headPosition,int tailPosition,int id,String name) {
		this.headPosition=headPosition;
		this.tailPosition=tailPosition;
		this.id=id;
		this.name=name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
