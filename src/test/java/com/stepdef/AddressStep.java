package com.stepdef;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_input_pojo;
import com.pojo.AddAddress_output_pojo;
import com.pojo.CommonVariable;
import com.pojo.DeleteAddress_input_pojo;
import com.pojo.DeleteAddress_output_pojo;
import com.pojo.GetAllAddress_output_pojo;
import com.pojo.PojoClass;
import com.pojo.UpdateAddress_input_pojo;
import com.pojo.UpdateAddress_output_pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import groovy.util.logging.Log;
import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.locals_return;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class AddressStep extends BaseClass {
	public static CommonVariable commonVariable;
	public static int address_id;

	@Given("User add Headers and basic auth")
	public void userAddHeadersAndBasicAuth() {
		String logtoken = LoginStep.CommonVariable.getLogtoken();
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);
		addHeaders(headers);
	}

	@Then("User send request body with his Firstname {string},lastname {string} ,phone number {string},Apartment name {string}, State {string},city {string},Country {string},Zipcode{string},address{string},AddressType {string}")
	public void userSendRequestBodyWithHisFirstnameLastnamePhoneNumberApartmentNameStateCityCountryZipcodeAddressAddressType(
			String string, String string2, String string3, String string4, String string5, String string6,
			String string7, String string8, String string9, String string10) {

		AddAddress_input_pojo input = new AddAddress_input_pojo(string, string2, string3, string4,
				Integer.parseInt(string5), Integer.parseInt(string6), Integer.parseInt(string7), string8, string9,
				string10);
		addBody(input);
	}

	@When("User send {string} request for Addaddress Endpoint")
	public void userSendRequestForAddaddressEndpoint(String string) {
		Response response = reqType(string, Endpoints.ADDADDRESS);
		commonVariable = new CommonVariable();
		commonVariable.setResponse(response);
		int statusCode = getStatusCode(response);

	}

	@When("User should verify addaddress response body message  with  {string}")
	public void userShouldVerifyAddaddressResponseBodyMessageWith(String string) {
		AddAddress_output_pojo addAddress_output_pojo = response.as(AddAddress_output_pojo.class);
		String message = addAddress_output_pojo.getMessage();
		address_id = addAddress_output_pojo.getAddress_id();
		System.out.println(address_id);
		LoginStep.CommonVariable.setAddress_id(address_id);
		System.out.println(message);

	}

	@Then("User send request body with his id  {int}  Firstname {string},lastname {string} ,phone number {string},Apartment name {string}, State {string},city {string},Country {string},Zipcode{string},address{string},AddressType {string}")
	public void userSendRequestBodyWithHisIdFirstnameLastnamePhoneNumberApartmentNameStateCityCountryZipcodeAddressAddressType(
			Integer int1, String string, String string2, String string3, String string4, String string5, String string6,
			String string7, String string8, String string9, String string10) {

		UpdateAddress_input_pojo updateAddress_input_pojo = new UpdateAddress_input_pojo(String.valueOf(address_id),
				string, string2, string3, string4, Integer.parseInt(string5), Integer.parseInt(string6),
				Integer.parseInt(string7), string8, string9, string10);
		addBody(updateAddress_input_pojo);
	}

	@When("User send {string} request for UpdateAddress Endpoint")
	public void userSendRequestForUpdateAddressEndpoint(String string) {
		Response response = reqType(string, Endpoints.UPDATEADDRESS);

	}

//-------------------------------------------------------------
	@When("User should verify updateAddress response body message with {string}")
	public void userShouldVerifyUpdateAddressResponseBodyMessageWith(String string) {
		Response response1 = CommonVariable.getResponse();
		LoginStep.CommonVariable.setResponse(response1);
		int statusCode = getStatusCode(response1);
		System.out.println(statusCode);
		UpdateAddress_output_pojo updateAddress_output_pojo = response.as(UpdateAddress_output_pojo.class);
		String message = updateAddress_output_pojo.getMessage();
		System.out.println(message);
		org.junit.Assert.assertEquals(message, string);
	}

	@When("User send {string} request for GETAddress Endpoint")
	public void userSendRequestForGETAddressEndpoint(String string) {
		Response response1 = reqType(string, Endpoints.GETALLADDRESSES);
		int statusCode = response1.getStatusCode();
		System.out.println(statusCode);

	}

	@When("User should verify response body message and status code with {string} and {int}")
	public void userShouldVerifyResponseBodyMessageAndStatusCodeWithAnd(String string, Integer int1) {
		GetAllAddress_output_pojo getAllAddress_output_pojo = response.as(GetAllAddress_output_pojo.class);
		String message = getAllAddress_output_pojo.getMessage();
		System.out.println(message);
		org.junit.Assert.assertEquals(message, string);

	}

	@When("User send {string} request body with Address Id")
	public void userSendRequestBodyWithAddressId(String string) {
		DeleteAddress_input_pojo deleteAddress_input_pojo = new DeleteAddress_input_pojo(String.valueOf(address_id));
		addBody(deleteAddress_input_pojo);

	}

	@When("User should verify Delete address response body message with {string}")
	public void userShouldVerifyDeleteAddressResponseBodyMessageWith(String string) {
		Response response = reqType("DELETE", Endpoints.DELETEADDRESS);
		commonVariable.setResponse(response);
		Response response2 = LoginStep.CommonVariable.getResponse();
		int statusCode2 = response2.getStatusCode();
		System.out.println(statusCode2);
		Assert.assertEquals(statusCode2, 200);
		DeleteAddress_output_pojo deleteAddress_output_pojo = response.as(DeleteAddress_output_pojo.class);
		String message = deleteAddress_output_pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Address deleted successfully");

	}

}
