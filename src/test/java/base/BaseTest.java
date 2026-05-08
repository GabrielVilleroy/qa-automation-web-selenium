package base;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {

        if(driver != null) {

            driver.quit();
        }
    }
    public void tirarPrint(String nomeArquivo) {

        File screenshot =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        try {

            File pasta =
                    new File("screenshots");

            if(!pasta.exists()) {

                pasta.mkdir();
            }

            Files.copy(
                    screenshot.toPath(),
                    new File(
                            "screenshots/"
                                    + nomeArquivo
                                    + ".png"
                    ).toPath()
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
