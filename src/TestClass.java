import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//label/strong[contains(text(),'Round trip')]")).click();
		driver.findElement(By.xpath("//input[@name='origin']")).sendKeys("Mumbai, IN - Chatrapati Shivaji Airport (BOM)");
		driver.findElement(By.xpath("//input[@name='destination']")).sendKeys("New Delhi, IN - Indira Gandhi Airport (DEL)");
		driver.findElement(By.cssSelector("#DepartDate")).sendKeys("Sat, 26 Jan, 2019");
		driver.findElement(By.cssSelector("#ReturnDate")).clear();
		driver.findElement(By.cssSelector("#ReturnDate")).sendKeys("Sat, 27 Jan, 2019");
		WebElement adult=driver.findElement(By.cssSelector("#Adults"));
		adult.click();
		Select select=new Select(adult);
		select.selectByVisibleText("2");
		while(!driver.findElement(By.cssSelector("#SearchBtn")).isEnabled()) {
		}
		driver.findElement(By.cssSelector("#SearchBtn")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		for(int i=0;i<=2;i++) {
			try {
				driver.findElement(By.xpath("//div[@data-fromto='BOM_DEL']/nav/ul/li[2]/div/descendant::th[1]")).click();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		for(int i=0;i<=2;i++) {
			try {
				driver.findElement(By.xpath("//div[@data-fromto='DEL_BOM']/nav/ul/li[2]/div/descendant::th[1]")).click();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		while(!driver.findElement(By.xpath("//*[@id=\"flightForm\"]/section[2]/div[3]/div[3]/button")).isEnabled()) {
		System.out.println("Element is not visible");
		}
		JavascriptExecutor script=(JavascriptExecutor)driver;
		script.executeAsyncScript("document.getElementsByClassName(\"booking fRight\")[1].click();");
	}

}
