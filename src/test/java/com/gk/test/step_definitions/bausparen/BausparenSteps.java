package com.gk.test.step_definitions.bausparen;

import com.gk.test.framework.helpers.UrlBuilder;
import com.gk.test.page_objects.gui.BausperanPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class BausparenSteps {

    private final BausperanPage bausperanPage;

    public BausparenSteps(BausperanPage bausperanPage) {
        this.bausperanPage = bausperanPage;
    }

    @Given("^i navigate to the website Bausparen portal$")
    public void i_navigate_to_the_website_page() {
        UrlBuilder.startAtHomePage();
        bausperanPage.clickOnAcceptCookies();
    }

    @And("verify search box input field is present")
    public void verifySearchBoxInputFieldIsPresent() {
        bausperanPage.isSearchBoxPresent();
    }

    @And("I search {string} in search box and verify search results")
    public void iSearchInSearchBoxAndVerifySearchResults(String searchCriteria) {
        bausperanPage.sendKeysToSearchBox(searchCriteria);
        bausperanPage.pressEnter();
        bausperanPage.getSearchResultPageHeader(searchCriteria);
    }

    @And("verify below menu items are present")
    public void verifyBelowMenuItemsArePresent(DataTable dataTable) {
        bausperanPage.validateMenuItems(dataTable.asList());
    }

    @When("logo is present")
    public void logoIsPresent() {
        bausperanPage.verifyLogoIsDisplayed();
    }

    @When("hyperlink for gewintballen is present")
    public void hyperlinkForGewintballenIsPresent() {
        bausperanPage.verifyGewintballenHyperLinkPresent();
    }

    @And("i click on gewintballen hyperlink")
    public void iClickOnGewintballenHyperlink() {
        bausperanPage.clickOnGewintballen();
    }

    @Then("verify page is navigated with {string} header")
    public void verifyPageIsNavigatedWithHeader(String header) {
        bausperanPage.verifyHeaderOnNavigatedPage(header);
    }

    @When("i click slick arrow button and verify its action")
    public void iClickSlickArrowButtonAndVerifyItsAction() {
        bausperanPage.verifySlickArrowButtons();
    }

    @And("i click slick dot buttons and verify it actions")
    public void iClickSlickDotButtonsAndVerifyItActions() {
        bausperanPage.verifySlickDotButtons();
    }

    @Then("verify below products are available")
    public void verifyBelowProductsAreAvailable(DataTable dataTable) {
        bausperanPage.verifyUnsereProductsAvailable(dataTable.asList());
    }

    @When("video is present")
    public void videoIsPresent() {
        bausperanPage.verifyVideoIsAvailable();
    }

    @And("i click on play video")
    public void iClickOnPlayVideo() {
        bausperanPage.clickOnPlayButton();
    }

    @Then("verify video is playable")
    public void verifyVideoIsPlayable() {
        bausperanPage.verifyAttributeOfPlaying();
    }

    @Then("verify flyout should open on hovering over each of them")
    public void verifyFlyoutShouldOpenOnHoveringOverEachOfThem() {
        bausperanPage.verifyFlyout();
    }

    @When("I am able to expand Weitere infos")
    public void iAmAbleToExpandWeitereInfos() {
        bausperanPage.expandTheShutter();
    }

    @Then("verify that section is expanded")
    public void verifyThatSectionIsExpanded() {
        bausperanPage.assertSectionIsExpanded();
    }

    @And("I am able to collapse schliessen")
    public void iAmAbleToCollapseSchliessen() {
        bausperanPage.collapseTheShutter();
    }

    @Then("verify section is collapsed")
    public void verifySectionIsCollapsed() {
        bausperanPage.assertSectionIsCollapsed();
    }

    @When("download produktubfos is present")
    public void downloadProduktubfosIsPresent() {
        bausperanPage.assertDownloadButton();
    }

    @When("i enter invalid value {string} in the field")
    public void iEnterInvalidValueInTheField(String invalidValue) {
        bausperanPage.sendKeysToEmail(invalidValue);
    }

    @And("verify error message {string}")
    public void verifyErrorMessage(String expectedError) {
        bausperanPage.assertErrorMessage(expectedError);
    }

    @And("i click on Absenden")
    public void iClickOnAbsenden() {
        bausperanPage.clickOnAbsenden();
    }

    @Then("verify email sent success message as {string}")
    public void verifyEmailSentSuccessMessageAs(String expectedMessage) {
        bausperanPage.assertSuccessMessage(expectedMessage);
    }

    @When("I enter valid email {string}")
    public void iEnterValidEmail(String emailid) {
        bausperanPage.sendKeysToEmail(emailid);
    }
}
