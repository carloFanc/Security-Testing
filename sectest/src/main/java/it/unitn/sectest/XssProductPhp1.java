package it.unitn.sectest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement; 
 
import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage; 
import utils.ProductPage;

public class XssProductPhp1 extends Base{

	DashBoardPage dashboard;
	BrandPage brandPage;
	CategoryPage categoriesPage;
	ProductPage productPage;
	
	@Test
	public void XssProductPhp1Test() {
	 
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		 
		brandPage = dashboard.moveToBrand();
		brandPage.add("prod</option></select><h1>test</h1>", "Available");
		 
		categoriesPage = dashboard.moveToCategory();
		categoriesPage.add("category", "Available");
		 
		productPage = categoriesPage.moveToProduct();
		productPage.addProductButton.click();
		productPage.Wait();
		
		try {
			String injectedvar = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[6]/div[1]/h1[1]")).getAttribute("innerHTML");
			assertEquals(injectedvar, "test");
		}catch(NoSuchElementException e){
			fail("can't find injected <h1> tag, thus no XSS");		
		}
	}
	
	@After
	public void reset() throws InterruptedException {
		WebElement closeButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[3]/button[1]"));
		closeButton.click();
		productPage.Wait();
		brandPage = productPage.moveToBrand();
		brandPage.remove();
		categoriesPage = brandPage.moveToCategory();
		categoriesPage.remove();
	}
	
}
