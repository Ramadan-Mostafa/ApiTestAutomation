package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.Map;

public class getElement {
    public static By getFindBy(String findBy, String identifierValue) {
        try {
            switch (findBy) {
                case "id":
                    return By.id(identifierValue);
                case "name":
                    return By.name(identifierValue);
                case "linkText":
                    return By.linkText(identifierValue);
                case "className":
                    return By.className(identifierValue);
                case "tagName":
                    return By.tagName(identifierValue);
                case "xpath":
                    return By.xpath(identifierValue);
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return By.cssSelector("");
    }

    public static WebElement getWebElement(String elementName) {
        Map<String, String> selector = ReadExternalData.readObjectRepo(elementName);
        String findByString = null;
        for (Map.Entry<String, String> entry : selector.entrySet()) {
            findByString = entry.getKey();
        }
        String value = selector.get(findByString);
        WebElement element = BasePage.getDriver().findElement(getFindBy(findByString, value));
        return element;
    }
}