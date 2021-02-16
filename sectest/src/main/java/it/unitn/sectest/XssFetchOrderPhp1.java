package it.unitn.sectest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test; 

import utils.AddOrdersPage;
import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage;
import utils.ManageOrdersPage;
import utils.ProductPage;

public class XssFetchOrderPhp1 extends Base{

	DashBoardPage dashboard;
	BrandPage brandPage;
	CategoryPage categoriesPage;
	ProductPage productPage;
	AddOrdersPage addOrdersPage;
	ManageOrdersPage manageOrdersPage;
	
	@Test
	public void XssFetchOrderPhp1Test() {
 
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		 
		brandPage = dashboard.moveToBrand();
		brandPage.add("brand", "Available");
		  
		categoriesPage = brandPage.moveToCategory();
		categoriesPage.add("category", "Available");
		
		productPage = categoriesPage.moveToProduct();
		productPage.add("Prodotto", "30", "1", "brand", "category", "Available");
		
		addOrdersPage = productPage.moveToAddOrders();
		addOrdersPage.add("01/12/2012", "<h1>name</h1>", "<h1>contact</h1>", "Prodotto", "0", "0", "Cash", "No Payment", "In Gujarat");
		manageOrdersPage = addOrdersPage.moveToManageOrders();
		 
		String actualClientName = manageOrdersPage.getClientdName();
		String actualClientContact = manageOrdersPage.getClientContact();

		assertEquals("<h1>name</h1>", actualClientName);
		assertEquals("<h1>contact</h1>", actualClientContact);
	}
	
	@After
	public void reset() {		 
		manageOrdersPage.remove();
		productPage = manageOrdersPage.moveToProduct();
		productPage.remove();
		categoriesPage = productPage.moveToCategory();
		categoriesPage.remove();
		brandPage = categoriesPage.moveToBrand();
		brandPage.remove();
	}
	}
	 