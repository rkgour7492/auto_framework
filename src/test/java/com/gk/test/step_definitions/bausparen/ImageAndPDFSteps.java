package com.gk.test.step_definitions.bausparen;

import com.gk.test.framework.helpers.ApiHelper;
import com.gk.test.framework.helpers.utils.Base64Decoder;
import com.gk.test.page_objects.gui.BausperanPage;
import com.gk.test.services.Api;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageAndPDFSteps extends ApiHelper {
    private Response response;
    private final BausperanPage bausperanPage;
    public ImageAndPDFSteps(BausperanPage bausperanPage){
        this.bausperanPage=bausperanPage;
    }
    @And("perform GET request on image")
    public void perform_GET_request_on_image(){
        String code=bausperanPage.getImageBase64Code();
        String url=Base64Decoder.decodeBase64ImageToUrl(code);
        response=Api.getList(url);
    }

    @Then("verify image is not broken with {int} status code")
    public void verifyImageIsNotBrokenWithStatusCode(int expectedStatusCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }

    @And("I perform GET request for {string}")
    public void iPerformGETRequestFor(String imageName) {
        response=Api.getList(bausperanPage.getSRC(imageName));
    }

    @And("verify PDF file is available for download with response {int}")
    public void verifyPDFFileIsAvailableForDownloadWithResponse(int expectedStatusCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }
}
