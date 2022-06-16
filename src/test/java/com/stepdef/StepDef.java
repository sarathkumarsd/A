package com.stepdef;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_input_pojo;
import com.pojo.AddAddress_output_pojo;
import com.pojo.PojoClass;

import java.util.List;

import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class StepDef extends BaseClass {}
