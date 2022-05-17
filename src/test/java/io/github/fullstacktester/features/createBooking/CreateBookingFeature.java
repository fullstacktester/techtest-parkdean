package io.github.fullstacktester.features.createBooking;

import io.github.fullstacktester.steps.BookingUiSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class CreateBookingFeature {

    @Steps
    public BookingUiSteps user;

    @Managed
    WebDriver driver;

    @Test
    public void CanAccessTheHomepage() {
        user.isOnTheHomePage();

        assertThat(driver.getCurrentUrl())
                .isEqualTo("https://www.parkdeanresorts.co.uk/");
    }

    @Test
    public void CanFilterHolidaysOnHomePage() {
        user.isOnTheHomePage();

        user.filtersTheAccommodation();

        user.selectsAnAccommodation();

        user.hasBookedAccommodation();
    }
}
