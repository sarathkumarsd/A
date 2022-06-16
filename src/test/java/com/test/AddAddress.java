package com.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_input_pojo;
import com.pojo.AddAddress_output_pojo;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class AddAddress extends BaseClass {
	@Test
	private void addUserAddress() {

		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization",
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImI1ZDJmYzdjOGZhNDNmN2NlYmUwODgwYTNiNDIxMWE1ODYwYzQxN2Q5ZmU1NmNlZTQyYTI5ZTgxOTc5YjhhNmJjNTNlYmZlYjJjNDQyMjM4In0");
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		AddAddress_input_pojo addAddress_input_pojo = new AddAddress_input_pojo("sarath", "kumar", "21332432", "casa", 5, 6, 5, null, "chennai", "home");
		Response response2 = reqType("POST",Endpoints.ADDADDRESS);
		int statusCode = response2.getStatusCode();
		System.out.println(statusCode);
		AddAddress_output_pojo as = response2.as(AddAddress_output_pojo.class);
		String message = as.getMessage();
		System.out.println(message);
		

	}

}
