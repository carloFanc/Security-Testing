package utils;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageOrdersPage extends Page{
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[3]")
	WebElement clientName; 	
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")
	WebElement clientContact; 
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[7]/div[1]/button[1]")
	WebElement actionBtn;
	
	@FindBy(id="editOrderModalBtn")
	WebElement editOrderModalBtn;
	
	@FindBy(id="removeOrderModalBtn")
	WebElement removeOrderModalBtn;	
	
	@FindBy(id="removeOrderBtn")
	WebElement removeOrderBtn;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[7]/div[1]/ul[1]/li[3]/a[1]")
	WebElement printBtn;
	
	public ManageOrdersPage(WebDriver driver) {
		super(driver);
	}

	public void remove() {
		actionBtn.click();
		Wait();
		removeOrderModalBtn.click();
		Wait();
		removeOrderBtn.click();
		Wait();
	}

	public String getClientdName() {
		Wait();
		return clientName.getAttribute("innerHTML");
	}

	public String getClientContact() {
		return clientContact.getAttribute("innerHTML");
	}

	public void print() {
		actionBtn.click();
		Wait();
		printBtn.click();
		Wait();
	}

	public void edit() {
		actionBtn.click();
		Wait();
		editOrderModalBtn.click();
		Wait();
	}

}
