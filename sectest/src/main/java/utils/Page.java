package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page {
	
	@FindBy(id="navDashboard")
	WebElement dashboardButton; 
	
	@FindBy(id="navBrand")
	WebElement brandButton; 
	
	@FindBy(id="navCategories")
	WebElement categoriesButton; 
	
	@FindBy(id="navProduct")
	WebElement productButton; 
	
	@FindBy(id="navOrder")
	WebElement ordersButton; 
	
	@FindBy(id="topNavAddOrder")
	WebElement addOrdersButton; 
	
	@FindBy(id="topNavManageOrder")
	WebElement manageOrdersButton; 
	
	@FindBy(id="navSetting")
	WebElement settingsButton; 
	
	@FindBy(id="navReport")
	WebElement reportButton; 
	
	@FindBy(xpath="/html[1]/body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[1]/a[1]")
	WebElement addUserButton; 
	
	@FindBy(xpath="/html[1]/body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[2]/a[1]")
	WebElement settingButton; 
	
	@FindBy(xpath="/html[1]/body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[3]/a[1]")
	WebElement logout;
	
	public void logout() {
		logout.click();
	}
	
	public DashBoardPage movetoDashboard() {
		this.dashboardButton.click();
		Wait();
		return new DashBoardPage(driver);
	}
	
	public AddUserPage moveToAddUser() {
		this.settingsButton.click();
		Wait(); 
		this.addUserButton.click();
		Wait();
		return new AddUserPage(driver);
	}
	
	public BrandPage moveToBrand() {
		this.brandButton.click();
		Wait();
		return new BrandPage(driver);
	}
	public ProductPage moveToProduct() {
		this.productButton.click();
		Wait();
		return new ProductPage(driver);
	}
	public CategoryPage moveToCategory() {
		this.categoriesButton.click();
		Wait();
		return new CategoryPage(driver);
	}
	public SettingPage moveToSetting() {
		this.settingsButton.click();
		Wait(); 
		this.settingButton.click();
		Wait();
		return new SettingPage(driver);
	}
	
	public SettingPage moveToReport() {
		this.reportButton.click();
		Wait(); 
		return new SettingPage(driver);
	}
	
	public ManageOrdersPage moveToManageOrders() {
		this.ordersButton.click();
		Wait(); 
		this.manageOrdersButton.click();
		Wait();
		return new ManageOrdersPage(driver);
	}
	
	public AddOrdersPage moveToAddOrders() {
		this.ordersButton.click();
		Wait();
		this.addOrdersButton.click();
		Wait();
		return new AddOrdersPage(driver);
	}
	
	public void Wait() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getAlertText(){ 
		try {
			return driver.switchTo().alert().getText();
		} catch (Exception e) {
			return null;
		}		 
	}
	
	public void closeAlert() {
		Wait();
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) { 
		}
		Wait();
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) { 
		}
		Wait();
	}
	
	protected WebDriver driver;
	
	public Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
