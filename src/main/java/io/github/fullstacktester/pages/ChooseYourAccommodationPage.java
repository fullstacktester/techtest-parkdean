package io.github.fullstacktester.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.awaitility.Durations;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static org.awaitility.Awaitility.await;

@DefaultUrl("https://www.parkdeanresorts.co.uk/")
public class ChooseYourAccommodationPage extends PageObject {


    @FindBy(css = ".result-item")
    List<WebElementFacade> results;

    public void waitForLoad(){
        await()
                .pollDelay(Duration.ofSeconds(5))
                .atMost(Durations.TEN_SECONDS)
                .until(results::size, size -> size >= 1);
    }

    public void bookNow(int bookingIndex) {
        results.get(bookingIndex).find(By.className("btn-primary")).click();
    }

    public String getAccommodationName(int bookingIndex) {
        return results.get(bookingIndex).find(By.cssSelector(".title-container > h2")).getText();
    }

    public int getBasePriceInPence(int bookingIndex) {
        String basePrice = results.get(bookingIndex).find(By.className("price-container")).getText().replace("£", "");

        return Integer.parseInt(basePrice) * 100; // Get price in pence
    }

}