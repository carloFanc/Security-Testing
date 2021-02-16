package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddUserPage extends Page{
	
	@FindBy(id="addUserModalBtn")
	WebElement addUserModalBtn; 
	
	@FindBy(id="userName")
	WebElement userNameTextBox; 
	
	@FindBy(id="upassword")
	WebElement userPasswordTextBox; 
	
	@FindBy(id="uemail")
	WebElement userEmailTextBox; 
	
	@FindBy(id="createUserBtn")
	WebElement createUserBtn; 
	
	@FindBy(id="editProductBtn")
	WebElement editProductBtn; 
	
	@FindBy(id="editUserModalBtn")
	WebElement editUserModalBtn;
	
	@FindBy(id="edituserName")
	WebElement edituserNameBox;
	
	@FindBy(id="editPassword")
	WebElement editPasswordBox;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[5]/button[1]")
	WebElement closeeditBtn;
	
	@FindBy(id="editBio")
	WebElement editBio;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[3]/button[1]")
	WebElement closeModalBtn;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[2]/td[1]")
	WebElement addedUserName;

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/button[1]")
	WebElement actionUserBtn;
	
	public AddUserPage(WebDriver driver) {
		super(driver);
	}
	
	public void edit(String username, String password, String bio) {
		Wait();
		Wait();
		actionUserBtn.click();
		Wait();
		editUserModalBtn.click();
		Wait();
		edituserNameBox.clear();
		edituserNameBox.sendKeys(username);
		Wait();
		editPasswordBox.clear();
		editPasswordBox.sendKeys(password);
		Wait();
		editBio.clear();
		editBio.sendKeys(bio);
		Wait();
		editProductBtn.click();
		Wait();
		closeeditBtn.click();
		Wait();
	}
	
	public void add(String username, String password, String email) {
		addUserModalBtn.click();
		Wait();
		userNameTextBox.sendKeys(username);
		userPasswordTextBox.sendKeys(password);
		userEmailTextBox.sendKeys(email);
		Wait();
		createUserBtn.click();
		Wait();
		closeModalBtn.click();
		Wait();
	}
	
	public String getAddedUsername() {
		return addedUserName.getAttribute("innerHTML");
	}


	public void remove() {
		Wait();
		actionUserBtn.click();
		Wait();
		Wait();
		WebElement removeUserModalBtn = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[2]/td[2]/div[1]/ul[1]/li[2]/a[1]"));
		removeUserModalBtn.click();
		Wait();
		WebElement removeUserConfirmationtBtn = driver.findElement(By.id("removeProductBtn"));
		removeUserConfirmationtBtn.click();
		Wait();
	}
		
}
