package my.practice.codechallenges.puzzle.domain;

public class Dice {

	private static final int MINVALUE = 1;
	private static final int MAXVALUE = 6;

	public int roll() {
		return random(MINVALUE, MAXVALUE);
	}

	private int random(int min, int max) {
		assert min < max;
		return (int) (min + Math.round((max - min) * Math.random()));
	}

}
