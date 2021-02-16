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

public class XssProductPhp4 extends Base{

	DashBoardPage dashboard;
	BrandPage brandPage;
	CategoryPage categoriesPage;
	ProductPage productPage;
	AddOrdersPage addOrdersPage;
	ManageOrdersPage manageOrdersPage;
	
	@Test
	public void XssProductPhp4Test() {
	 
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		 
		brandPage = dashboard.moveToBrand();
		brandPage.add("brand</option></select><h1>test</h1>", "Available");
		 
		categoriesPage = brandPage.moveToCategory();
		categoriesPage.add("category", "Available");
		 
		productPage = categoriesPage.moveToProduct(); 
		
		try {
			productPage.add("prova", "10", "10", "brand", "category", "Available");
		}catch(NoSuchElementException e) {
			productPage.closeAdd();
			productPage.add("prova", "10", "10", "brand</option></select><h1>test</h1>", "category", "Available");
		}
		productPage.openEdit();
		try {
			String var = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[5]/div[1]/h1[1]")).getAttribute("innerHTML");
			assertEquals(var, "test");
		}catch(NoSuchElementException e) {
			fail("can't find injected <h1> tag, thus no XSS");
		}
	}
	
	@After
	public void reset() throws InterruptedException {
		productPage.closeEdit();
		productPage.remove();
		brandPage = productPage.moveToBrand();
		brandPage.remove();
		categoriesPage = brandPage.moveToCategory();
		categoriesPage.remove();
	}
	
}
