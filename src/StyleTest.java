package TestGoogle;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class StyleTest {

	public static void main(String[] args) throws InterruptedException {
		String url, email, password;
		File f = new File ("D:/Style.xls");
		Workbook w = null;
		try {
			w = Workbook.getWorkbook(f);
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		Sheet s = w.getSheet("user");
		int rows = s.getRows();
		int cols = s.getColumns();
//		System.out.println(rows);
//		System.out.println(cols);
		String[][] userData = new String[rows][cols];
		for (int i=1; i<rows;i++) {
			for (int j=0; j<cols; j++) {
				userData[i][j]=s.getCell(j,i).getContents();
//				System.out.println(userData[i][j]);
			}
		}
//		url=userData[1][0]; email=userData[1][1]; password=userData[1][2];
	    System.setProperty("webdriver.chrome.driver", "d:\\work\\selenium\\chromedriver.exe");
		
		for (int i=1;i<rows;i++ ) {
			WebDriver driver = new ChromeDriver();
				url=userData[i][0];
				email=userData[i][1];
				password=userData[i][2];
			
			driver.get(url);
			
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    //(new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>());
		
		
			WebElement email_field=driver.findElement(By.id("join-email"));
			email_field.sendKeys(email);
			WebElement sign_button = driver.findElement(By.className("join-button"));
			sign_button.click();
			WebElement password_filed = driver.findElement(By.id("reg-password"));
			password_filed.sendKeys(password);
			WebElement join_button = driver.findElement(By.xpath(".//*[@id='registration-form']/div/input"));
			join_button.click();
			String msg = driver.findElement(By.className("descline")).getText();
			if (msg.equalsIgnoreCase("thanks for joining")) {
				System.out.println("Account has been created successfully");
			} else {
				System.out.println("Account has not been created successfully");
			}
			driver.quit();
			
			/*Actions act = new Actions(driver);
			WebElement element = driver.findElement(By.linkText("Help"));
			act.contextClick(element);*/
		}
			
	}
	

}
