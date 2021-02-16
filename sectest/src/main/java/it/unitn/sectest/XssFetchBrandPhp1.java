package it.unitn.sectest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage; 

public class XssFetchBrandPhp1 extends Base{

	DashBoardPage dashboard;
	BrandPage brand;
	CategoryPage categories; 
	
	@Test
	public void XssFetchBrandPhp1Test() {
		
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		
		brand = dashboard.moveToBrand(); 
		brand.add("<h1>Prova</h1>", "Available");
		
		String changedbrandname = brand.getBrandName();
		
		assertEquals("<h1>Prova</h1>", changedbrandname);
	}
	
	@After
	public void reset() {	
		brand.remove();
	}
	
}
