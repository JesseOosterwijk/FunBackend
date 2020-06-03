package Api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class IntegrationTest {
    WebDriver browser;
    private String localIndex = "http://localhost:4200/";

    public void Setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        browser = new ChromeDriver(options);
    }

//    @Test
//    void ShouldBeValid()
//    {
//        Setup();
//    }
}
