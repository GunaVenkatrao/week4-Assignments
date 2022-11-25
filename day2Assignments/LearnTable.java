package week4.day2Assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnTable {
		public static void main(String[] args) throws InterruptedException, IOException {
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver= new ChromeDriver();
			driver.get("https://testleaf.herokuapp.com/pages/table.html");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 List<WebElement> col = driver.findElements(By.tagName("th"));
			 System.out.println("Column count is "+col.size());
			 for (int i = 0; i < col.size(); i++) 
				 
				 {
					 System.out.println(col.get(i).getText());
				 }
			 //get count number of rows
			 	 
			 List<WebElement> row = driver.findElements(By.tagName("tr"));
			 int size = row.size();
				System.out.println("Total number of rows are "+(size-1));
				
				 String text = driver.findElement(By.xpath("(//table[@cellspacing='0'])//tr[3]//td[2]")).getText();
					System.out.println("Progress value of Learn to interact element is "+text);
					Thread.sleep(1000);
					Set<String> st=new TreeSet<String>();
					for(int j=2;j<=size;j++)
					{
					 List<WebElement> progress = driver.findElements(By.xpath("//table//tr["+j+"]//td[2]"));
					 for(int k=0;k<progress.size();k++) {
						 //System.out.println(progress.get(k).getText().replaceAll("[^0-9]", ""));
						 st.add(progress.get(k).getText().replaceAll("%", " ").trim());
						 //st.add(replaceValue.trim());
					 }
					 					
					}

					List<String> lst =new ArrayList<String>(st);
					List<Integer> lstint=new ArrayList<Integer>();
					for (String integer : lst) {
						lstint.add(Integer.valueOf(integer));
					}
					System.out.println(lstint);
					Collections.sort(lstint);
					System.out.println(lstint);
					int input=lstint.get(0);
					driver.findElement(By.xpath("//td/font[contains(text(),'"+input+"')]/following::input")).click();
					File source = driver.getScreenshotAs(OutputType.FILE);
					File dest=new File("./snap/LearnTable.png");
					FileUtils.copyFile(source, dest);
					driver.close();

	}

}
