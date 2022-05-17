package io.github.fullstacktester.features.createBooking;

import io.github.fullstacktester.steps.BookingUiSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class OptionExtrasFeature {

    @Steps
    public BookingUiSteps user;

    @Managed
    WebDriver driver;

    @Test
    public void CanAddCancellationCoverAsOptionalExtra() {
        user.isOnTheOptionalExtrasPage();

        user.addsOptionalCancellationCover();

        user.canSeeCostOfCancellationCover();
    }

    @Test
    public void CanAddACotAsOptionalExtra() {
        user.isOnTheOptionalExtrasPage();

        user.addsOptionalCot(1);

        user.canSeeCostOfCots(1);
    }

    @Test
    public void CanAddMultipleCotsAsOptionalExtras() {
        user.isOnTheOptionalExtrasPage();

        user.addsOptionalCot(2);

        user.canSeeCostOfCots(2);
    }

    @Test
    public void CanAddAHighChairAsOptionalExtra() {
        user.isOnTheOptionalExtrasPage();

        user.addsOptionalHighChair(1);

        user.canSeeCostOfHighChairs(1);
    }

    @Test
    public void CanAddPriorityArrivalAsOptionalExtra() {
        user.isOnTheOptionalExtrasPage();

        user.addsOptionalPriorityArrival();

        user.canSeeCostOfPriorityArrival();
    }


}
