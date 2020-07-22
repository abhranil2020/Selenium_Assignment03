package com.objectrepo;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.WaitHelper;


public class SFDCLoginPage {
	
	private WebDriver driver;
				
	@FindBy(xpath = ".//*[@id='username']")	
	WebElement usernametextbox; // Enter Username text box	
	
	@FindBy(xpath = ".//*[@id='password']")
	public static WebElement passwordtextbox; // Enter password text box	
		
	@FindBy(xpath = ".//*[@id='Login']")	
	WebElement loginbutton	; // login button
		
	@FindBy(xpath = ".//*[text()='Setup Home']")	
	public WebElement SetupHomelink; // Setup Home
		
	@FindBy(xpath = ".//*[text()='Object Manager']")	
	public WebElement ObjectManagertab; // Object Manager
	
	
	WaitHelper waitHelper;
	
	public SFDCLoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}
	
	public void verifyusernamepasswordtextbox()	{		
		if(usernametextbox.isDisplayed() && passwordtextbox.isDisplayed()) {
			System.out.println("Username Password textbox button present in Sign-in page");
		}
		else {
			System.out.println("Error page");
		}				
	}			
	
	public void SFDCloginusername(String username) {		
		usernametextbox.clear();
		usernametextbox.sendKeys(username);	}		
	
	
public static void SFDCloginpassword(String password) {			
	byte[] decodedString = Base64.decodeBase64(password);
	String DcdPasswords=(new String(decodedString));
	passwordtextbox.clear();			
	passwordtextbox.sendKeys(DcdPasswords);	}	
	

	public void Clicklogin() {
		loginbutton.click();   
		waitHelper.WaitForElement(SetupHomelink, 30);
		waitHelper.WaitForElement(ObjectManagertab, 30);
	}
			
	
	public void verifyingHometab(){				
		if(SetupHomelink.isDisplayed()){					
				System.out.println("Setup Home link is Present in SFDC Lightning home page");  }
		else{
				System.out.println("Setup Home link is not Present in SFDC Lightning home page");	}			
			    }	

	public void verifyingObjectManagertab(){				
		if(ObjectManagertab.isDisplayed()){
				
				System.out.println("Object Manager tab is Present in SFDC Lightning home page");  }
		else{
				System.out.println("Object Manager tab is not Present in SFDC Lightning home page");	}		
					
			  }											
          }
