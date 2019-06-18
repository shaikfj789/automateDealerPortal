package logic;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Homepage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class contains methods to perform action on home page.
 *
 * @author sfiroj
 */
public class HomePageHelper {

    Homepage homepage;
    WebDriver driver;
    String url;
    String csvFilePath;
    String searchString;
    FileWriter writer;
    File file;
    PrintWriter pw;


    public HomePageHelper() throws IOException {
        driver = CreateSession.getWebDriver();
        homepage = new Homepage(driver);
        csvFilePath = ".//src//test//java//outputFiles//searchResults.csv";
    }


    /**
     * method to open the mentioned url
     */
    @Given("^user is on portal page \"([^\"]*)\"$")
    public void user_is_on_portal_page(String url) throws Throwable {
        homepage.get(url);
    }

    /**
     * method to search the required string on google search
     *
     * @param stringtoBeSearched value to be searched
     * @throws Throwable
     */
    @And("^searches for \"([^\"]*)\"$")
    public void searches_for_something(String stringtoBeSearched) throws Throwable {
        searchString = stringtoBeSearched;
        homepage.waitForPageToLoad(homepage.getTitle());
        homepage.waitForVisibility(homepage.searchbox);
        homepage.findElement(homepage.searchbox).sendKeys(searchString);
    }


    /**
     * method to click on search button
     *
     * @throws Throwable
     */
    @When("^user clicks on Find Vehicle button$")
    public void user_clicks_on_find_vehicle_button() throws Throwable {
        homepage.waitForVisibility(homepage.searchButton);
        //homepage.clickOnElementUsingJs(homepage.searchButton);;
        homepage.findElement(homepage.searchButton).click();
    }


    /**
     * checking the error message
     *
     * @throws Throwable
     */
    @Then("^the error message \"([^\"]*)\" should be displayed$")
    public void the_error_message_is_displayed(String expectedMessage) throws Throwable {
        WebElement errorMessage = homepage.findElement(homepage.errorMessage);
        String actualMessage = errorMessage.getText();
        homepage.verifyEquals(actualMessage, expectedMessage, "Error Message Not Matching", true, true);

    }

    /**
     * saving the result and information in the csv file present under outputFiles folder
     *
     * @throws Throwable
     */
    @Then("^the result should be displayed and saved in csv file$")
    public void the_result_should_be_displayed_and_saved_in_csv_file() throws Throwable {

        homepage.waitForPageToLoad(homepage.getTitle());
        homepage.waitForVisibility(homepage.searchResult);
        WebElement searchResult = homepage.findElement(homepage.searchResult);
        Boolean isCoverStartDatePresent = homepage.isElementPresent(homepage.coverStart);
        Boolean isCoverEndDatePresent = homepage.isElementPresent(homepage.coverEnd);

        try {

            file = new File(csvFilePath);

            if (!file.exists())
                file.createNewFile();

            //	writer = new FileWriter(csvFilePath);


            pw = new PrintWriter(new File(csvFilePath));
            StringBuilder sb = new StringBuilder();


            //for header
            sb.append("Searched For");
            sb.append("   ,    ");
            sb.append("Result");
            sb.append("   ,    ");
            sb.append("Cover Start Date");
            sb.append("   ,    ");
            sb.append("Cover End Date");
            sb.append('\n');

            // writing the required data to CSV file

            sb.append(searchString);
            sb.append("   ,    ");
            sb.append(searchResult.getText());
            sb.append("   ,    ");
            if (isCoverStartDatePresent) {
                WebElement coverStartDate = homepage.findElement(homepage.coverStart);

                sb.append(coverStartDate.getText());
            } else
                sb.append("");
            sb.append("   ,    ");
            if (isCoverEndDatePresent) {
                WebElement coverEndDate = homepage.findElement(homepage.coverEnd);
                sb.append(coverEndDate.getText());

            } else
                sb.append("");
            sb.append('\n');


            pw.write(sb.toString());

        } catch (Exception e) {
            System.out.println("Error while writing to csv file !!!");
            e.printStackTrace();
        } finally {
            try {
                pw.flush();
                pw.close();
            } catch (Exception e) {

                System.out.println("Error while flushing/closing fileWriter !!!");

                e.printStackTrace();
            }
        }
    }


}
