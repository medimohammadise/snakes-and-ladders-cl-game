package my.practice.codechallenges.puzzle.domain;
import static my.practice.codechallenges.puzzle.setting.Constants.DIC_MINVALUE;
import static my.practice.codechallenges.puzzle.setting.Constants.DIC_MAXVALUE;
public class Dice {
	public int roll() {
		return random(DIC_MINVALUE, DIC_MAXVALUE);
	}

	private int random(int min, int max) {
		assert min < max;
		return (int) (min + Math.round((max - min) * Math.random()));
	}
}
