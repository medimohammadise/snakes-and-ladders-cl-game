package my.practice.codechallenges.puzzle.io;



import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputProcessor {

    private final Scanner scanner;
    private static final int MAX_RETRY = 5;


    public UserInputProcessor() {
        this.scanner = new Scanner(System.in);
        
        
    }
    
	int readInt(int optionsSize) {
        try {
            int result = Integer.parseInt(scanner.nextLine());
            /*if (isNotBetweenZeroAndMaxExclusive(result, optionsSize)) {
                throw new UserInputParseException("Sorry, but command number is either below 0 or too high. Please try again.");
            } else {*/
                return result;
            //}
        } catch (NumberFormatException | InputMismatchException e) {
            //throw new UserInputParseException("Sorry, but command number you entered is not even a number. Please try harder next time.");
        }
		return optionsSize;
    }
	 public int tryReadingInputAsInt(int optionsSize) {
	        for (int i = 1; i <= MAX_RETRY; i++) {
	            try {
	                return readInt(optionsSize);
	            } catch (Exception e) {
	                if (MAX_RETRY == i) {
	                    throw e;
	                } else {
	                    System.out.println(e.getMessage());
	                }
	            }
	        }

	        return -1;
	    }
}
