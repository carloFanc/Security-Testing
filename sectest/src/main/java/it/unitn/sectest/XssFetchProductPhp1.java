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

public class XssFetchProductPhp1 extends Base{

	DashBoardPage dashboard;
	BrandPage brandPage;
	CategoryPage categoriesPage;
	ProductPage productPage;
	AddOrdersPage addOrdersPage;
	ManageOrdersPage manageOrdersPage;
	
	@Test
	public void XssFetchProductPhp1Test() {
 
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		  
		brandPage = dashboard.moveToBrand();
		brandPage.add("<h1>brand</h1>", "Available");
		 
		categoriesPage = dashboard.moveToCategory();
		categoriesPage.add("<h1>category</h1>", "Available");
		  
		productPage = dashboard.moveToProduct();
		productPage.add("<h1>productName</h1>", "30", "<h1>rate</h1>", "brand", "category", "Available");
		 
		 
		String actualproductName = productPage.getProductName();
		assertEquals("<h1>productName</h1>", actualproductName);
		
		String actualBrandName = productPage.getBrandName();
		assertEquals("<h1>brand</h1>", actualBrandName);
		
		String actualCategoryName = productPage.getCategoryName();
		assertEquals("<h1>category</h1>", actualCategoryName);
		
		String actualRate = productPage.getRate();
		assertEquals("<h1>rate</h1>", actualRate);
	}
	
	@After
	public void reset() {		 
		productPage.remove();
		brandPage = productPage.moveToBrand();
		brandPage.remove();
		categoriesPage = brandPage.moveToCategory(); 
		categoriesPage.remove(); 
	}
	
}
