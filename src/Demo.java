package TestGoogle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {

	public static void main(String[] args) {
		testElement test_element = new testElement();
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		WebElement search_input = test_element.search_input(driver);
		search_input.sendKeys("finding job");
		search_input.submit();
		//WebElement search_button=test_element.search_button(driver);
		//search_button.click();
		
		System.out.println("Google page tile:" + driver.getTitle());
		
	}

}

class testElement {
	WebElement serach_input=null;
	WebElement search_button=null;
	public  WebElement search_input (WebDriver driver){
		serach_input = driver.findElement(By.name("q"));
		return serach_input;
		 }
	public  WebElement search_button (WebDriver driver){
		search_button = driver.findElement(By.name("btnK"));
		return search_button;
		 }
	
}