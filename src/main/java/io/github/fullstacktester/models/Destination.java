package io.github.fullstacktester.models;

public enum Destination {
    CORNWALL("Cornwall"),
    CRANTOCK_BEACH(" - Crantock Beach");

    private final String text;

    Destination(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
            return this.text;
    }
}
