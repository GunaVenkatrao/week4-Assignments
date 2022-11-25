package week4.day2Assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HtmlTableValue {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://html.com/tags/table/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		List<WebElement> col = driver.findElements(By.xpath("(//div[@class='render'])//table//tr//th"));
		//System.out.println(col.size());
		//System.out.println(rowval.size());
//System.out.println(rows.get(2).getText());
String Libname="Absolute Usage";
int Val=0;
if (Libname.equals("Absolute Usage"))
{
	Val=2;
}
else if (Libname.equals("Market Share"))
{
	Val=1;
}
System.out.println(Libname);
List<WebElement> Rowval = driver.findElements(By.xpath("(//div[@class='render'])//table//tr["+Val+"]//td"));
for (int i = 1; i <col.size(); i++) {
		  	  
		  
		 System.out.println("values of "+col.get(i).getText()+" is "+Rowval.get(i).getText());
		 
		}
driver.close();
	}

	}

