package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class AppiumDriverFactory {
    private ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>(); ;
    private DesiredCapabilities options;
    private URL remoteAddress;
    DevicePlatform devicePlatform = DevicePlatform.ANDROID;
    private AppiumElement appiumElement;
    private AppiumDriverFactory() {

    }
    private AppiumDriverFactory(DevicePlatform platform) {
        devicePlatform = platform;
    }

    public DevicePlatform getPlatform() {
        return devicePlatform;
    }
    public static AppiumDriverFactory init() {
        return new AppiumDriverFactory();
    }

    boolean isAndroid() {
        return devicePlatform==DevicePlatform.ANDROID;
    }
    private Map<String, Object> createOptionsMap(String optionFile) {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(optionFile);
        Map<String, Object> optionsMap = null;
        try {
            optionsMap = mapper.readValue(jsonFile, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return optionsMap;
    }

    public AppiumDriverFactory setOptions(DesiredCapabilities options) {
        this.options = options;
        return this;
    }

    public AppiumDriverFactory loadOptions(String optionFile) {
        this.options = new DesiredCapabilities(createOptionsMap(optionFile));
        return this;
    }

    public AppiumDriverFactory setCapability(String key, Object value) {
        this.options.setCapability(key, value);
        return this;
    }

    public AppiumDriverFactory setRemoteAddress(String remoteAddress) {
        try {
            this.remoteAddress = new URL(remoteAddress);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    public AppiumDriverFactory clearDriver() {
        if (appiumDriver!=null) {
            appiumDriver.get().quit();
            appiumDriver.set(null);
            appiumDriver.remove();
            appiumElement = null;
        }
        return this;
    }

    public AppiumDriverFactory buildDriver() {
        if (appiumDriver.get()==null) {
            appiumDriver.set(createDriver());
            appiumElement = new AppiumElement(this);
        }
        return this;
    }
    private AppiumDriver createDriver(){
        AppiumDriver _appiumDriver;
        _appiumDriver = new AppiumDriver(remoteAddress, options);
        _appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return _appiumDriver;
    }
    public AppiumDriver getDriver() {
        buildDriver();
        return appiumDriver.get();
    }
    public AndroidDriver asAndroidDriver() {
        return (AndroidDriver)getDriver();
    }
    public IOSDriver asIOSDriver() {
        return (IOSDriver)getDriver();
    }

    public AppiumElement switchElement(By by) {
        this.appiumElement.switchElement(by);
        return this.appiumElement;
    }

    public AppiumDriverFactory hardWait(long milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
        }
        return this;
    }

}
