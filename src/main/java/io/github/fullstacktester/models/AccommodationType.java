package io.github.fullstacktester.models;

public enum AccommodationType {
    ALL("View All"),
    CARAVAN("Caravan"),
    LODGE("Lodge"),
    CHALET("Chalet");

    private final String text;

    AccommodationType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
