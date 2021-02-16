package it.unitn.sectest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By; 

import utils.AddUserPage;
import utils.Base;
import utils.BrandPage;
import utils.CategoryPage;
import utils.DashBoardPage;
import utils.LoginPage;
import utils.ProductPage;

public class XssFetchUserPhp1 extends Base{

	DashBoardPage dashboard;
	BrandPage brand;
	CategoryPage categories;
	ProductPage productPage;
	AddUserPage addUserPage;
	
	@Test
	public void XssFetchUserPhp1Test() {

		LoginPage login = new LoginPage(driver);
		dashboard = login.login("admin", "admin");
		
		addUserPage = dashboard.moveToAddUser(); 
		addUserPage.add("<h1>username</h1>", "password", "user@email.com");
		
		String actualUserName= addUserPage.getAddedUsername();
		
		assertEquals("<h1>username</h1>", actualUserName);
	}
	
	@After
	public void reset() {		
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/button[1]")).click();
		addUserPage.Wait();
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/ul[1]/li[2]/a[1]")).click();
		addUserPage.Wait();
		driver.findElement(By.id("removeProductBtn")).click();
		addUserPage.Wait();
	}
	
}
