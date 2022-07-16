package com.gk.test.page_objects.gui;

import com.gk.test.framework.PageObject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
public class BausperanPage extends PageObject {
    private By acceptCookies = By.id("onetrust-accept-btn-handler");
    private By searchBox = By.id("search--input-header");
    private By hoverOverDropdownMenuItems = By.xpath("//a[@class='dropdown-toggle']");
    private By flyout = By.xpath("//a[@class='dropdown-toggle']/following-sibling::div[contains(@class,'open')]");
    private By bausparenHoverOverMenu = By.xpath("//a[@class and contains(text(),'Bausparen')]");
    private By finanzierenHoverOverMenu = By.xpath("//a[@class and contains(text(),'Finanzieren')]");
    private By rechnerenHoverOverMenu = By.xpath("//a[@class and contains(text(),'Rechner')]");
    private By servicesHoverOverMenu = By.xpath("//a[@class and contains(text(),'Services')]");
    private By meineWohnweltHoverOverMenu = By.xpath("//a[@class and contains(text(),'Meine Wohnwelt')]");
    private By kontaktHoverOverMenu = By.xpath("//a[@class and contains(text(),'Kontakt')]");
    private By pageImage1 = By.xpath("//img[@alt='Modernes Wohnzimmer']");
    private By bausparenHeaderText = By.xpath("//h1[text()='Bausparen']");
    private By downloadPDFButton = By.xpath("//*[text()='Download Produktinfos']/parent::a");
    private By slickArrowPrev = By.xpath("//button[contains(@class,'prev')]");
    private By slickArrowNext = By.xpath("//button[contains(@class,'next')]");
    private By slickButton = By.xpath("//li[not(contains(@class,'slick'))]//button[contains(@id,'slick')]");
    private By video = By.tagName("video");
    private By playVideoButton = By.xpath("//button[@title='Play Video']");
    private By contentExpander = By.xpath("//span[contains(text(),'Einlagensicherung')]//parent::a");
    private By contentWrapper = By.xpath("//span[contains(text(),'Schliessen')]//parent::a");
    private By content = By.xpath("//a[@title='raiffeisen-einlagensicherung.at']");
    private By emailTextBox = By.name("emailAddress");
    private By emailAbsendenButton = By.xpath("//span[contains(text(),'Absenden')]//parent::a");
    private By locationSearchBox = By.id("location-search-form");
    private By logoImage = By.xpath("//img[@alt='Logo Raiffeisen Bausparkasse']");
    private By hyperlinkGewinntabellen = By.xpath("//a[@class and contains(@href,'gewinntabellen')]");
    private By draggableSlickOptions = By.xpath("//div[contains(@class,'slick-active') and @role]//h3");
    private By errorMessage = By.xpath("//ul[contains(@class,'parsley-errors')]//li");
    private By successMessage = By.xpath("//div[contains(@class,'newsletter-subscription-success')]//p");

    //    private By allProductOptions = By.xpath("//div[(contains(@class,'slick-active') or @class='slick-slide') and @role]//h3");
   /* private By getProductHyperlink(String... productName) {
        if (productName.length <= 0) {
            productName[0] = "";
        }
        return By.xpath("//div[contains(@class,'slick-active') and @role]//a[contains(@class,'btn') and contains(@href,'" + productName[0] + "+']");
    }*/

    private By getHoverOverDropdownMenu(String menuName) {
        return By.xpath("//a[@class and contains(text(),'" + menuName + "')]");
    }

    public void clickOnAcceptCookies() {
        if (verifyIfElementPresent(acceptCookies)) {
            element(acceptCookies).click();
        }

    }

    public void sendKeysToSearchBox(String textToInput) {
        waitForExpectedElement(searchBox).sendKeys(textToInput);
    }

    public void pressEnter() {
        waitForExpectedElement(searchBox).sendKeys(Keys.ENTER);
    }

    public void isSearchBoxPresent() {
        Assert.assertTrue(verifyIfElementPresent(searchBox), "Search Box is not present");
    }

    public void getSearchResultPageHeader(String text) {
        Assert.assertTrue(verifyIfElementPresent(By.xpath("//h3[contains(text(),'" + text + "')]")), "Search is unsuccessful");
    }

    public void validateMenuItems(List<String> expectedList) {
        Assert.assertEquals(getTextListFromWebelementsList(hoverOverDropdownMenuItems), expectedList);
    }

    public String getImageBase64Code() {
        return element(pageImage1).getAttribute("src").split(",")[1];
    }

    public String getSRC(String imageName) {
        switch (imageName) {
            case "Logo Raiffeisen Bausparkasse":
                return element(logoImage).getAttribute("src");
            case "Download Produktinfos":
                return element(downloadPDFButton).getAttribute("href");

        }
        return null;
    }

