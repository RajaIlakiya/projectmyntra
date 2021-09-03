package week4.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
//		1) Open https://www.myntra.com/
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
//		2) Mouse hover on MeN 
		WebElement men=driver.findElement(By.xpath("//a[text()='Men']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(men).perform();
		
//		3) Click Jackets 
driver.findElement(By.xpath("//a[text()='Jackets']")).click();

//4) Find the total count of item 

String jacketCount=driver.findElement(By.xpath("//span[@class='title-count']")).getText();
//String replaceAll=jacketCount.replaceAll("[^0-9]" , "");
String str = jacketCount.replaceAll("[^0-9]", "");

int count1=Integer.parseInt(str);
System.out.println("total count of item: " + count1);


//5) Validate the sum of categories count matches

String num1=driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
String str1 = num1.replaceAll("[^0-9]", "");
int count=Integer.parseInt(str1);
System.out.println("total count of item: " + count);


String num2=driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
String str2 = num2.replaceAll("[^0-9]", "");
int count2=Integer.parseInt(str2);
System.out.println("total count of item: " + count2);
int sum=count+count2;


System.out.println("sum of categories: " + sum);


//6) Check jackets

if( sum == count1)
{
	System.out.println("Matches Equal to the jackets count");
}
else
	System.out.println("Matches not Equal to the jackets count");

//7) Click + More option under BRAND	

driver.findElement(By.xpath("//div[@class='brand-more']")).click();

//8) Type Duke and click checkbox
WebElement text=driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']"));
text.sendKeys("Duke");
Thread.sleep(2000);
driver.findElement(By.xpath("//label[@class=' common-customCheckbox']")).click();
Thread.sleep(5000);




//9) Close the pop-up x
driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();

//10) Confirm all the Coats are of brand Duke
//Hint : use List 
List<String> list=new ArrayList<String>();
String coatBrand=driver.findElement(By.xpath("//h3[text()='Duke']")).getText();
System.out.println(coatBrand);
list.add(coatBrand);
if(list.contains("Duke"))
{
	System.out.println("Yes,Coats brand were Duke");
}
else
{
	System.out.println("No,Coats brand were not Duke");
}
//11) Sort by Better Discount
driver.findElement(By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']")).click();
driver.findElement(By.xpath("//label[text()='Better Discount']")).click();

//12) Find the price of first displayed item

String price=driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).getText();

System.out.println("Jacket price of first displayed item: " +price);

//Click on the first product
driver.findElement(By.xpath("(//li[@class='product-base'])[1]//a")).click();
Set<String> windowHandleSet= driver.getWindowHandles();
List<String> windowHandleList=new ArrayList<String>(windowHandleSet);
driver.switchTo().window(windowHandleList.get(1));

//Get the number of windows
System.out.println("Number of windows: " + windowHandleList.size());

//13) Take a screen shot
Thread.sleep(2000);
File src1=driver.getScreenshotAs(OutputType.FILE);
File dst=new File("./snaps/jacket.jpg");
FileUtils.copyFile(src1, dst);	
System.out.println("Screenshot page title: " + driver.getTitle());


//14) Click on WishList Now
driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();

//get back to main window
driver.switchTo().window(windowHandleList.get(0));
System.out.println("Back to home page: " + driver.getTitle());

//15) Close Browser
//driver.switchTo().defaultContent();
//driver.switchTo().parentFrame();
driver.close();
driver.quit();
	}

}
