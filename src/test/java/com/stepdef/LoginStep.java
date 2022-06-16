package com.stepdef;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.CommonVariable;
import com.pojo.PojoClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class LoginStep extends BaseClass {
	public static CommonVariable CommonVariable;
	public static Response response;

	@Given("User add Headers and basicAuth for login")
	public void userAddHeadersAndBasicAuthForLogin() throws FileNotFoundException, IOException {
		addHeader("Content-Type", "application/json");
		basicAuth(getPropertyValue("username"), getPropertyValue("password"));

	}

	@When("User send {string} request for Login Endpoint")
	public void userSendRequestForLoginEndpoint(String string) {
		response = reqType(string, Endpoints.LOGIN);
		CommonVariable = new CommonVariable();
		CommonVariable.setResponse(response);
	}


	@When("User should verify response body message with {string} and save logtoken")
	public void userShouldVerifyResponseBodyMessageWithAndSaveLogtoken(String string) {

		PojoClass as = response.as(PojoClass.class);
		String message = as.getMessage();
		System.out.println(message);
		String logtoken = as.getData().getLogtoken();
		System.out.println(logtoken);
		CommonVariable = new CommonVariable();
		CommonVariable.setLogtoken(logtoken);

	}

}
