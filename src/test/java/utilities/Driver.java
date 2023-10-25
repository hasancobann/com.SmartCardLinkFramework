package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    /*
     JUnit'de WebDriver objesi TestBase'den geliyordu

     TestNG extends ile baglanma zorunlulugunu ortadan kaldirmak
     ve testi yazanlara daha fazla kontrol imkani vermek icin
     TestBase yerine Driver class'inda static 2 method ile
     driver olusturma ve kapatma islemlerini yapmayi tercih etmistir
     */

    private Driver(){
        // Bu constructor default constructor ile ayni islevi yapan parametresiz constructor'dir
        // buna erisimi kontrol edebilecegimiz icin bu constructor'i olusturduk
    }

    static WebDriver driver; // biz değer atamadığımız için Java default olarak null point eder
    public static WebDriver getDriver(){

        String browser=ConfigReader.getProperty("browser");

        if (driver == null){

            switch (browser){
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver=new SafariDriver();
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
            }

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }

    public static void closeDriver(){
        if (driver != null){
            driver.close();
            driver=null; // driver'a null olarak işaretlemezsek bir sonraki testlerde yeni driver oluşturmaz.
        }
    }

}
