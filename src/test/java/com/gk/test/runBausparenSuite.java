package com.gk.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/classes", tags = {"@bausparen"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runwebat",
        "json:target/cucumber-report/runwebat/cucumber.json"},
        glue = "com.gk.test")
public class runBausparenSuite extends AbstractTestNGCucumberTests {
}
