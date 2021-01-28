package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.Map;

public class getElement {
    /***
     * This method is desired to convert the value of findBy as string coming from the external sheet and retuen an object of
     * By to be used by the user to get the web element with
     * @param findBy
     * @param identifierValue
     * @return
     */
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

    /***
     * This method desired to return the web element according to the value of the findBy and its value coming from the
     * external sheet, this is based on the element name the same like what is written in the sheet
     * @param elementName
     * @return
     */
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