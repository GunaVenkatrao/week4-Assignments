package week4.day2Assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		//1) Go to https://www.nykaa.com/ 
		WebDriverManager.chromedriver().setup();
		ChromeOptions ch = new ChromeOptions();
		ch.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(ch);
		//ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//2) Mouseover on Brands and Search L'Oreal Paris
		WebElement mouse = driver.findElement(By.xpath("(//a[@href='/'])[2]"));
		Actions builder= new Actions(driver);
		builder.moveToElement(mouse).perform();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
		//3) Click L'Oreal Paris
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		//4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		System.out.println(title);
		if(title.contains("L'Oreal Paris"))
		{
			System.out.println(title +" page loaded successfully.");
		}
		
		//5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']//parent::button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[@for='radio_customer top rated_undefined']")).click();
		
		Thread.sleep(500);
		//6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//div[@id='first-filter']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//label[contains(@for,'checkbox_Shampoo')]//div[2]")).click();
		Thread.sleep(1000);
		 //7) Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("(//label[@class='control control-checkbox'])[6]//div[2]")).click();
		//8)check whether the Filter is applied with Shampoo   
		String filtertext = driver.findElement(By.xpath("//span[@class='filter-value']")).getText();
		if(filtertext.contains("Shampoo"))
		{
			System.out.println("Shampoo filter is applied correctly");
		}
		else
		{
			System.out.println("Shampoo filter is not applied");
		}
		
		// 9) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]")).click();
		//10) GO to the new window and select size  as 175ml
		Set<String> frame1 = driver.getWindowHandles();
		List<String> LstFrm1= new ArrayList<String>(frame1);
		driver.switchTo().window(LstFrm1.get(1));
		
		WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select dropsize=new Select(size);
		dropsize.selectByValue("0");
		
		//11) Print the MRP of the product
		String MRPPrice = driver.findElement(By.xpath("//span[contains(text(),'MRP')]//following-sibling::span")).getText();
		System.out.println("MRP of the product is "+MRPPrice.substring(1));
//driver.close();
		
		//12) Click on ADD to BAG
		driver.findElement(By.xpath("(//span[text()='Add to Bag']//parent::button)[1]")).click();
		//13) Go to Shopping Bag 
		driver.findElement(By.xpath("//span[@class='cart-count']//parent::button")).click();
		//14) Print the Grand Total amount

		driver.switchTo().frame(0);
		Thread.sleep(1000);
		//String GrandTot = driver.findElement(By.xpath("(//span[text()='Grand Total']/preceding::span)[1]")).getText().substring(1);
		String GrandTot =driver.findElement(By.xpath("//div[contains(@class,'footer-layout')]//div//div//div[1]//span")).getText().substring(1);
		//String GrandTot =driver.findElement(By.xpath("//span[@class='css-n8gwxi e171rb9k3']")).getText().substring(1);
		int grandval = Integer.valueOf(GrandTot);
		System.out.println("Grand Total is "+grandval);
		//15) Click Proceed 
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		//16) Click on Continue as Guest 
		driver.findElement(By.xpath("//button[contains(text(),'guest')]")).click();
		//17) Check if this grand total is the same in step 14 
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//p[text()='New Address']/preceding::span[1]")).click();
		String CartTotal = driver.findElement(By.xpath("//p[@class='css-5t7v9l eka6zu20']")).getText().substring(1);
		int Cartval =Integer.valueOf(CartTotal);
		System.out.println("Total to pay is "+Cartval);
		if (grandval==Cartval)
		{
			System.out.println("Grant total and Total pay are same");
		}
		else 
		{
			System.out.println("Grand total and Total Pay is not same");
		}
		//18)Close all windows
		driver.quit();
	
}

}
