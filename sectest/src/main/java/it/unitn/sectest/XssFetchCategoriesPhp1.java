package it.unitn.sectest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test; 

import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage;
import utils.SettingPage;

public class XssFetchCategoriesPhp1 extends Base{

	DashBoardPage dashboard;
	BrandPage brand;
	CategoryPage categories;
	SettingPage settingPage;
	
	@Test
	public void XssFetchCategoriesPhp1Test() {

		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		
		categories = dashboard.moveToCategory();
		categories.add("<h1>Prova</h1>", "Available");
		
		String actualCategoriesName = categories.getCategoryName();
		
		assertEquals(actualCategoriesName, "<h1>Prova</h1>");
	}
	
	@After
	public void reset() {		
		categories.remove();
	}
	
}
