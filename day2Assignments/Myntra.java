package week4.day2Assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		//Myntra
		//1) Open https://www.myntra.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions ch = new ChromeOptions();
		ch.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(ch);
		//ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//2) Mouse hover on MeN 
		WebElement men = driver.findElement(By.xpath("//a[text()='Men']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(men).perform();
		//3) Click Jackets 
		driver.findElement(By.xpath("//a[text()='Jackets']")).click();
		System.out.println(driver.getTitle());
		//4) Find the total count of item 
		String counttext = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		int totalcount = Integer.parseInt(counttext.replaceAll("[^0-9]", " ").trim());
		System.out.println("Total count of items is "+totalcount);
		//System.out.println("Total count of items is "+count);
		//5) Validate the sum of categories count matches
		List<WebElement> counts = driver.findElements(By.xpath("//span[@class='categories-num']"));
		List<Integer> lstcount=new ArrayList<Integer>();
		for (int i = 0; i < counts.size(); i++) {
			String text = counts.get(i).getText();
			lstcount.add(Integer.parseInt(text.replaceAll("[^0-9]", " ").trim()));
			
		}
		int countofitems=0;
		System.out.println(lstcount);
		for (int i = 0; i < lstcount.size(); i++) {
			countofitems=countofitems+lstcount.get(i);
			
		}
		System.out.println(countofitems);
		
		if (totalcount!=countofitems)
		{
			System.out.println("Total count and sum of categories are different");
		}
		else 
		{
			System.out.println("Total count and sum of categories are matched");
		}
		
		//6) Check jackets
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		//7) Click + More option under BRAND
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		//8) Type Duke and click checkbox
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Duke");
		driver.findElement(By.xpath("//label[@class=' common-customCheckbox']//div")).click();
		//9) Close the pop-up x
		driver.findElement(By.xpath("//span[contains(@class,'FilterDirectory-close sprites-remove')]")).click();
		//10) Confirm all the Coats are of brand Duke
		    //Hint : use List 
		/*Set<String> windows = driver.getWindowHandles();
		List<String> lst=new ArrayList<String>(windows);
		System.out.println(lst);*/
		Thread.sleep(1000);
		
		List<WebElement> brandslist = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		Set<String> stbrands=new HashSet<String>();
		for (int i = 0; i <brandslist.size(); i++) {
			stbrands.add(brandslist.get(i).getText());
		}
		System.out.println(stbrands);
		List<String> lstbrands=new ArrayList<String>(stbrands);
		for (int i = 0; i < lstbrands.size(); i++) {
			if((lstbrands.get(i).equals("Duke")))
					{
				System.out.println("Only Duke brand Jackets are available");
					}
			else
			{
				System.out.println("Other brand jackets are also included");
			}
			
		}
		//11) Sort by Better Discount
		driver.findElement(By.xpath("//div[@class='sort-sortBy']")).click();
		driver.findElement(By.xpath("//input[@value='discount']//parent ::label")).click();
		Thread.sleep(1000);
		//12) Find the price of first displayed item
		driver.findElement(By.xpath("//div[@class='filter-summary-selectedFilterContainer']")).click();
		Thread.sleep(1000);
		String price = driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).getText();
		System.out.println(price);
		//Click on the first product
		driver.findElement(By.xpath("(//img[@class='img-responsive'])[1]")).click();
		//13) Take a screen shot
		Set<String> windows = driver.getWindowHandles();
		List<String> lstwindow=new ArrayList<String>(windows);
		driver.switchTo().window(lstwindow.get(1));
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./snap/Myntra.png");
		FileUtils.copyFile(source, dest);
		//14) Click on WishList Now
		driver.findElement(By.xpath("//div[contains(@class,'pdp-add-to-wishlist')]")).click();
		
		//15) Close Browser
		driver.close();
		driver.quit();
		

	}

}
