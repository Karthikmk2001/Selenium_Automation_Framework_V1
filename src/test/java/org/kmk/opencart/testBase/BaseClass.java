package org.kmk.opencart.testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass{

    public static WebDriver driver;//refer extent report manager onTestFailure method for static reason
    public Logger logger; //Log4j
    public Properties properties;

    @BeforeClass(groups = {"Sanity","Regression","Master"})
    @Parameters({"os","browser"})
    public void setUp(String operating_system,String browser) throws IOException {

        //Loading config.properties file
        FileReader file=new FileReader("./src//test//resources//config.properties");
        properties=new Properties();
        properties.load(file);

        logger= LogManager.getLogger(this.getClass());

        //REMOTE
        if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities=new DesiredCapabilities();
            switch (operating_system.toLowerCase())
            {
                case "windows":capabilities.setPlatform(Platform.WIN11);
                break;
                case "mac":capabilities.setPlatform(Platform.MAC);
                break;
                case "linux":capabilities.setPlatform(Platform.LINUX);
                break;
                default:
                    System.out.println("No matching Operating System");
                    return;
            }

            switch(browser.toLowerCase())
            {
                case "chrome":capabilities.setBrowserName("chrome");
                break;
                case "edge":capabilities.setBrowserName("MicrosoftEdge");
                break;
                case "firefox":capabilities.setBrowserName("firefox");
                break;
                default:
                    System.out.println("No matching browser");
                    return;
            }
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }


        if(properties.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (browser)
            {
                case "chrome":driver=new ChromeDriver();
                    break;
                case "edge":driver=new EdgeDriver();
                    break;
                //case "firefox":driver=new FirefoxDriver();
                //break;
                default:
                    System.out.println("Invalid Browser");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity","Regression","Master"})
    public void tearDown()
    {
        driver.quit();
    }

    public String randomString()
    {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber()
    {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric()
    {
        return RandomStringUtils.randomAlphanumeric(10);
    }
    public String captureScreen(String tname)
    {
        String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File source_file=takesScreenshot.getScreenshotAs(OutputType.FILE);

        String target_file_path=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";
        File targetFile=new File(target_file_path);

        source_file.renameTo(targetFile);
        return target_file_path;
    }
}
