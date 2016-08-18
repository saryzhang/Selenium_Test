package TestGoogle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleHomeTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://google.com");
		WebElement element=driver.findElement(By.name("q"));
		element.sendKeys("git commit error");
		element.submit();
		System.out.println("page tile is:" + driver.getTitle());
		(new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				
				return d.getTitle().toLowerCase().startsWith("git");
			}
			
		});
		System.out.println("page title is: " + driver.getTitle());
	}

}
