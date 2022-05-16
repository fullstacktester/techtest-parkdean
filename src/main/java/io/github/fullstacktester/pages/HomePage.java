package io.github.fullstacktester.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

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

    @FindBy(id = "holiday-nights-hol")
    WebElementFacade filterArrivalDate;

    @FindBy(id = "holiday-nights-hol")
    WebElementFacade filterWithPets;

    @FindBy(css = "#booking-search-form button")
    WebElementFacade filterSearch;
}
