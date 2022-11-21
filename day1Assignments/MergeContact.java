package week4.day1Assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		Set<String> stFrm1 = driver.getWindowHandles();
		List<String> LstFrm1= new ArrayList<String>(stFrm1);
		driver.switchTo().window(LstFrm1.get(1));
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])//a[1]")).click();
		driver.switchTo().window(LstFrm1.get(0));
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> stfrm2 = driver.getWindowHandles();
		List<String> lstfrm2= new ArrayList<String>(stfrm2);
		driver.switchTo().window(lstfrm2.get(1));
	
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		driver.switchTo().window(lstfrm2.get(0));
		driver.findElement(By.linkText("Merge")).click();
		Alert al=driver.switchTo().alert();
		al.accept();
		
		Thread.sleep(1000);
		String title = driver.getTitle();
		if(title.contains("View Contact"))
		{
			System.out.println("Title is verified successfully "+ title);
		}
		else 
		{
			System.out.println("Wrong title "+title);
		}
		driver.close();
		
		
		
	}

}
