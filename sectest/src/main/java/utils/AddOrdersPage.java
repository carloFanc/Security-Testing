package utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddOrdersPage extends Page{
	
	@FindBy(id="orderDate")
	WebElement orderDateTextBox; 	
	
	@FindBy(id="clientName")
	WebElement clientNameTextBox; 
	
	@FindBy(id="clientContact")
	WebElement clientContactTextBox; 
	
	@FindBy(id="discount")
	WebElement discountTextBox; 
	
	@FindBy(id="paid")
	WebElement paidTextBox; 
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/form[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/select[1]")
	WebElement addProduct1;
	
	@FindBy(id="createOrderBtn")
	WebElement createOrderBtn;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/form[1]/table[1]/tbody[1]/tr[1]/td[6]/button[1]")
	WebElement trashButton;
	
	@FindBy(id="addRowBtn")
	WebElement addRowBtn;

		
	public AddOrdersPage(WebDriver driver) {
		super(driver);
	}

	public void add(String orderDate, String clientName, String clientContact, String product, String discount, String paid, 
			String paymentType, String paymentStatus, String paymentPlace) {
		
		
		WebElement inputField = driver.findElement(By.id("orderDate"));
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].setAttribute('value', arguments[1])", inputField, orderDate);
		
		clientNameTextBox.click();
		clientNameTextBox.sendKeys(clientName);
		clientContactTextBox.sendKeys(clientContact);
		discountTextBox.sendKeys(discount);
		paidTextBox.sendKeys(paid);
		
		trashButton.click();
		trashButton.click();
		Wait();
		
		Select productName = new Select(driver.findElement(By.cssSelector("#productName3")));
		productName.selectByVisibleText(product);
		
		Select paymentTypeElm = new Select(driver.findElement(By.id("paymentType")));
	
		if (paymentType.equals("Cheque")){
			paymentTypeElm.selectByVisibleText("Cheque");
		}
		else if (paymentType.equals("Cash")){
			paymentTypeElm.selectByVisibleText("Cash");
		}
		else {
			paymentTypeElm.selectByVisibleText("Credit Card");
		}
		
		Select paymentStatusElm = new Select(driver.findElement(By.id("paymentStatus")));
		if (paymentStatus.equals("Full Payment")){
			paymentStatusElm.selectByVisibleText("Full Payment");
		}
		else if (paymentStatus.equals("Advanced Payment")){
			paymentTypeElm.selectByVisibleText("Advanced Payment");
		}
		else {
			paymentStatusElm.selectByVisibleText("No Payment");
		}
		
		Select paymentPlaceElm = new Select(driver.findElement(By.id("paymentPlace")));
		if (paymentPlace.equals("In Gujarat")){
			paymentPlaceElm.selectByVisibleText("In Gujarat");
		}
		else {
			paymentPlaceElm.selectByVisibleText("Out Of Gujarat");
		}
		Wait();

		createOrderBtn.click();
		Wait();

	}
	
	public void addRow() {
		addRowBtn.click();
	}

	public void remove() { 
	}

}
