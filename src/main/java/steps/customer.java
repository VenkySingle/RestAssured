package steps;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import Utilities.DynamicDataGeneration;
import Utilities.WritingtoJSON;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class customer {
	public static RequestSpecification req;
	public static Response res;
	public static Map<String,String> head ;
	public static String CustomerID;
	
	@Given("the Endpoint URL is {string}")
	public void the_endpoint_url_is(String url) {
		try
		{
			RestAssured.baseURI = url;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	  
	}

	@Given("Authentication is provided with username {string} and password {string}")
	public void authentication_is_provided_with_username_and_password(String username, String password) {
	
		try
		{
head= new HashMap<String, String>();
head.put("Authorization", "Basic bXZya3Rlc3Q6TXYjckBrdEUkc3QkMzBhWQ==");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Given("the Request body is provided using {string}")
	public void the_request_body_is_provided_using(String filename) {
	  try
	  {
		  String jsonPath = "customer.customer_profile.identifications[0].value";
		  String newValue = DynamicDataGeneration.FakeDataforID().toString();
		WritingtoJSON.updateJsonValue(filename, jsonPath, newValue);
		  File file = new File("./Payload/"+filename);
		  req = RestAssured.given().headers(head).contentType("application/json").when().body(file);

	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	}

	@When("the Request is sent")
	public void the_request_is_sent() {
		
		  try
		  {
			  res = req.post("integ/wdl/customers/");

		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	   
	}

	@Then("the Status Code should be asserted")
	public void the_status_code_should_be_asserted() {
	int status = res.getStatusCode();
	String statusLine = res.getStatusLine();
	System.out.println(status);
	System.out.println(statusLine);
	//res.prettyPrint();
	if(status!=201 || status!=200)
	{
		System.out.println(res.jsonPath().getString("error_details.errorDetails[0].message"));
	}
	res.then().assertThat().statusCode(201);
	CustomerID = res.jsonPath().getString("response_body.cif");
	System.out.println("Customer is" + " "+  CustomerID);
	res.then().assertThat().body("response_body.cif", Matchers.startsWith("143"));
	
	}
	
	@When("the Request is sent with customerid")
	public void the_request_is_sent_withgetCustomer() {
		
		  try
		  {
			 req = RestAssured.given().headers(head).when().body("");
			 res = req.get("/cfl/customers/"+CustomerID);

		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	   
	}
	@Then("the Status Code should be asserted with getCustomer")
	public void the_status_code_should_be_asserted_with_get() {
	int status = res.getStatusCode();
	String statusLine = res.getStatusLine();
	System.out.println(status);
	System.out.println(statusLine);
	//res.prettyPrint();
	if(status!=200)
	{
		System.out.println(res.jsonPath().getString("error_details.error_details.message"));
	}
	res.then().assertThat().statusCode(200);
	CustomerID = res.jsonPath().getString("response_body.customer_identity.cif");
	System.out.println("Customer is retrieved" + " "+  CustomerID);
	res.then().assertThat().body("response_body.customer_identity.cif", Matchers.startsWith("1"));
	
	}


}
