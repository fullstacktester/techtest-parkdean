package io.github.fullstacktester.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.awaitility.Durations;
import org.openqa.selenium.By;

import java.util.List;

import static org.awaitility.Awaitility.await;

public class OptionalExtrasPage extends PageObject {


    @FindBy(css = ".holiday-summary dd:nth-child(4) ")
    WebElementFacade holidayPark;

    @FindBy(css = ".holiday-summary  dd:nth-child(6)")
    WebElementFacade nightsAndDateOfArrival;

    @FindBy(css = ".holiday-summary  dd:nth-child(8) > div")
    List<WebElementFacade> lineItems;

    @FindBy(css = ".holiday-summary  p.price")
    WebElementFacade totalCost;

    @FindBy(css = ".continue-btn-container > button")
    WebElementFacade continueButton;

    @FindBy(css = "form > div:nth-child(3) article:nth-child(1) .btn")
    WebElementFacade cancellationCover;

    @FindBy(css = "form > div:nth-child(4) article:nth-child(1) > span")
    WebElementFacade costPerCot;

    @FindBy(css = "form > div:nth-child(4) article:nth-child(1) select")
    WebElementFacade cot;

    @FindBy(css = "form > div:nth-child(4) article:nth-child(2) > span")
    WebElementFacade costPerHighChair;

    @FindBy(css = "form > div:nth-child(4) article:nth-child(2) select")
    WebElementFacade highChair;

    @FindBy(css = ".extras-items > article:nth-child(3) > span")
    WebElementFacade costPerPet;

    @FindBy(css = ".extras-items > article:nth-child(3) select")
    WebElementFacade pet;

    @FindBy(css = ".extras-items > article:nth-child(3) .btn")
    WebElementFacade priorityArrival;

    public void waitForLoad() {
        await()
                .pollDelay(Durations.FIVE_SECONDS)
                .atMost(Durations.TEN_SECONDS)
                .until(continueButton::isClickable);
    }

    public String getHolidayPark() {
        return holidayPark.getText();
    }

    public String getNights() {
        String combined = nightsAndDateOfArrival.getText();

        // Get the text up until the slash character, removing the trailing space.
        return combined.substring(0, combined.indexOf('/') - 1);
    }

    public String getDateOfArrival() {
        String combined = nightsAndDateOfArrival.getText();

        // Get the text after the slash character, removing the leading space.
        return combined.substring(combined.indexOf('/') +2);
    }

    public void addCots(int numberOfCots) {
        cot.select().byVisibleText(String.valueOf(numberOfCots));
    }

    public void addHighChairs(int numberOfHighChairs) {
        highChair.select().byVisibleText(String.valueOf(numberOfHighChairs));

    }

    public void selectPriorityArrival() {
        priorityArrival.click();
    }

    public int getTotalCostInPence() {
        String basePrice = totalCost.getText()
                .replace("£", "")
                .replace(".", ""); // remove decimal point to get cost in pence

        return Integer.parseInt(basePrice);
    }

    public int getCostOfCancellationCover() {
        String text = cancellationCover.getText();

        return Integer.parseInt(text.substring(text.indexOf("£")).replace(".", ""));
    }

    public void selectCancellationCover() {
        cancellationCover.click();
    }

    public int getCancellationCoverSummaryPrice() {
        return getSummaryPrice("Cancellation Cover");
    }

    public int getCotSummaryPrice() {
        return getSummaryPrice("Cot");
    }

    public int getHighChairSummaryPrice() {
        return getSummaryPrice("High Chair");
    }

    public int getPetSummaryPrice() {
        return getSummaryPrice("Pet");
    }

    public int getPriorityArrivalSummaryPrice() {
        return getSummaryPrice("Priority Arrival");
    }

    public int getSummaryPrice(String lineItem) {
        WebElementFacade line = lineItems
                .stream()
                .filter(x -> x.getAttribute("outerHTML").contains(lineItem))
                .findFirst()
                .orElse(null);

        if (line == null) return 0;

        String price = line.find(By.cssSelector("div > div:nth-child(2)")).getText();
        price = price.replace("£", "").replace(".", "");

        return Integer.parseInt(price);
    }
}