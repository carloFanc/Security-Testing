package utils;
  
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page{
	
	@FindBy(id="productName")
	WebElement productNameTextBox; 
	
	@FindBy(id="addProductModalBtn")
	public WebElement addProductButton; 	

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[1]/td[8]/div[1]/button[1]")
	WebElement actionBtn;
	
	@FindBy(css="#removeProductModalBtn")
	WebElement removeProductModalBtn;
	
	@FindBy(css="#editProductModalBtn")
	WebElement editProductModalBtn;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[8]/button[1]")
	WebElement closeEdit;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[3]/button[1]")
	WebElement closeAdd;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/ul[1]/li[2]/a[1]")
	WebElement productinfo;
	
	@FindBy(id="removeProductBtn")
	WebElement removeProductBtn;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[1]/td[2]")
	WebElement productNameLabel;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[1]/td[3]")
	WebElement rateLabel;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[1]/td[5]")
	WebElement brandNameLabel;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[1]/td[6]")
	WebElement categoryNameLabel;
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	private void getUploadFile() {
		URL res = getClass().getClassLoader().getResource("image.jpg");
		File file = null;
		try {
			file = Paths.get(res.toURI()).toFile();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String absolutePath = file.getAbsolutePath(); 
		driver.findElement(By.id("productImage")).sendKeys(absolutePath);
	}

	public void add(String name, String quantity, String rate, String brand, String category, String status) {
		 
		this.addProductButton.click();
		Wait();
		
		getUploadFile();
		Wait();
		productNameTextBox.sendKeys(name);
		
		WebElement quantityTextBox = driver.findElement(By.id("quantity"));
		quantityTextBox.sendKeys(quantity);
		
		WebElement rateTextBox = driver.findElement(By.id("rate"));
		rateTextBox.sendKeys(rate);

		Select elmBrand = new Select(driver.findElement(By.id("brandName")));
		elmBrand.selectByVisibleText(brand);
		
		Select elmCategory = new Select(driver.findElement(By.id("categoryName")));
		elmCategory.selectByVisibleText(category);

		Select elmStatus = new Select(driver.findElement(By.id("productStatus")));
		if (status.equals("Available")){
			elmStatus.selectByVisibleText("Available");
		}
		else {
			elmStatus.selectByVisibleText("Not Available");
		}
		Wait();
		
		WebElement saveButton = driver.findElement(By.id("createProductBtn"));
		saveButton.click();
		Wait();
		
		WebElement closeButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[3]/button[1]"));
		
		closeButton.click();
		Wait();
	}

	public void openEdit() {
		actionBtn.click();
		Wait();
		editProductModalBtn.click();
		Wait();
		productinfo.click();
		Wait();
	}
	
	public void closeEdit() {
		closeEdit.click();
		Wait();
	}
	
	public void closeAdd() {
		closeAdd.click();
		Wait();
	}

	public void remove() {
		Wait();
		actionBtn.click();
		Wait();
		removeProductModalBtn.click();
		Wait();
		removeProductBtn.click();
		Wait();
	}
	
	public String getProductName() {
		return productNameLabel.getAttribute("innerHTML");
	}
	
	public String getBrandName() {
		return brandNameLabel.getAttribute("innerHTML");
	}

	public String getCategoryName() {
		return categoryNameLabel.getAttribute("innerHTML");
	}

	public String getRate() {
		return rateLabel.getAttribute("innerHTML");
	}
 
}
