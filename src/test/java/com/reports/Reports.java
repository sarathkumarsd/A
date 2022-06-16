package com.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseClass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reports extends BaseClass {

	public static void generateJVMReport(String json) throws FileNotFoundException, IOException {

		File file = new File(getPropertyValue("Reports"));
		Configuration config = new Configuration(file, "OMRBranchAPIAutomation");
		config.addClassifications("Platform", "windows-11");
		config.addClassifications("version", "100.02.1.50");
		List<String> list = new ArrayList<String>();
		list.add(json);
		ReportBuilder builder = new ReportBuilder(list, config);
		builder.generateReports();
	}

}
