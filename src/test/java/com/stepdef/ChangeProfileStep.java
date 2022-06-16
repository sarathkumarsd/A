package com.stepdef;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.ChangeProfilePic_Output_pojo;
import com.pojo.CommonVariable;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ChangeProfileStep extends BaseClass {
	public static CommonVariable commonVariable;

	@Given("user add headers")
	public void userAddHeaders() {
		String logtoken = LoginStep.CommonVariable.getLogtoken();
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "multipart/form-data");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);
		addHeaders(headers);

	}

	@When("user select form data")
	public void userSelectFormData() {

		formData();
	}

	@Then("user send {string} request for updateProfilePic")
	public void userSendRequestForUpdateProfilePic(String string) {

		Response response = reqType(string, Endpoints.PROFILEPICUPDATE);
		commonVariable.setResponse(response);
		Response response2 = LoginStep.CommonVariable.getResponse();
		int statusCode2 = response2.getStatusCode();
		System.out.println(statusCode2);
		Assert.assertEquals(statusCode2, 200);

	}

	@Then("User verifies response Profilepicbody message with {string}")
	public void userVerifiesResponseProfilepicbodyMessageWith(String string) {
		ChangeProfilePic_Output_pojo changeProfilePic_Output_pojo = new ChangeProfilePic_Output_pojo();
		String message = changeProfilePic_Output_pojo.getMessage();
		System.out.println(message);

	}

}
