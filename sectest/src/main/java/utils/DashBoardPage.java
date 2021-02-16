package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DashBoardPage extends Page{
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[5]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]")
	WebElement username;
	
	
	public DashBoardPage(WebDriver driver) {
		super(driver);
	}
	
	public String getUser() {
		return this.username.getAttribute("innerHTML");
	}
	
	
}