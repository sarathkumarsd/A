package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_input_pojo;
import com.pojo.AddAddress_output_pojo;
import com.pojo.DeleteAddress_input_pojo;
import com.pojo.DeleteAddress_output_pojo;
import com.pojo.GetAllAddress_output_pojo;
import com.pojo.PojoClass;
import com.pojo.UpdateAddress_input_pojo;
import com.pojo.UpdateAddress_output_pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class OMRBranchClub extends BaseClass {
	String logtoken;
	int address_id;

	@Test(priority = 1)
	private void login() throws FileNotFoundException, IOException {
		addHeader("Content-Type", "application/json");
		basicAuth(getPropertyValue("username"), getPropertyValue("password"));
		Response response = reqType("POST", Endpoints.LOGIN);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		PojoClass as = response.as(PojoClass.class);
		String message = as.getMessage();
		System.out.println(message);

		logtoken = as.getData().getLogtoken();
		System.out.println(logtoken);
		String first_name = as.getData().getFirst_name();
		System.out.println(first_name);
		Assert.assertEquals(first_name, "SARATH");
	}

	@Test(priority = 2)
	private void addUserAddress() {

		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);
		addHeaders(headers);
		AddAddress_input_pojo input = new AddAddress_input_pojo("Raj", "Khundra", "1234567898", "apartment", 33, 3378,
				101, "202020", "64/63 partap nagar", "home");
		addBody(input);
		Response response = reqType("POST", "https://omrbranch.com/api/addUserAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		AddAddress_output_pojo addAddress_output_pojo = response.as(AddAddress_output_pojo.class);
		String message = addAddress_output_pojo.getMessage();
		address_id = addAddress_output_pojo.getAddress_id();
		System.out.println(address_id);

		System.out.println(message);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(message, "Address added successfully");

	}

	@Test(priority = 3)
	private void updateUserAddress() {

		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);
		addHeaders(headers);

		UpdateAddress_input_pojo input = new UpdateAddress_input_pojo(String.valueOf(address_id), "Sarath", "Khundra",
				"1234567898", "apartment", 33, 3378, 101, "202020", "64/63 partap nagar", "home");
		addBody(input);

		Response response = reqType("PUT", Endpoints.UPDATEADDRESS);
		UpdateAddress_output_pojo updateAddress_output_pojo = response.as(UpdateAddress_output_pojo.class);
		int status = updateAddress_output_pojo.getStatus();
		System.out.println(status);

		String message = updateAddress_output_pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(status, 200);
		Assert.assertEquals(message, "Address updated successfully");

	}

	@Test(priority = 4)
	private void getAllAddresses() {
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);
		addHeaders(headers);

		Response response2 = reqType("GET", Endpoints.GETALLADDRESSES);
		int statusCode2 = response.getStatusCode();
		System.out.println(statusCode2);
		Assert.assertEquals(statusCode2, 200);

		GetAllAddress_output_pojo getAllAddress_output_pojo = response.as(GetAllAddress_output_pojo.class);
		String message = getAllAddress_output_pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "OK");

	}

	@Test(priority = 5)

	private void deleteAddress() {
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);

		Headers headers = new Headers(h);
		addHeaders(headers);
		DeleteAddress_input_pojo deleteAddress_input_pojo = new DeleteAddress_input_pojo(String.valueOf(address_id));
		addBody(deleteAddress_input_pojo);
		Response response = reqType("DELETE", Endpoints.DELETEADDRESS);
		int statusCode2 = response.getStatusCode();
		System.out.println(statusCode2);
		Assert.assertEquals(statusCode2, 200);
		DeleteAddress_output_pojo deleteAddress_output_pojo = response.as(DeleteAddress_output_pojo.class);
		String message = deleteAddress_output_pojo.getMessage();
		Assert.assertEquals(message, "Address deleted successfully");

	}

}
