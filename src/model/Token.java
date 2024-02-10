package model;

public enum Token {
    YELLOW("Y"),
    RED("R"),
    EMPTY("-");

    private final String representation;

    private Token(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
