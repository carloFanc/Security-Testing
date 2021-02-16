package it.unitn.sectest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.AddOrdersPage;
import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage;
import utils.ManageOrdersPage;
import utils.ProductPage;

public class XssOrdersPhp52 extends Base{

	DashBoardPage dashboard;
	BrandPage brandPage;
	CategoryPage categoriesPage;
	ProductPage productPage;
	AddOrdersPage addOrdersPage;
	ManageOrdersPage manageOrdersPage;
	
	@Test
	public void XssOrdersPhp52Test() {
		 
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		  
		brandPage = dashboard.moveToBrand();
		brandPage.add("brand", "Available");
		  
		categoriesPage = brandPage.moveToCategory();
		categoriesPage.add("category", "Available");
		  
		productPage = categoriesPage.moveToProduct();
		productPage.add("Prova", "30", "10", "brand", "category","Available");
		 
		addOrdersPage = productPage.moveToAddOrders();
	    addOrdersPage.add("01/01/2020", "prv", "l", "Prova", "0", "0", "Cash", "Full Payment", "In Gujarat");
		 
		manageOrdersPage = addOrdersPage.moveToManageOrders();
		manageOrdersPage.edit();
		WebElement gstnbox = driver.findElement(By.id("gstn"));
		gstnbox.sendKeys("\"> <h1>test</h1>");
		driver.findElement(By.id("editOrderBtn")).click();
		manageOrdersPage.moveToManageOrders();
		manageOrdersPage.edit();
		try {
			String injectedvar = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[2]/form[1]/div[4]/div[6]/div[1]/h1[1]")).getAttribute("innerHTML");
			assertEquals(injectedvar, "test");
		}catch(NoSuchElementException e){
			fail("can't find injected <h1> tag, thus no XSS");
		}
	}
	
	@After
	public void reset() {		
		manageOrdersPage.moveToManageOrders();
		manageOrdersPage.remove();
		productPage = addOrdersPage.moveToProduct();
		productPage.remove();
		categoriesPage = productPage.moveToCategory();
		categoriesPage.remove();
		brandPage = categoriesPage.moveToBrand();
		brandPage.remove();
	}   
}
