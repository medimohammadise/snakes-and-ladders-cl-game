package my.practice.codechallenges.puzzle.io;

import java.util.InputMismatchException;
import java.util.Scanner;

import my.practice.codechallenges.puzzle.exception.UserInputParseException;

import static my.practice.codechallenges.puzzle.setting.Constants.MAX_RETRY_FOR_INPUT;

public class UserInputProcessor {

	private final Scanner scanner;

	public UserInputProcessor() {
		this.scanner = new Scanner(System.in);

	}

	int readInt(int optionsSize) {
		try {
			int result = Integer.parseInt(scanner.nextLine());
			return result;
		} catch (NumberFormatException | InputMismatchException e) {
			throw new UserInputParseException(
					"Sorry, but command number you entered is not even a number. Please try harder next time.");
		}
	}

	public int tryReadingInputAsInt(int optionsSize) {
		for (int i = 1; i <= MAX_RETRY_FOR_INPUT; i++) {
			try {
				return readInt(optionsSize);
			} catch (Exception e) {
				if (MAX_RETRY_FOR_INPUT == i) {
					throw e;
				} else {
					System.out.println(e.getMessage());
				}
			}
		}

		return -1;
	}

	public String readUserInputAsString() {
		for (int i = 1; i <= MAX_RETRY_FOR_INPUT; i++) {
			String inputString = scanner.nextLine();
			if (inputString == null || "".equals(inputString.trim()))
				System.out.print("Sorry, You need to enter this value ");
			else
				return inputString.trim();

		}
		throw new UserInputParseException("\nSorry , You maximum number for input try exceeded,We have to quit!");

	}

}
