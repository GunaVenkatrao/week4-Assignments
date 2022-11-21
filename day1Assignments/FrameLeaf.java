package week4.day1Assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameLeaf {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://leafground.com/frame.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.switchTo().frame(0);
		WebElement ele = driver.findElement(By.xpath("//button[@id='Click']"));
		ele.click();
		String msg = ele.getText();
		System.out.println(msg);
		//get outside
		driver.switchTo().defaultContent();
		
		//nested frame
		driver.switchTo().frame(2);
		driver.switchTo().frame("frame2");
		WebElement el2 = driver.findElement(By.id("Click"));
		
		el2.click();
		String el2txt = el2.getText();
		System.out.println(el2txt);
		driver.switchTo().defaultContent();
		
		int countsize = driver.findElements(By.tagName("iframe")).size();
		int countnested=0;
		for (int i = 0; i < countsize; i++) {
			
			driver.switchTo().frame(i);
			int size = driver.findElements(By.tagName("iframe")).size();
			driver.switchTo().defaultContent();
			countnested=countnested+size;
			
		}
	      
		int frameSize=countnested+countsize;
		System.out.println("The no frames in this page is "+frameSize);
		driver.close();

	}

}
