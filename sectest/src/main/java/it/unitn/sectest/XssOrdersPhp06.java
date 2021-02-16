package it.unitn.sectest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
 
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import utils.AddOrdersPage;
import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage;
import utils.ManageOrdersPage;
import utils.ProductPage;
import utils.SettingPage;
 
public class XssOrdersPhp06 extends Base{

	DashBoardPage dashboard;
	BrandPage brand;
	CategoryPage categories;
	SettingPage settingsPage;
	ProductPage products;
	ManageOrdersPage orders; 
	AddOrdersPage addorders;
 
	@Test
	public void XssOrdersPhp06Test() {
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		
		driver.get("http://localhost/inventory-management-system/orders.php?o=editOrd&i=<h1>test</h1>");
		dashboard.Wait();
		try {
			String injected = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/h1[1]")).getAttribute("innerHTML");
			assertEquals(injected, "test");
		}catch(NoSuchElementException e){
			fail("can't find injected <h1> tag, thus no XSS");
		}
	}
 
}
