package my.practice.codechallenges.puzzle.menu;


public enum SanakeLadderMenuItem {
    DICE("Throw the Dice"),
    HIDE_MAP("Turn off the Map"),
    SHOW_LEGEND("Show Map Legend"),
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
