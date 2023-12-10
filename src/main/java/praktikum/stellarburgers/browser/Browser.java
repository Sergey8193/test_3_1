package praktikum.stellarburgers.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

import static praktikum.stellarburgers.browser.BrowserType.BY_BROWSER_SYSTEM_PROPERTY;
import static praktikum.stellarburgers.browser.OsType.LINUX;
import static praktikum.stellarburgers.browser.OsType.MAC_OS;
import static praktikum.stellarburgers.browser.OsType.WINDOWS;
import static praktikum.stellarburgers.browser.OsType.UNSUPPORTED_OS;

public class Browser {

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return options;
    }

    private static ChromeOptions getYandexOptions() {
        ChromeOptions options = getChromeOptions();
        switch (getOperatingSystem()) {
            case MAC_OS:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver-mac");
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                break;
            case WINDOWS:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                break;
            case LINUX:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver-linux");
            case UNSUPPORTED_OS: break;
        }
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        return new FirefoxOptions();
    }

    public static WebDriver getDriver(BrowserType browserType) {
        String systemProperty = Objects.equals(System.getProperty("browser"), null)
                ? "CHROME"
                : System.getProperty("browser").toUpperCase();

        BrowserType driverType = Objects.equals(browserType, BY_BROWSER_SYSTEM_PROPERTY)
                ? BrowserType.valueOf(systemProperty)
                : browserType;

        WebDriver driver = getWebDriver(driverType);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getWebDriver(BrowserType driverType) {
        WebDriver driver = null;
        switch(driverType) {
            case EDGE: driver = new EdgeDriver();
                break;
            case CHROME:  driver = new ChromeDriver(getChromeOptions());
                break;
            case YANDEX:  driver = new ChromeDriver(getYandexOptions());
                break;
            case FIREFOX:  driver = new FirefoxDriver(getFirefoxOptions());
        }
        assert driver != null;
        return driver;
    }
    
    public static OsType getOperatingSystem()
    {        
        String osName = System.getProperty("os.name").toLowerCase();
        
        if (osName.contains("win")) { return WINDOWS; }
        else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) { return LINUX; }
        else if (osName.contains("mac")) { return MAC_OS; }
        return UNSUPPORTED_OS;
    }    
}
