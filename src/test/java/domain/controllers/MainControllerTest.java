package domain.controllers;

import domain.DevelopmentTest;
import domain.rest.ApiClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.util.Random;

import static org.junit.Assert.assertTrue;

@Component
@RunWith(SpringRunner.class)
public class MainControllerTest extends DevelopmentTest {
    private WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() throws MalformedURLException {
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void singIn() throws InterruptedException {
        driver.get(ApiClient.URL_CLIENT);

        try {
            assertTrue("User is logged", driver.findElement(By.id("singIn")).isDisplayed());
        } catch (Exception ignore) {
            this.threadWait();
            driver.findElement(By.id("singOut")).click();
        }

        this.threadWait();
        assertTrue("User is logged", driver.findElement(By.id("singIn")).isDisplayed());
        driver.findElement(By.id("singIn")).click();

        this.threadWait();
        assertTrue("SingIn modal is not present", driver.findElement(By.id("modalSingIn")).isDisplayed());
        driver.findElement(By.id("userEmail")).click();
        driver.findElement(By.id("userEmail")).sendKeys("marlonmacf@gmail.com");
        driver.findElement(By.id("userPassword")).click();
        driver.findElement(By.id("userPassword")).sendKeys("123456");
        driver.findElement(By.id("singInButton")).click();

        this.threadWait();
        assertTrue("singOut button is not present", driver.findElement(By.id("singOut")).isDisplayed());
        assertTrue("userName label is not present", driver.findElement(By.id("userName")).isDisplayed());
        assertTrue("userName label is not present", driver.findElement(By.id("userName")).getText().equals("Marlon"));
        driver.findElement(By.id("singOut")).click();

        this.threadWait();
        assertTrue("User is logged", driver.findElement(By.id("singIn")).isDisplayed());
    }

    @Test
    public void singUp() throws InterruptedException {
        driver.get(ApiClient.URL_CLIENT);

        try {
            assertTrue("User is logged", driver.findElement(By.id("singUp")).isDisplayed());
        } catch (Exception ignore) {
            this.threadWait();
            driver.findElement(By.id("singOut")).click();
        }

        this.threadWait();
        assertTrue("User is logged", driver.findElement(By.id("singUp")).isDisplayed());
        driver.findElement(By.id("singUp")).click();

        this.threadWait();
        assertTrue("SingUp modal is not present", driver.findElement(By.id("modalSingUp")).isDisplayed());
        driver.findElement(By.id("storeUserName")).click();
        driver.findElement(By.id("storeUserName")).sendKeys("TEST_NAME");
        driver.findElement(By.id("storeUserEmail")).click();
        driver.findElement(By.id("storeUserEmail")).sendKeys(this.randomEmail() + "@gmail.com");
        driver.findElement(By.id("storeUserPassword")).click();
        driver.findElement(By.id("storeUserPassword")).sendKeys("123456");
        driver.findElement(By.id("singUpButton")).click();

        this.threadWait();
        assertTrue("singOut button is not present", driver.findElement(By.id("singOut")).isDisplayed());
        assertTrue("userName label is not present", driver.findElement(By.id("userName")).isDisplayed());
        assertTrue("userName label is not present", driver.findElement(By.id("userName")).getText().equals("TEST_NAME"));
        driver.findElement(By.id("singOut")).click();

        this.threadWait();
        assertTrue("User is logged", driver.findElement(By.id("singUp")).isDisplayed());
    }

    public void threadWait() throws InterruptedException {
        Thread.sleep(1000);
    }

    public String randomEmail() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder email = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            email.append(c);
        }
        return email.toString();
    }
}
