package Utilities;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

public class DynamicDataGeneration {


	public static String FakeDataforID()
	{
	 Faker fake  = new Faker();
	 String str = fake.idNumber().valid().toString();
	String str1 =  str.replace("-", "ABC");
	return str1;
	}
	
	

}
