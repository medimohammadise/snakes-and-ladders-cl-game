package my.practice.codechallenges.puzzle.menu;
/*
 * Menu for asking player information
 */
public enum PlayerMenuItem {
    PLAYER_NAME("Please enter your name"),
    PLAYER_ID("Please enter your id"),
    SAVE_EXIT("Leave the game"),
    RESUME_PREVIOUS_GAME("Do You want to resume previous? (Y/N)");
    private final String description;

    PlayerMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
