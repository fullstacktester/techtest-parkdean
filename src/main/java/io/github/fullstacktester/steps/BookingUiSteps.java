package io.github.fullstacktester.steps;

import io.github.fullstacktester.models.AccommodationFilter;
import io.github.fullstacktester.models.Booking;
import io.github.fullstacktester.pages.ChooseYourAccommodationPage;
import io.github.fullstacktester.pages.HomePage;
import io.github.fullstacktester.pages.OptionalExtrasPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static io.github.fullstacktester.models.AccommodationType.CARAVAN;
import static io.github.fullstacktester.models.Destination.CRANTOCK_BEACH;
import static net.serenitybdd.core.Serenity.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingUiSteps {

    HomePage homePage;
    ChooseYourAccommodationPage chooseYourAccommodationPage;
    OptionalExtrasPage optionalExtrasPage;

    private final AccommodationFilter accommodationFilter = new AccommodationFilter();
    private final Booking booking = new Booking(accommodationFilter);

    @Step
    public void isOnTheHomePage() {
        homePage.open();
        assertThat(getDriver().getCurrentUrl())
                .isEqualTo("https://www.parkdeanresorts.co.uk/");
    }

    @Step
    public void filtersTheAccommodation() {
        accommodationFilter.accommodationType = CARAVAN;
        accommodationFilter.destination = CRANTOCK_BEACH;
        accommodationFilter.withPets = false;

        homePage.selectAccommodationType(accommodationFilter.accommodationType);
        homePage.selectDestination(accommodationFilter.destination);

        GetAndSelectMonthOfArrival();
        GetAndSelectNumberOfNights();
        GetAndSelectDateOfArrival();

        homePage.selectWithPets(accommodationFilter.withPets);

        homePage.filterSearch();
    }

    private void GetAndSelectDateOfArrival() {
        List<String> datesOfArrival = homePage.getDatesOfArrival();

        String dateOfArrival = datesOfArrival.get(2);
        // TODO Wrangle the date string into a suitable comparator, possibly by converting to LocalDate.
        accommodationFilter.arrivalDate = dateOfArrival.contains("-") ? dateOfArrival.substring(0, dateOfArrival.indexOf("-") - 1) : dateOfArrival;
        homePage.selectDateOfArrival(dateOfArrival);
    }

    private void GetAndSelectNumberOfNights() {
        List<String> numbersOfNights = homePage.getNumbersOfNights();

        String numberOfNights = numbersOfNights.get(2);
        accommodationFilter.numberOfNights = numberOfNights;
        homePage.selectNumberOfNights(numberOfNights);
    }

    private void GetAndSelectMonthOfArrival() {
        List<String> monthsOfArrival = homePage.getMonthsOfArrival();

        String monthOfArrival = monthsOfArrival.get(2);
        accommodationFilter.arrivalMonth = monthOfArrival;
        homePage.selectMonthOfArrival(monthOfArrival);
    }

    @Step
    public void selectsAnAccommodation() {
        chooseYourAccommodationPage.waitForLoad();

        int bookingIndex = 0;
        booking.accommodationName = chooseYourAccommodationPage.getAccommodationName(bookingIndex);
        booking.baseCostinPence = chooseYourAccommodationPage.getBasePriceInPence(bookingIndex);
        chooseYourAccommodationPage.bookNow(bookingIndex);
    }

    @Step
    public void hasBookedAccommodation() {
        Serenity.recordReportData().asEvidence().withTitle("Booking Details").andContents(booking.toString());
        optionalExtrasPage.waitForLoad();

        String expectedHolidayPark = booking.filters.destination.toString().replace(" - ", "");
        assertThat(optionalExtrasPage.getHolidayPark())
                .contains(expectedHolidayPark);

        assertThat(optionalExtrasPage.getNights())
                .isEqualTo(booking.filters.numberOfNights);
/*
        TODO Add this back in once there's a valid date object to compare against.
        assertThat(optionalExtrasPage.getDateOfArrival())
                .isEqualTo(booking.filters.arrivalDate);
*/

        assertThat(optionalExtrasPage.getTotalCostInPence())
                .isEqualTo(booking.baseCostinPence);
    }

    @Step
    public void isOnTheOptionalExtrasPage() {
        isOnTheHomePage();
        filtersTheAccommodation();
        selectsAnAccommodation();

        optionalExtrasPage.waitForLoad();
    }

    @Step
    public void addsOptionalCot(int numberOfCots) {
        optionalExtrasPage.addCots(numberOfCots);
    }

    @Step
    public void canSeeCostOfCots(int numberOfCots) {
        assertThat(optionalExtrasPage.getCotSummaryPrice())
                .isEqualTo(1500 * numberOfCots);
    }

    @Step
    public void addsOptionalHighChair(int numberOfHighChairs) {
        optionalExtrasPage.addHighChairs(numberOfHighChairs);
    }

    @Step
    public void canSeeCostOfHighChairs(int numberOfHighChairs) {
        assertThat(optionalExtrasPage.getHighChairSummaryPrice())
                .isEqualTo(1500 * numberOfHighChairs);
    }

    @Step
    public void addsOptionalCancellationCover() {
        optionalExtrasPage.selectCancellationCover();
    }

    @Step
    public void canSeeCostOfCancellationCover() {
        assertThat(optionalExtrasPage.getCancellationCoverSummaryPrice())
                .isEqualTo(2500);
    }

    @Step
    public void addsOptionalPriorityArrival() {
        optionalExtrasPage.selectPriorityArrival();
    }

    @Step
    public void canSeeCostOfPriorityArrival() {
        assertThat(optionalExtrasPage.getPriorityArrivalSummaryPrice())
                .isEqualTo(5000);
    }
}