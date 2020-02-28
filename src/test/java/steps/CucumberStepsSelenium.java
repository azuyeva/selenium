package steps;

import io.cucumber.java.After;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Cucumber.class)
public class CucumberStepsSelenium {

    SearchPage searchPage;
    WebDriver driver;

    @Angenommen("Ich verwende {string} Browser")
    public void ichVerwendeBrowser(String browser) throws Exception {

        if (browser.contains("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            this.driver = new ChromeDriver();
        } else if (browser.contains("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            this.driver = new FirefoxDriver();
        } else {
            throw new Exception("Browser " + browser + " wird noch nicht unterstützt!");
        }
    }

    @Angenommen("Die Suchmaschine ist {string}")
    public void setSearchEngine(String searchEngine) throws Exception {

        if (searchEngine.contains("Google")) {
            searchPage = new GoogleSearchPage("http://google.de", driver);
        } else {
            throw new Exception("Suchmaschine " + searchEngine + " wird noch nicht unterstützt!");
        }
    }

    @Wenn("Ich nach {string} suche")
    public void setSearchRequest(String searchRequest) {
        searchPage.open();
        searchPage.search(searchRequest);
    }

    @Dann("Bekomme ich valide Ergebnisse")
    public void bekommeIchValideErgebnisse() throws Exception {
        if (!searchPage.validateResults()) {
            throw new Exception("Keine Valide Ergebnisse für die Suchanfrage!");
        }
    }

    @After("@Selenium")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
       // searchPage.close();
    }
}
