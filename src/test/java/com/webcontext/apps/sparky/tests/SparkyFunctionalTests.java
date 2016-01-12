package com.webcontext.apps.sparky.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report" }, features = "src/test/resources/functional")
public class SparkyFunctionalTests {

}
