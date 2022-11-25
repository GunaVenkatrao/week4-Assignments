package week4.day2Assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittorgarh {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.chittorgarh.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("navbtn_stockmarket")).click();
		driver.findElement(By.linkText("NSE Bulk Deals")).click();
		System.out.println(driver.getTitle());
		List<WebElement> lst = driver.findElements(By.xpath("//div[@class='table-responsive']//table//tr//td[3]"));
		Set<String> st=new LinkedHashSet<String>(); 
		List<String> lstdup=new ArrayList<String>();
				for(int i = 0; i < lst.size(); i++) {
			st.add(lst.get(i).getText());
		}
				for (String output : st) {
					lstdup.add(output);
				}

				int count=0;
				for (int i = 0; i < lstdup.size()-1; i++) {
					count=0;
					for (int j = 0; j < lstdup.size()-1; j++)
					{
						if (lstdup.get(i).equals(lstdup.get(j)))
						{
							count=count+1;
							
						}	
						
					}
					if(count>1)
					{
						System.out.println("There are duplicates");
					}					
				}	
					driver.close();
	}

}