    public void verifyLogoIsDisplayed() {
        Assert.assertTrue(verifyIfElementPresent(logoImage), "Logo is not present");
    }

    public void verifyHeaderOnNavigatedPage(String headerName) {
        Assert.assertTrue(verifyIfElementPresent(By.xpath("//h1[contains(text(),'" + headerName + "')]")), "Header not present, page not navigated");
    }

    public void verifyGewintballenHyperLinkPresent() {
        Assert.assertTrue(verifyIfElementPresent(hyperlinkGewinntabellen), "Gewintballen hyperlink not present");
    }

    public void clickOnGewintballen() {
        waitForExpectedElement(hyperlinkGewinntabellen).click();
    }

    public void verifySlickArrowButtons() {
        List<String> optionsInitially = getTextListFromWebelementsList(draggableSlickOptions);
        waitForExpectedElement(slickArrowNext).click();
        List<String> optionsAfterNextClick = getTextListFromWebelementsList(draggableSlickOptions);
        waitForExpectedElement(slickArrowPrev).click();
        List<String> optionsAfterPrevClick = getTextListFromWebelementsList(draggableSlickOptions);
        assertSlickButtons(optionsInitially, optionsAfterNextClick, optionsAfterPrevClick);
    }

    public void verifySlickDotButtons() {
        List<String> optionsInitially = getTextListFromWebelementsList(draggableSlickOptions);
        waitForExpectedElement(slickButton).click();
        List<String> optionsAfterNextClick = getTextListFromWebelementsList(draggableSlickOptions);
        waitForExpectedElement(slickButton).click();
        List<String> optionsAfterPrevClick = getTextListFromWebelementsList(draggableSlickOptions);
        assertSlickButtons(optionsInitially, optionsAfterNextClick, optionsAfterPrevClick);
    }

    private void assertSlickButtons(List<String> initialOptions, List<String> afterNext, List<String> afterPrev) {
        Assert.assertEquals(initialOptions, afterPrev);
        Assert.assertNotEquals(initialOptions, afterNext);
        Assert.assertNotEquals(afterPrev, afterNext);
    }

    private List<String> getTextListFromWebelementsList(By by) {
        List<String> list = new ArrayList<>();
        visibilityOfAllElementsLocatedBy(by).forEach(element -> list.add(element.getText()));
        return list;
    }

    public void verifyUnsereProductsAvailable(List<String> expected) {
        List<String> list1 = getTextListFromWebelementsList(draggableSlickOptions);
        waitForExpectedElement(slickButton).click();
        List<String> list2 = getTextListFromWebelementsList(draggableSlickOptions);
        list1.addAll(list2);
        Set<String> uniqueProducts = new HashSet<>(list1);
        List<String> actual = new ArrayList<>(uniqueProducts);
        Assert.assertTrue(actual.size() == expected.size() && actual.containsAll(expected) && expected.containsAll(actual));

    }

    public void verifyVideoIsAvailable() {
        Assert.assertTrue(verifyIfElementPresent(video));
    }

    public void clickOnPlayButton() {
        waitForExpectedElement(playVideoButton).click();
    }

    public void verifyAttributeOfPlaying() {
        Assert.assertTrue(waitForExpectedElement(video).getAttribute("class").contains("vjs-playing"));
    }

    public void verifyFlyout() {
        for (WebElement element : visibilityOfAllElementsLocatedBy(hoverOverDropdownMenuItems)) {
            hoverOver(element);
            Assert.assertTrue(verifyIfElementPresent(flyout), "Flyout didnt open for " + element.getText());
        }
    }

    public void expandTheShutter() {
        waitForExpectedElement(contentExpander).click();
    }

    public void assertSectionIsExpanded() {
        Assert.assertTrue(verifyIfElementPresent(content));
    }

    public void collapseTheShutter() {
        waitForExpectedElement(contentWrapper).click();
    }

    public void assertSectionIsCollapsed() {
        Assert.assertFalse(verifyIfElementPresent(content));
    }

    public void assertDownloadButton() {
        Assert.assertTrue(verifyIfElementPresent(downloadPDFButton));
    }

    public void sendKeysToEmail(String value) {
        waitForExpectedElement(emailTextBox).sendKeys(value);
    }

    public void clickOnAbsenden() {
        waitForExpectedElement(emailAbsendenButton).click();
    }

    public void assertErrorMessage(String expectedError) {
        Assert.assertEquals(waitForExpectedElement(errorMessage).getText(), expectedError);
    }

    public void assertSuccessMessage(String expectedMessage) {
        Assert.assertEquals(waitForExpectedElement(successMessage).getText(), expectedMessage);
    }
}
