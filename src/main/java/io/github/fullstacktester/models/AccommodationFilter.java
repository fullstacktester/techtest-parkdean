package io.github.fullstacktester.models;

public class AccommodationFilter {
    public AccommodationType accommodationType;
    public Destination destination;
    public String arrivalMonth;
    public String numberOfNights;
    public String arrivalDate;
    public boolean withPets;

    @Override
    public String toString() {
        return "AccommodationFilter{" +
                "accommodationType=" + accommodationType +
                ", destination=" + destination +
                ", arrivalMonth='" + arrivalMonth + '\'' +
                ", numberOfNights='" + numberOfNights + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", withPets=" + withPets +
                '}';
    }
}
