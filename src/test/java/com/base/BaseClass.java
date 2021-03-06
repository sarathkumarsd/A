package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	RequestSpecification rs;
	protected Response response;

	public void addHeader(String key, String value) {
		rs = RestAssured.given().header(key, value);
	}

	public void queryParam(String key, String value) {
		rs = rs.queryParam(key, value);
	}

	public void pathParam(String key, String value) {
		rs = rs.pathParam(key, value);
	}

	public void basicAuth(String username, String password) {
		rs = rs.auth().preemptive().basic(username, password);
	}

	public void addBody(String body) {
		rs = rs.body(body);
	}

	public void addBody(Object body) {
		rs = rs.body(body);
	}

	public Response reqType(String Reqtype, String endpoint) {
		switch (Reqtype) {
		case "GET":
			response = rs.get(endpoint);
			break;
		case "PUT":
			response = rs.put(endpoint);
			break;
		case "POST":
			response = rs.post(endpoint);
			break;
		case "DELETE":
			response = rs.delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	public int getStatusCode(Response response) {
		int statusCode = response.statusCode();
		return statusCode;

	}

	public ResponseBody getBody(Response response) {
		ResponseBody body = response.getBody();
		return body;

	}

	public ResponseBody getBody(Object body) {
		ResponseBody body2 = response.getBody();
		return body2;

	}

	public void addHeaders(Headers header) {
		rs = RestAssured.given().headers(header);
	}

	public String getResponseBody(Response response) {
		String asString = getBody(response).asString();
		return asString;

	}

	public String getasPrettyString(Response response) {
		String asPrettyString = getBody(response).asPrettyString();
		return asPrettyString;

	}

	public static String getPropertyValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(
				new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Config.properties"));
		Object object = properties.get(key);
		String value = (String) object;
		return value;
	}

	public void formData() {
		rs = rs.multiPart("profile_picture", new File("C:\\Users\\sarat\\Downloads\\img.jpg"));

	}

}
