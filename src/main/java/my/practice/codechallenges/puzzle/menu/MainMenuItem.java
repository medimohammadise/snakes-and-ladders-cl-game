package my.practice.codechallenges.puzzle.menu;


public enum MainMenuItem {
    START("Start the game"),
    LOAD("Load previouse game"),
    CONFIG("Change default configuration"),
    RANKING_VIEW("Dispaly winners in the history"),
    EXIT("Leave the game");

    private final String description;

    MainMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
