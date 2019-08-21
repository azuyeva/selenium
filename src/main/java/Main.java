import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            throw new Exception("Gebe, bitte, einen Suchbergriff ein!");
        }

        final String searchRequest = args[0];
        int number = args.length > 1 ? Integer.parseInt(args[1]) : 3;
        //String searchEngine = args.length > 2 ? args[2] : "google.de";

        //TODO HTML Unit driver? Or select driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        //TODO instance for other search engines
        SearchPage searchPage = new GoogleSearchPage("http://google.de", new ChromeDriver());

        try {
            searchPage.open();
            searchPage.search(searchRequest);
            searchPage.displayResults(number);
            searchPage.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            searchPage.close();
        }
    }
}