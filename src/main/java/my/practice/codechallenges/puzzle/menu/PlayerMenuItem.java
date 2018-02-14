package my.practice.codechallenges.puzzle.menu;


public enum PlayerMenuItem {
    PLAYER_NAME("Please enter your name : "),
    PLAYER_ID("Please enter your id"),
    SAVE_EXIT("Leave the game");

    private final String description;

    PlayerMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
