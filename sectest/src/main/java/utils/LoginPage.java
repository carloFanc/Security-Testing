package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page{
	
	
	@FindBy(id="username")
	WebElement userTextBox; 
	@FindBy(id="password")
	WebElement passTextBox; 
	@FindBy(xpath="/html/body/div/div/div/div/div[2]/form/fieldset/div[3]/div/button")
	WebElement submitButton; 
	@FindBy(xpath="//*[@id=\\\"navSetting\\\"]/a")
	WebElement dropDownMenuButton; 
	@FindBy(xpath="/html/body/nav/div/div[2]/ul/li[7]/ul/li[3]/a")
	WebElement logOutButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
		
	public DashBoardPage login(String username, String password) {
		this.userTextBox.sendKeys(username);
		this.passTextBox.sendKeys(password);
		this.submitButton.click();
		Wait();
		return new DashBoardPage(driver);
	}
	
	public void logout() {
		this.dropDownMenuButton.click();
		this.logOutButton.click();
	}
	
}
