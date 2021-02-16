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
import utils.SettingPage;

public class XssDashboardPhp10 extends Base{

	DashBoardPage dashboard; 
	SettingPage settingsPage;
	ManageOrdersPage orders; 
	BrandPage brandPage;
	CategoryPage categoriesPage;
	AddOrdersPage addOrdersPage;
	ProductPage productPage;
	String actualUserName; 
	
	@Test
	public void XssDashboardPhp10Test() {

		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		 
		brandPage = dashboard.moveToBrand();
		brandPage.add("brand", "Available");
		
		categoriesPage = brandPage.moveToCategory();
		categoriesPage.add("category", "Available");
		
		productPage = categoriesPage.moveToProduct();
		productPage.add("prova", "10", "10", "brand", "category", "Available");
	
		addOrdersPage = productPage.moveToAddOrders();
		addOrdersPage.add("01/01/2020", "asd", "tiz", "prova", "0", "0", "Cash", "Full Payment", "In Gujarat");
		orders = addOrdersPage.moveToManageOrders();
		
		actualUserName= "admin";
		
		settingsPage = orders.moveToSetting(); 
		settingsPage.changeUsername("<h1>admin</h1>");
		
		dashboard = settingsPage.movetoDashboard();
		String changedUser= dashboard.getUser();
		assertEquals("<h1>admin</h1>", changedUser);
	}
	
	@After
	public void reset() {	
		settingsPage = dashboard.moveToSetting();
		settingsPage.changeUsername(actualUserName);
		productPage = settingsPage.moveToProduct();
		productPage.remove();
		categoriesPage = productPage.moveToCategory();
		categoriesPage.remove();
		brandPage = categoriesPage.moveToBrand();
		brandPage.remove();
		brandPage.moveToManageOrders().remove();
	}
	
}
