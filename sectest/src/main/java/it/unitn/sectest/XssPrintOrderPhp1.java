package it.unitn.sectest;
import static org.junit.Assert.assertEquals; 
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

public class XssPrintOrderPhp1 extends Base{

	DashBoardPage dashboard;
	BrandPage brandPage;
	CategoryPage categoriesPage;
	ProductPage productPage;
	AddOrdersPage addOrdersPage;
	ManageOrdersPage manageOrdersPage;
	 
	@Test
	public void XssPrintOrderPhp1Test() {
		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		  
		brandPage = dashboard.moveToBrand();
		brandPage.add("brand", "Available");
		  
		categoriesPage = brandPage.moveToCategory();
		categoriesPage.add("category", "Available");
		  
		productPage = categoriesPage.moveToProduct();
		productPage.add("<h1>prod</h1>", "30", "10", "brand", "category","Available");
		 
		addOrdersPage = productPage.moveToAddOrders();
		
		try { //XSS case
			addOrdersPage.add("01/01/2020", "<h1>one</h1>", "<h1>two</h1>", "prod", "10", "10", "Cash", "Full Payment", "In Gujarat");
		}catch(NoSuchElementException e) { //NOXSS case
			addOrdersPage.moveToAddOrders();
			addOrdersPage.add("01/01/2020", "<h1>one</h1>", "<h1>two</h1>", "<h1>prod</h1>", "10", "10", "Cash", "Full Payment", "In Gujarat");
		}
		 
		manageOrdersPage = addOrdersPage.moveToManageOrders();
		manageOrdersPage.moveToManageOrders();
		manageOrdersPage.print();
		
		String  base= driver.getWindowHandle();
		for (String h : driver.getWindowHandles()) {
			 if(!base.equals(h)) {
				 driver.switchTo().window(h);
			 }
		}
		int count=0;
		try {
			driver.findElement(By.xpath("//td/h1[contains(.,'one')]"));
			count++;
		}catch(Exception e) {}
		
		try {
			driver.findElement(By.xpath("//td/h1[contains(.,'two')]"));
			count++;
		}catch(Exception e) {}
		
		try {
			driver.findElement(By.xpath("//td/h1[contains(.,'prod')]"));
			count++;
		}catch(Exception e) {}
		
		driver.close();
		driver.switchTo().window(base);
		
		assertEquals(3, count);
	}
	
	@After
	public void reset() throws InterruptedException {
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
