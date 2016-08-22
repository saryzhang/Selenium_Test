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
		int rows, cols; 
		String[][] userData;
		User_Data usr_data = new User_Data();
		rows=usr_data.get_rows();
		cols=usr_data.get_cols();
	    userData=usr_data.excel_UserData();
	    System.setProperty("webdriver.chrome.driver", "d:\\work\\selenium\\chromedriver.exe");
		for (int i=1;i<rows;i++ ) {
			WebDriver driver = new ChromeDriver();
				url=userData[i][0];
				email=userData[i][1];
				password=userData[i][2];
			
			driver.get(url);
			
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    //(new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>());
		
		    Register_Page register_page = new Register_Page();
			WebElement email_field=register_page.email_field(driver);
			email_field.sendKeys(email);
			WebElement sign_button = register_page.sign_button(driver);
			sign_button.click();
			WebElement password_filed = register_page.password_field(driver);
			password_filed.sendKeys(password);
			WebElement join_button = register_page.join_button(driver);
			join_button.click();
			String msg = register_page.success_field(driver).getText();
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

class User_Data {
		int rows, cols;
		String[][] userData = null;
		Sheet s = null;
		//read user data from excel
		public void excel_read() {
			File f = new File ("D:/Style.xls");
			Workbook w = null;
			try {
				w = Workbook.getWorkbook(f);
			} catch (BiffException | IOException e) {
				e.printStackTrace();
			}
			 s = w.getSheet("user");
		}
		//return the user data rows
		public int get_rows () {
			excel_read();
			rows = s.getRows();
			return rows;
			
		}
		
		//return the user data cols
		public int get_cols() {
			excel_read();
			cols=s.getColumns();
			return cols;
		}
			
		
		public String[][] excel_UserData() {
	
			userData = new String[rows][cols];
			for (int i=1; i<rows;i++) {
				for (int j=0; j<cols; j++) {
					userData[i][j]=s.getCell(j,i).getContents();
		//			System.out.println(userData[i][j]);
				}
				
			}
			return userData;
		}
}

class Register_Page {
	WebElement element = null;
	public WebElement email_field(WebDriver driver) {
		element = driver.findElement(By.id("join-email"));
		return element;
	}
	
	public WebElement sign_button (WebDriver driver) {
		element = driver.findElement(By.className("join-button"));
		return element;
	}
	
	public WebElement password_field (WebDriver driver) {
		element = driver.findElement(By.id("reg-password"));
		return element;
	}
	
	public WebElement join_button (WebDriver driver) {
		element = driver.findElement(By.xpath(".//*[@id='registration-form']/div/input"));
		return element;
	}
	public WebElement success_field (WebDriver driver) {
		element = driver.findElement(By.className("descline"));
		return element;
	}
	
}


