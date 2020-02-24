package testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;




public class TestBrowsers {

	
	public static String browser="chrome";
	public static WebDriver driver;
	public static boolean isElementPresent (By by )
	{try {
		driver.findElement((by));
		//System.out.print(driver.findElement((by)).getText());
		return true;
	}catch (Throwable t)
	
	
	{return false;
	}
	}
		
	public static void main(String[] args) throws IOException {
		//System.setProperty("webdriver.chrome.driver", "D:\\selenium-java-3.4.0\\chromedriver.exe");
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if (browser.equals("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if (browser.equals("InternetExplorer")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		//WebDriverWait wait=new WebDriverWait(driver,10);
		Wait <WebDriver> wait=new FluentWait<WebDriver> (driver)
		.withTimeout(Duration.ofSeconds(10))
		.pollingEvery(Duration.ofSeconds(2))
		.withMessage("Time out")
		.ignoring(NoSuchElementException.class);
		driver.get("https://google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q"))).sendKeys("Selenium");
		//wait.until(ExpectedConditions.elementToBeClickable(By.className("RNmpXc"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("btnK"))).submit();
		System.out.print(TestBrowsers.isElementPresent(By.xpath("//html/body/div[7]/div[3]/div[9]/div[1]/div[2]/div/div[4]/div/div/div[2]/div/div/div/div[1]/div/div/div[2]/div[1]/a/div[1]/g-img/img")));
		
		
		//WebElement ele = driver.findElement(By.xpath("//html/body/div[7]/div[3]/div[9]/div[1]/div[2]/div/div[4]/div/div/div[2]/div/div/div/div[1]/div/div/div[2]/div[1]/a/div[1]/g-img/img"));

		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "jpg", new File(".\\screenshot\\ashotimgelement.jpg"));

		//driver.quit();
		//
	}
}
