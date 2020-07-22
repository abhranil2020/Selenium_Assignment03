package com.testcase;
import org.testng.annotations.Test;
import com.testingbase.Testbase;


public class SFDCloginecryptdpasswd extends Testbase {
	
	
	@Test
	public void verifySFDClogin() throws Exception  {	
		
		test=extent.createTest("Veryfying SFDC Login with Encypy password");
	sfdcorpg.verifyusernamepasswordtextbox();
	sfdcorpg.SFDCloginusername(prop.getProperty("Username"));
	sfdcorpg.SFDCloginpassword(prop.getProperty("Password"));
	sfdcorpg.Clicklogin();		
	sfdcorpg.verifyingHometab();
	sfdcorpg.verifyingObjectManagertab();
	}	
}