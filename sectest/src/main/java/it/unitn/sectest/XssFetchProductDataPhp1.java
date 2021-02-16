package it.unitn.sectest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import utils.AddOrdersPage;
import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage;
import utils.ManageOrdersPage;
import utils.ProductPage;
import utils.SettingPage;

public class XssFetchProductDataPhp1 extends Base{

	DashBoardPage dashboard;
	BrandPage brandPage;
	SettingPage settingsPage;
	CategoryPage categoriesPage;
	ProductPage productPage;
	AddOrdersPage addOrdersPage;
	ManageOrdersPage manageOrdersPage; 
	
	@Test
	public void XssFetchProductDataPhp1Test() {
		 
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		
		brandPage = dashboard.moveToBrand();
		brandPage.add("brand", "Available");
	 
		categoriesPage = brandPage.moveToCategory();
		categoriesPage.add("category", "Available");
	
		productPage = categoriesPage.moveToProduct();
		productPage.add("G</option></select><h1>test</h1>", "30", "1", "brand", "category", "Available"); 
		 
		addOrdersPage = productPage.moveToAddOrders();
		addOrdersPage.Wait();
		
		driver.findElement(By.id("addRowBtn")).click();
		try {
			String injectedvar = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[2]/form[1]/table[1]/tbody[1]/tr[4]/td[1]/div[1]/h1[1]")).getAttribute("innerHTML");
			assertEquals(injectedvar, "test");
		} catch(NoSuchElementException e) {
			fail("can't find the injected element, thus there's no XSS vulnerability");
		}
	}
	
	@After
	public void reset() {		
		productPage = addOrdersPage.moveToProduct();
		productPage.remove();
		categoriesPage = productPage.moveToCategory();
		categoriesPage.remove();
		brandPage = categoriesPage.moveToBrand();
		brandPage.remove();
	} 
	
}
