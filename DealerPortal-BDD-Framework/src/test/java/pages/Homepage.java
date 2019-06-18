package pages;

import framework.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * class to interact with page
 *
 * @author sfiroj
 * <p>
 * This class has web elements of homepage which are used by HomePageHelper
 */
public class Homepage extends CommonMethods {


    public Homepage(WebDriver driver) throws IOException {
        super(driver);

    }


    public By searchbox = By.xpath("//input[@id='vehicleReg']");
    public By searchButton = By.xpath("//span[contains(text(),'Find vehicle')]");
    public By searchResult = By.xpath("//div[@class='result']");
    public By coverStart = By.cssSelector("#page-container .resultDate-bold:nth-of-type(2) .resultDate");
    public By coverEnd = By.cssSelector("#page-container .resultDate-bold:nth-of-type(3) .resultDate");
    public By errorMessage = By.xpath("//div[@class='error-required']");


}
