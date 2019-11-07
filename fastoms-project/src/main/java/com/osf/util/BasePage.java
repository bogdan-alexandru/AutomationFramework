package com.osf.util;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	Date date = new Date();

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
	}

	public void click(By locator) {
		getElement(locator).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void click(WebElement element) {
		element.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void scrollToElement(By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void clickElementWithJS(By locator) {
		final JavascriptExecutor executor = (JavascriptExecutor) driver;
//		final WebDriverWait wait = new WebDriverWait(driver, 60);
//		wait.pollingEvery(2, TimeUnit.SECONDS);
//		wait.until(ExpectedConditions.elementToBeClickable(locator));
		executor.executeScript("arguments[0].click();", driver.findElement(locator));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void typeText(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void typeText(WebElement element, String value) {
		element.sendKeys(value);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public List<WebElement> getElements(WebElement element, By locator) {
		return element.findElements(locator);
	}
	
	public WebElement getElementByNameFromList(List<WebElement> elements, String name) {
		WebElement categoryElement = null;
		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(name)) {
				categoryElement = element;
				break;
			}
		}
		return categoryElement;
	}
	
	public void clear(By locator) {
		driver.findElement(locator).clear();
	}

	public void clear(WebElement element) {
		element.clear();
	}

	public void selectOptionFromDropdownByValue(By dropdownSelector, String optionValue) {
		WebElement dropdownElement = driver.findElement(dropdownSelector);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue(optionValue);
	}

	public void selectOptionFromDropdownByText(By dropdownSelector, String optionText) {
		WebElement dropdownElement = driver.findElement(dropdownSelector);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText(optionText);
	}

	public void selectOptionFromDropdownByIndex(By dropdownSelector, int optionIndex) {
		WebElement dropdownElement = driver.findElement(dropdownSelector);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(optionIndex);
		wait(5);
	}

	public WebElement getParentOfElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement parentElement = (WebElement) executor.executeScript("return arguments[0].parentNode;", element);
		return parentElement;
	}

	public boolean verifyElementPresent(By locator) {
		boolean isElementPresent = false;
		try {
			getElement(locator);
			isElementPresent = true;
		} catch (NoSuchElementException e) {
		}
		return isElementPresent;
	}
	
	public void hoverElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).perform();
	}
	
	public void clickElement(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
	}
	
	public void hoverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public void clickElement(WebElement element) {
		Actions action = new Actions(driver);
		action.click(element).perform();
	}
}
