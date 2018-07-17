package com.epam.beforeLabTask;

import com.epam.beforeLabTask.pages.MainPage;
import com.epam.beforeLabTask.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;
    private Util util;
    private MainPage mainPage;

    private static final String FIRST_DAY = "20";
    private static final String LAST_DAY = "24";
    private static final String MINSK = "Minsk";
    private static final String MILAN = "Milan";
    private static final String LONDON = "London";
    private static final String childrenAmount = "0";
    private static final String adultAmount = "2";
    private static final String roomAmount = "1";
    private static final int MIN_AMOUNT_OF_PLACES = 3;

    @BeforeClass
    public void setUp() {
        util = new Util();

        System.setProperty("webdriver.chrome.driver", "E:\\EPAM\\QA\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1500, 500));
        driver.get("https://www.booking.com");
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @Test
    public void findPlacesForTwoAdultsInMinskTest(){
        mainPage.fillPlace(MINSK);
        mainPage.fillDate(FIRST_DAY, LAST_DAY);
        mainPage.fillPeopleInfo(roomAmount, adultAmount, childrenAmount);
        mainPage.clickApplyButton();

        WebElement pageTitle = driver.findElement(By.className("sr_header--title"));
        int actualPlacesAmount = util.getFirstNumber(pageTitle.getText());
        Assert.assertTrue(actualPlacesAmount > MIN_AMOUNT_OF_PLACES);
    }

    @Test
    public void findPlacesForTwoAdultsForSevenDaysSinceTodayTest() {
        int arrival = util.getCurrentDay();
        int departure = arrival + 7;
        mainPage.fillPlace(LONDON);
        mainPage.fillDate(String.valueOf(arrival), String.valueOf(departure));
        mainPage.clickApplyButton();

        WebElement pageTitle = driver.findElement(By.className("sr_header--title"));
        int actualPlacesAmount = util.getFirstNumber(pageTitle.getText());
        Assert.assertTrue(actualPlacesAmount > MIN_AMOUNT_OF_PLACES);
    }

    @Test
    public void findPlacesForTwoAdultsNextWeekendTest() {
        int arrival = util.getSaturdayDate();
        int departure = ++arrival;
        mainPage.fillPlace(MILAN);
        mainPage.fillDate(String.valueOf(arrival), String.valueOf(departure));
        mainPage.clickApplyButton();

        WebElement pageTitle = driver.findElement(By.className("sr_header--title"));
        int actualPlacesAmount = util.getFirstNumber(pageTitle.getText());
        Assert.assertTrue(actualPlacesAmount > MIN_AMOUNT_OF_PLACES);
    }

    @AfterMethod
    public void comeBack(){
        driver.navigate().to("https://www.booking.com");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
