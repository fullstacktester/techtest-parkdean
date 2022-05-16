package io.github.fullstacktester.steps;

import io.github.fullstacktester.pages.HomePage;
import net.thucydides.core.annotations.Step;

public class BookingUiSteps {

    HomePage homePage;

    @Step
    public void isOnTheHomePage() {
        homePage.open();
    }

}