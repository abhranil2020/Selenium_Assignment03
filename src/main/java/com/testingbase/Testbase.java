package com.testingbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.objectrepo.SFDCLoginPage;
import com.testcase.SFDCloginecryptdpasswd;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Testbase {

	public static WebDriver driver;
	public static Properties prop;
	public static SFDCLoginPage sfdcorpg;	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	
@BeforeTest
public void setExtent() {
 // specify location of the report	
DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
Date date = new Date();
String time = dateFormat.format(date);
htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + "HtmlReport" + time + "/" + "myReport.html");

htmlReporter.config().setDocumentTitle("Automation Report"); // Title of report
htmlReporter.config().setReportName("Functional Testing"); // Name of the report
htmlReporter.config().setTheme(Theme.DARK);
 
extent = new ExtentReports();
extent.attachReporter(htmlReporter);
 
 // Passing General information
extent.setSystemInfo("Host name", "localhost");
extent.setSystemInfo("Environemnt", "SIT");
extent.setSystemInfo("user", "Abhranil");

}


@AfterTest
public void EndReport1() {
 extent.flush();

}

	
@BeforeMethod
public void setup ()  
{	
		initialization();
		sfdcorpg= new SFDCLoginPage(driver);
					
}
	
		
@AfterMethod
public void tearDown(ITestResult result)throws IOException{		
	
	if (result.getStatus() == ITestResult.FAILURE) {
		   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
		   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
		   String screenshotPath = SFDCloginecryptdpasswd.getScreenshot(driver, result.getName());
		   test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		  } else if (result.getStatus() == ITestResult.SKIP) {
		   test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		  }
		  else if (result.getStatus() == ITestResult.SUCCESS) {
		   test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		  }
		  driver.quit();
		 }
		 

public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot) driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  
		  // after execution, we can see a folder "FailedTestsScreenshots" under src folder
		  String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		  File finalDestination = new File(destination);
		  FileUtils.copyFile(source, finalDestination);
		  return destination;
		 }

public Testbase( ) {
	
	try {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/resource/Propertyfile/config.properties");
		prop.load(ip);		} 
	
	catch (IOException e) {
		e.printStackTrace();
	}
}
	
		
public static void initialization(){
				
		String Browser= prop.getProperty("browser");
		
		if(Browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/com/resource/drivers/chromedriver.exe");			
			driver = new ChromeDriver(); 
					
		}
		else if(Browser.equalsIgnoreCase("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/com/resource/drivers/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.MINUTES);
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
				
	}	
}		
	