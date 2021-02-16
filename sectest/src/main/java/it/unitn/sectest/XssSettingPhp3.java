package it.unitn.sectest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
 
import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage; 
import utils.SettingPage;

public class XssSettingPhp3 extends Base{

	DashBoardPage dashboard;
	BrandPage brand;
	CategoryPage categories;
	SettingPage settingsPage;
	String actualUserName;
	@Test
	public void XssSettingPhp3Test() {
 
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		
		settingsPage = dashboard.moveToSetting();
		 
		settingsPage.changeBio("prova \"/> <h1>test</h1>");
		settingsPage.moveToSetting();
		
		try {
			String xssvar = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[2]/fieldset[1]/div[2]/div[1]/h1[1]")).getAttribute("innerHTML");
			assertEquals(xssvar, "test");
		}catch(NoSuchElementException e){
			fail("can't find injected <h1> tag, thus no XSS");		
		}
	}
	
	@After
	public void reset() {
		settingsPage.changeUsername("admin");
	}
	
}
