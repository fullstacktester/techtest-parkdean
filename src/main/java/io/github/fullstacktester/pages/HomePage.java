package io.github.fullstacktester.pages;

import io.github.fullstacktester.models.AccommodationType;
import io.github.fullstacktester.models.Destination;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.awaitility.Durations;

import java.util.List;

import static org.awaitility.Awaitility.await;

@DefaultUrl("https://www.parkdeanresorts.co.uk/")
public class HomePage extends PageObject {

    @FindBy(className = "aspect-holidays")
    WebElementFacade tabHolidays;

    @FindBy(id = "accommodation-type")
    WebElementFacade filterAccommodationType;

    @FindBy(id = "holiday-destination-hol")
    WebElementFacade filterDestination;

    @FindBy(id = "holiday-month-hol")
    WebElementFacade filterArrivalMonth;

    @FindBy(id = "holiday-nights-hol")
    WebElementFacade filterNights;

    @FindBy(id = "holiday-arrival-hol")
    WebElementFacade filterArrivalDate;

    @FindBy(id = "holiday-pets-hol")
    WebElementFacade filterWithPets;

    @FindBy(css = "#booking-search-form button")
    WebElementFacade filterSearch;

    public void selectAccommodationType(AccommodationType accommodationType) {
        filterAccommodationType.select().byVisibleText(accommodationType.toString());
    }

    public void selectDestination(Destination destination) {
        filterDestination.select().byVisibleText(destination.toString());
    }

    public List<String> getMonthsOfArrival() {
        return await()
                .pollDelay(Durations.FIVE_HUNDRED_MILLISECONDS)
                .atMost(Durations.TEN_SECONDS)
                .until(filterArrivalMonth::getSelectOptions, c -> c.size() > 3);
    }

    public void selectMonthOfArrival(String monthOfArrival) {
        filterArrivalMonth.waitUntilEnabled();
        filterArrivalMonth.select().byVisibleText(monthOfArrival);
    }

    public List<String> getNumbersOfNights() {
        return await()
                .pollDelay(Durations.FIVE_HUNDRED_MILLISECONDS)
                .atMost(Durations.TEN_SECONDS)
                .until(filterNights::getSelectOptions, c -> c.size() > 3);
    }

    public void selectNumberOfNights(String numberOfNights) {
        filterNights.waitUntilEnabled();
        filterNights.select().byVisibleText(numberOfNights);
    }

    public List<String> getDatesOfArrival() {
        return await()
                .pollDelay(Durations.FIVE_HUNDRED_MILLISECONDS)
                .atMost(Durations.TEN_SECONDS)
                .until(filterArrivalDate::getSelectOptions, c -> c.size() > 3);
    }

    public void selectDateOfArrival(String dateOfArrival) {
        filterArrivalDate.waitUntilEnabled();
        filterArrivalDate.select().byVisibleText(dateOfArrival);
    }

    public void selectWithPets(boolean withPets) {
        String value = withPets ? "Yes" : "No";

        filterWithPets.selectByVisibleText(value);
    }

    public void filterSearch() {
        filterSearch.click();
    }


}
