package week4.day2Assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Amazon:
		int cartotal=0;
		//1.Load the uRL https://www.amazon.in/
		WebDriverManager.chromedriver().setup();
		ChromeOptions ch = new ChromeOptions();
		ch.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(ch);
		//ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//2.search as oneplus 9 pro 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
		//3.Get the price of the first product
		String firstproprice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText().trim();
		String price=firstproprice.replace(",", "");
		int firstprice =Integer.valueOf(price);
		System.out.println("First product price is "+firstprice);
		//4. Print the number of customer ratings for the first displayed product
		String customerrating  = driver.findElement(By.xpath("(//span[contains(@class,'s-underline-text')])[1]")).getText();;
		System.out.println("The number of customer rating for the first product is "+customerrating);
		//5. Mouse Hover on the stars
		/*WebElement mouse = driver.findElement(By.xpath("(//a[@role='button']//i)[2]"));
		Actions builder=new Actions(driver);
		builder.moveToElement(mouse).perform();*/
		driver.findElement(By.xpath("(//a[@role='button']//i)[2]")).click();
		
		Thread.sleep(1000);
		//6. Get the percentage of ratings for the 5 star.
		String percentage = driver.findElement(By.xpath("//table[@id='histogramTable']/tbody[1]/tr[1]/td[3]/span[2]/a[1]")).getText();
		System.out.println("5 star rating percentage is "+percentage);
		//7. Click the first text link of the first image
		driver.findElement(By.xpath("//a[contains(@class,'a-text-normal')]")).click();
		//8. Take a screen shot of the product displayed
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./snap/AmazonProduct.png");
		FileUtils.copyFile(source, dest);
		
		//9. Click 'Add to Cart' button
		Set<String> window = driver.getWindowHandles();
		List<String> lstwindow=new ArrayList<String>(window);
		driver.switchTo().window(lstwindow.get(1));
			System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
		driver.findElement(By.xpath("(//a[@class='a-link-normal close-button'])[1]")).click();
		//10. Get the cart subtotal and verify if it is correct.	
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='nav-cart-count-container']")).click();
		try {
			 String cartotalpr = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']")).getText().trim().replace(",", "");
			 cartotal =Integer.valueOf(cartotalpr.trim().replace(".00", ""));
			System.out.println("The cart total is "+cartotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			System.out.println(e);
		}
		
		if(cartotal==(firstprice))
		{
			System.out.println("Cart price is verified successfully");
		}
		else
		{
			System.out.println("Cart price is different than product price");
		}
		driver.quit();
		

	}

}
