package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

public class IntegrationTest {
    WebDriver browser;
    private String localIndex = "http://localhost:4200/";

    @Before
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jesse\\OneDrive\\Bureaublad\\Chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        browser = new ChromeDriver(options);
    }

    public void Signin() throws InterruptedException {
        browser.findElement(new By.ById("Signin")).click();
        browser.findElement(new By.ById("email")).sendKeys("testing@gmail.com");
        browser.findElement(new By.ById("password")).sendKeys("12345");
        browser.findElement((new By.ById("signin"))).click();
        Thread.sleep(2000);
    }

    @Test
    public void TitleShouldBeSet()
    {
        Setup();
        browser.get(localIndex);
        Assert.assertEquals("FUNPersoonlijkProjectFrontend", browser.getTitle());
        browser.close();
    }

    @Test
    public void NavBarNavigatesTo() throws InterruptedException {
        Setup();
        browser.get(localIndex);
        Signin();
        browser.findElement(new By.ById("projects")).click();
        Assert.assertTrue(browser.getPageSource().contains("Projects"));
        Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/getprojects");
        browser.close();
    }

    @Test
     public void NavbarNavigatesToSigninIfNotLoggedIn() {
        Setup();
        browser.get(localIndex);
        browser.findElement(new By.ById("projects")).click();
        Assert.assertTrue(browser.getPageSource().contains("Welcome back."));
        browser.close();
    }

    @Test
    public void CanMakeNewProject() throws InterruptedException {
        Setup();
        browser.get(localIndex);
        Signin();
        browser.findElement(new By.ById("newproject")).click();
        browser.findElement(new By.ById("name")).sendKeys("project test name new name");
        browser.findElement(new By.ById("description")).sendKeys("new project description added");
        Thread.sleep(1000);
        browser.findElement(new By.ById("submitproject")).click();
        browser.findElement(new By.ById("projects")).click();
        Thread.sleep(500);
        Assert.assertTrue(browser.getPageSource().contains("project test name new name"));
        browser.close();
    }

    @Test
    public void CanNavigateToNewProject() throws InterruptedException {
        Setup();
        browser.get(localIndex);
        Signin();
        browser.findElement(new By.ById("newproject")).click();
        Assert.assertEquals("http://localhost:4200/newproject", browser.getCurrentUrl());
        browser.close();
    }
}
