package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.LoginContext;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.PojoClass;

import io.restassured.response.Response;

public class OMRBranchClub extends BaseClass {
	@Test
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

		String logtoken = as.getData().getLogtoken();
		System.out.println(logtoken);
		String first_name = as.getData().getFirst_name();
		System.out.println(first_name);
		Assert.assertEquals(first_name, "SARATH");
	}

}
