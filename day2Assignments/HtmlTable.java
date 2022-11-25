package week4.day2Assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HtmlTable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://html.com/tags/table/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//table 1
		List<WebElement> col = driver.findElements(By.xpath("(//div[@class='render'])//table//th"));
		 System.out.println("Column count is "+col.size());
			List<WebElement> row = driver.findElements(By.xpath("(//div[@class='render'])//table//tr"));
			 System.out.println("Column count is "+row.size());	
			 //all tables
				List<WebElement> col1 = driver.findElements(By.xpath("//table//th"));
				 System.out.println("Column count is "+col1.size());
					List<WebElement> row1 = driver.findElements(By.xpath("//table//tr"));
					 System.out.println("Column count is "+row1.size());	
					 
			 driver.close();
			 }
	}
