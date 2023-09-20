import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.AppiumDriverFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class test01 {
    //AppiumDriverFactory driver = new AppiumDriverFactory();

    @Test
    public void test01() {
        AppiumDriverFactory.init()
                .loadOptions("cap.json")
                .setRemoteAddress("http://localhost:4357")
                .setCapability("platformName", "Android")
                .buildDriver()
                .switchElement(By.id("murat"))
                .click()
                .switchElement(By.id("login"))
                .sendKeys("abuzer")
                .hideKeyboard()
                .openNotifications()
                .lockDevice();

        /*driver.loadOptions("cap.json")
                .setRemoteAddress("http://localhost:4357")
                .asAndroidDriver();*/
    }

}
