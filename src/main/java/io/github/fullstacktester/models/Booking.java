package io.github.fullstacktester.models;

public class Booking {
    public AccommodationFilter filters;
    public String accommodationName;
    public int baseCostinPence;

    public Booking(AccommodationFilter accommodationFilter) {
        this.filters = accommodationFilter;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "filters=" + filters.toString() +
                ", accommodationName='" + accommodationName + '\'' +
                ", baseCostinPence=" + baseCostinPence +
                '}';
    }
}


