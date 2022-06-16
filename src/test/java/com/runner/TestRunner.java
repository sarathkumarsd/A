package com.runner;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.reports.Reports;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","json:C:\\Users\\sarat\\eclipse-workspace\\OMRBranchAPIAutomation\\target\\omrbranch.json"},features = {
		"src\\test\\resources\\Features" }, glue = "com.stepdef", monochrome = true, dryRun = false, snippets = SnippetType.CAMELCASE)
public class TestRunner extends BaseClass {
	@AfterClass
	public static void afterClass() throws FileNotFoundException, IOException {
		Reports.generateJVMReport(getPropertyValue("ReportsLocation"));
	}
}
