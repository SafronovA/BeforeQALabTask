package com.epam.beforeLabTask.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "ss")
    private WebElement place;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[2]/div/div[2]/div/div/div/div[1]")
    private WebElement checkInDate;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[2]/div/div[3]/div/div/div/div[1]")
    private WebElement checkOutDate;

    @FindBy(xpath = "//*[@id=\"xp__guests__toggle\"]/span")
    private WebElement selectPeopleAmount;

    @FindBy(xpath = "//*[@id=\"frm\"]/div[1]/div[4]/div[2]/button")
    private WebElement applyButton;

    public void fillPlace(String placeStr) {
        place.sendKeys(placeStr);
    }

    public void fillDate(String arrivalDate, String departureDate) {

        checkInDate.click();
        WebElement dateFormArrival = driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]"));
        List<WebElement> checkInDateColumns = dateFormArrival.findElements(By.xpath("//span[@class='c2-day-inner']"));

        Iterator<WebElement> iterIn = checkInDateColumns.iterator();
        while (iterIn.hasNext()) {
            WebElement currentElem = iterIn.next();
            if (currentElem.getAttribute("innerHTML").contains(arrivalDate)){
                currentElem.click();
                break;
            }
        }

        checkOutDate.click();
        WebElement dateFormDeparture = driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div/div[3]/div/div/div/div[2]/div[2]"));
        List<WebElement> checkOutDateColumns = dateFormDeparture.findElements(By.tagName("td"));

        Iterator<WebElement> iterOut = checkOutDateColumns.iterator();
        while (iterOut.hasNext()) {
            WebElement currentElem = iterOut.next();
            if (currentElem.getText().equals(departureDate)){
                currentElem.click();
                break;
            }
        }
    }


    public void fillPeopleInfo(String roomAmount, String adultAmount, String childrenAmount) {

        selectPeopleAmount.click();

        Select roomElement = new Select(driver.findElement(By.id("no_rooms")));
        roomElement.selectByVisibleText(roomAmount);

        Select adultElement = new Select(driver.findElement(By.id("group_adults")));
        adultElement.selectByVisibleText(adultAmount);

        Select childElement = new Select(driver.findElement(By.id("group_children")));
        childElement.selectByVisibleText(childrenAmount);
    }

    public void clickApplyButton() {
        applyButton.click();
    }
}
