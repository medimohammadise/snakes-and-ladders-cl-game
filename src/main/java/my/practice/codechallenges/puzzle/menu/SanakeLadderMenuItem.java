package my.practice.codechallenges.puzzle.menu;
/*
 * Menu for the time that You are playing
 */

public enum SanakeLadderMenuItem {
    DICE("Throw the Dice"),
    HIDE_MAP("Turn OFF/ON the Map"),
    SHOW_LEGEND("Show Map Legend"),
    MAIN_MENU("Return to Main Menu"),
    SAVE_EXIT("Save & Leave the game");

    private final String description;

    SanakeLadderMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
