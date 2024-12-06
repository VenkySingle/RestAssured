package steps;

import java.io.File;

import org.hamcrest.Matchers;

import Utilities.DynamicDataGeneration;
import Utilities.WritingtoJSON;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;

public class accounts {
	
	public static String AccountNum;
	@Given("the Request body is provided using for accounts {string}")
	public void the_request_body_is_provided_using_for_accounts(String filename) {
		
		  try
		  {
			  String jsonPath = "cif";
			  String newValue = customer.CustomerID;
			WritingtoJSON.updateJsonValue(filename, jsonPath, newValue);
			  File file = new File("./Payload/"+filename);
			  steps.customer.req = RestAssured.given().headers(steps.customer.head).contentType("application/json").when().body(file);

		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }

	}

	@When("the Request is sent for account creation")
	public void the_request_is_sent_for_account_creation() {
		  try
		  {
			  customer.res = customer.req.post("cfl/accounts/");

		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	
	}

	@Then("the Status Code should be asserted for accounts creation")
	public void the_status_code_should_be_asserted_for_accounts_creation() {
		
		int status = customer.res.getStatusCode();
		String statusLine = customer.res.getStatusLine();
		System.out.println(status);
		System.out.println(statusLine);
		//res.prettyPrint();
		if(status!=201)
		{
			System.out.println(customer.res.jsonPath().getString("validation_errors[0].field_error"));
		}
		customer.res.then().assertThat().statusCode(201);
		AccountNum = customer.res.jsonPath().getString("response_body.account_number");
		System.out.println("Accountnum is" + " "+  AccountNum);
		customer.res.then().assertThat().body("response_status", Matchers.startsWith("success"));
	
	}


}
