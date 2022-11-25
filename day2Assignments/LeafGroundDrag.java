package week4.day2Assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundDrag {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.leafground.com/drag.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder= new Actions(driver);
		
		//Draggable
		WebElement drag = driver.findElement(By.xpath("//div[@id='form:conpnl_header']"));
		
		System.out.println(drag.getLocation());;
		builder.dragAndDropBy(drag, 300, 10);
		System.out.println(drag.getLocation());
		
		//Droppable
		WebElement sour = driver.findElement(By.xpath("//div[@id='form:drag_content']"));
		WebElement dest = driver.findElement(By.xpath("//div[@id='form:drop_content']"));
		builder.dragAndDrop(sour, dest).perform();
		System.out.println(dest.getText());
		System.out.println(dest.getCssValue("color"));
		
		
		//Draggable columns
		WebElement sour1 =driver.findElement(By.xpath("(//span[text()='Name']//parent::th)[1]"));
		WebElement dest1 = driver.findElement(By.xpath("(//span[text()='Quantity']//parent::th)[1]"));
		builder.dragAndDrop(sour1, dest1).perform();
		String msg = driver.findElement(By.xpath("//span[@class='ui-growl-title']")).getText();
		System.out.println(msg+ " message was received and columns are draggable");
		Thread.sleep(1000);
	
		

	}

}
;