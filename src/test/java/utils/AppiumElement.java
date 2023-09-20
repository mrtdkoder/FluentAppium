package utils;

import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.ScriptKey;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppiumElement {
    private AppiumDriverFactory _appiumDriverFactory;
    private WebElement element;
    private WebDriverWait wait;
    public AppiumElement(AppiumDriverFactory appiumDriver) {
        _appiumDriverFactory = appiumDriver;
        wait = new WebDriverWait(appiumDriver.getDriver(), Duration.ofSeconds(10));
    }

    public AppiumElement switchElement(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        if (_appiumDriverFactory.isAndroid()) {
            element = _appiumDriverFactory.asAndroidDriver().findElement(by);
        } else {
            element = _appiumDriverFactory.asIOSDriver().findElement(by);
        }
        return this;
    }

    public AppiumElement hardWait(long milisec) {
        _appiumDriverFactory.hardWait(milisec);
        return this;
    }

    /*=== phone operations ===*/
    public AppiumElement KeyPress(KeyEvent keyEvent) {
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().pressKey(keyEvent);
        } else {
            System.out.println("pressKey is not supported");
        }
        return this;
    }

    public boolean isAppInstalled(String appName){
        boolean r = false;
        if (_appiumDriverFactory.isAndroid()) {
            r = _appiumDriverFactory.asAndroidDriver().isAppInstalled(appName);
        } else {
            r = _appiumDriverFactory.asIOSDriver().isAppInstalled(appName);
        }
        return r;
    }

    public AppiumElement activateApp(String appName){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().activateApp(appName);
        } else {
            _appiumDriverFactory.asIOSDriver().activateApp(appName);
        }
        return this;
    }

    public AppiumElement terminateApp(String appName){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().terminateApp(appName);
        } else {
            _appiumDriverFactory.asIOSDriver().terminateApp(appName);
        }
        return this;
    }

    public boolean isKeyboardShown(String appName){
        boolean r = false;
        if (_appiumDriverFactory.isAndroid()) {
            r = _appiumDriverFactory.asAndroidDriver().isKeyboardShown();
        } else {
            r = _appiumDriverFactory.asIOSDriver().isKeyboardShown();
        }
        return r;
    }

    public AppiumElement hideKeyboard(){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().hideKeyboard();
        } else {
            _appiumDriverFactory.asIOSDriver().hideKeyboard();
        }
        return this;
    }

    public AppiumElement removeApp(String appName){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().removeApp(appName);
        } else {
            _appiumDriverFactory.asIOSDriver().removeApp(appName);
        }
        return this;
    }

    public AppiumElement installApp(String appPath){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().installApp(appPath);
        } else {
            _appiumDriverFactory.asIOSDriver().installApp(appPath);
        }
        return this;
    }

    public AppiumElement runAppInBackground(String appName, Duration duration){
        this.activateApp(appName);
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().runAppInBackground(duration);
        } else {
            _appiumDriverFactory.asIOSDriver().runAppInBackground(duration);
        }
        return this;
    }

    public boolean isDeviceLocked(){
        boolean r = false;
        if (_appiumDriverFactory.isAndroid()) {
            r = _appiumDriverFactory.asAndroidDriver().isDeviceLocked();
        } else {
            r = _appiumDriverFactory.asIOSDriver().isDeviceLocked();
        }
        return r;
    }

    public AppiumElement lockDevice(){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().lockDevice();
        } else {
            _appiumDriverFactory.asIOSDriver().lockDevice();
        }
        return this;
    }

    public AppiumElement unlockDevice(){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().unlockDevice();
        } else {
            _appiumDriverFactory.asIOSDriver().unlockDevice();
        }
        return this;
    }

    public AppiumElement unpinDevice(ScriptKey pin){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().unpin(pin);
        } else {
            _appiumDriverFactory.asIOSDriver().unpin(pin);
        }
        return this;
    }

    public AppiumElement rotateDevice(ScreenOrientation screenOrientation){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().rotate(screenOrientation);
        } else {
            _appiumDriverFactory.asIOSDriver().rotate(screenOrientation);
        }
        return this;
    }

    public AppiumElement openNotifications(){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().openNotifications();
        } else {
            System.out.println("openNotifications() is not supported");
        }
        return this;
    }

    public AppiumElement toggleWifi(){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().toggleWifi();
        } else {
            System.out.println("toggleWifi() is not supported");
        }
        return this;
    }
    public AppiumElement toggleData(){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().toggleData();
        } else {
            System.out.println("toggleData() is not supported");
        }
        return this;
    }

    public AppiumElement toggleLocationServices(){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().toggleLocationServices();
        } else {
            System.out.println("toggleLocationServices() is not supported");
        }
        return this;
    }

    public AppiumElement toggleAirplaneMode(){
        if (_appiumDriverFactory.isAndroid()) {
            _appiumDriverFactory.asAndroidDriver().toggleAirplaneMode();
        } else {
            System.out.println("toggleLocationServices() is not supported");
        }
        return this;
    }
    /*=== element operations ===*/

    public AppiumElement click() {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public AppiumElement clear() {
        wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
        return this;
    }

    public AppiumElement sendKeys(String keys) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(keys);
        return this;
    }

    public String getText() {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }



}
