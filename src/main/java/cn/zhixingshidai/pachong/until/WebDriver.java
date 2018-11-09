package cn.zhixingshidai.pachong.until;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriver {

    private static ChromeDriver chromeDriver = null;

    static {
       /* String driver = null;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            driver = "geckodriver.exe";
        } else {
            driver = "geckodriver";
        }*/
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeDriver = new ChromeDriver(chromeOptions);
    }

    public static ChromeDriver getChromeDriver(){
        return chromeDriver;
    }
}
