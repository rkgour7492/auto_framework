package com.gk.test.page_objects.gui;

import com.gk.test.framework.PageObject;
import com.gk.test.framework.helpers.BausparenConstants;
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

    private By bausparenHeaderText = By.xpath("//h1[text()='Bausparen']");
    private By downloadPDFButton = By.xpath("//*[text()='Download Produktinfos']/parent::a");
    private By slickArrowPrev = By.xpath("//button[contains(@class,'prev')]");
    private By slickArrowNext = By.xpath("//button[contains(@class,'next')]");
    private By slickButton = By.xpath("//li[not(contains(@class,'slick'))]//button[contains(@id,'slick')]");
    private By video = By.xpath("//video[@class]/parent::div[@id]");
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

    public void isSearchBoxPresent() throws InterruptedException {
        scrollToTop(searchBox);
        Assert.assertTrue(verifyIfElementPresent(searchBox), "Search Box is not present");
    }

    public void getSearchResultPageHeader(String text) {
        Assert.assertTrue(verifyIfElementPresent(By.xpath("//h3[contains(text(),'" + text + "')]")), "Search is unsuccessful");
    }

    public void validateMenuItems(List<String> expectedList) {
        Assert.assertEquals(getTextListFromWebelementsList(hoverOverDropdownMenuItems), expectedList);
    }

    public String getSRC(String imageName) {
        switch (imageName) {
            case BausparenConstants.LOGO_RAIFFEISEN_BAUSPARKASSE:
                return element(logoImage).getAttribute(BausparenConstants.ATTRIBUTE_SRC);
            case BausparenConstants.DOWNLOAD_PRODUKTINFOS:
                return element(downloadPDFButton).getAttribute(BausparenConstants.ATTRIBUTE_HREF);
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
        Assert.assertTrue(waitForExpectedElement(video).getAttribute(BausparenConstants.ATTRIBUTE_CLASS).contains(BausparenConstants.ATTRIBUTE_CLASS_VALUE_PLAYING));
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
