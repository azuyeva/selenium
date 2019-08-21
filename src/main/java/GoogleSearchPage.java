import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleSearchPage extends SearchPage {

    GoogleSearchPage(String pageURL, WebDriver webDriver) {
        super(pageURL, webDriver);
    }

    @Override
    public void search(String request) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        currentWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        currentWebElement.sendKeys(request);
        currentWebElement.submit();
        currentWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
    }

    public void displayResults(int number) {

        final String resultOutput = currentWebElement.getText();
        System.out.println("Results: " + resultOutput);
        List<WebElement> links = webDriver.findElements(By.xpath("//div[@id='rso']//div[@class='r']/a[1]"));

        if (number > links.size()) {
            number = links.size();
            System.out.println("Maximal " + links.size() + " Ergebnisse auf der Seite. Die nächste Seiten werden noch nicht unterstützt");
        }

        for (int i = 0; i < number; i++) {
            System.out.println(links.get(i).getAttribute("href"));
        }
    }
}
