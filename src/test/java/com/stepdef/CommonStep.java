package com.stepdef;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.pojo.CommonVariable;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CommonStep extends BaseClass {
	public static Response response;

	@When("User should verify Status code with {int}")
	public void userShouldVerifyStatusCodeWith(int int1) {
		response = LoginStep.CommonVariable.getResponse();
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, int1);
	}

	@When("User should verify response code with {int}")
	public void userShouldVerifyResponseCodeWith(Integer int1) {
	}

}
