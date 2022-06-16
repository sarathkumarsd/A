package com.pojo;

import io.restassured.response.Response;

public class CommonVariable {
	public static Response response;
	public static String logtoken;
	public static int address_id;

	public static Response getResponse() {
		return response;
	}

	public static void setResponse(Response response) {
		CommonVariable.response = response;
	}

	public static String getLogtoken() {
		return logtoken;
	}

	public static void setLogtoken(String logtoken) {
		CommonVariable.logtoken = logtoken;
	}

	public static int getAddress_id() {
		return address_id;
	}

	public static void setAddress_id(int address_id) {
		CommonVariable.address_id = address_id;
	}

}
