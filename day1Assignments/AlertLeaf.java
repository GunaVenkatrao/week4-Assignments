package week4.day1Assignments;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertLeaf {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://leafground.com/alert.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//find the element Simple alert
		
		driver.findElement(By.xpath("(//span[@class='ui-button-text ui-c'])[1]")).click();
		//handle the alert
		Alert sa = driver.switchTo().alert();
		String msg = sa.getText();
		System.out.println("Simple Alert "+msg);
		sa.accept();
				
		
		//find the element Confirm dialog alert
				driver.findElement(By.xpath("(//span[@class='ui-button-text ui-c'])[2]")).click();
				//handle the alert
				Alert ca = driver.switchTo().alert();
				Thread.sleep(2000);				
				//information from the dialog
				String msgca = ca.getText();
				System.out.println("Confirm Dailog "+msgca);
				ca.accept();				
				String text = driver.findElement(By.xpath("//span[@id='result']")).getText();
				System.out.println(text);
				
				//Sweet alert(Simple Dialog)
				driver.findElement(By.xpath("(//span[@class='ui-button-text ui-c'])[3]")).click();
				//handle the alert
				driver.findElement(By.xpath("//span[text()='Dismiss']")).click();
				
				//Sweet Modal Dialog
				driver.findElement(By.xpath("(//span[@class='ui-button-text ui-c'])[5]")).click();
				//handle the alert
				driver.findElement(By.xpath("(//span[@class='ui-icon ui-icon-closethick'])[2]")).click();
				
				//Prompt ALert
				driver.findElement(By.xpath("(//span[@class='ui-button-text ui-c'])[6]")).click();
				//handle the alert
				Alert pa = driver.switchTo().alert();				
				pa.sendKeys("Testleaf");
				//information from the dialog
				String msgpa = pa.getText();
				System.out.println("Confirm Dailog "+msgpa);
				pa.accept();
				
				String textpa = driver.findElement(By.xpath("//span[@id='confirm_result']")).getText();
				System.out.println(textpa);
				
				
				//Sweet alert confirmation
				driver.findElement(By.xpath("//span[text()='Delete']")).click();
				//handle the alert
				String title = driver.getTitle();
				System.out.println(title);
				
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//span[@class='ui-button-text ui-c'])[9]")).click();
				
				//Minimize and Maximize
				driver.findElement(By.xpath("(//span[@class='ui-button-text ui-c'])[8]")).click();
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-minus']")).click();
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-extlink']")).click();
				String titlemin = driver.getTitle();
				System.out.println(titlemin);
				driver.findElement(By.xpath("( //span[@class='ui-icon ui-icon-closethick'])[3]")).click();
				
				driver.close();
	}

}
