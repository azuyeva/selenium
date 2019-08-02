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
        String searchEngine = args.length > 2 ? args[2] : "google.de";

        WebDriver driver = null;
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://" + searchEngine);
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
            element.sendKeys(searchRequest);
            element.submit();
            WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
            final String resultOutput = results.getText();
            System.out.println("Results: " + resultOutput);
            List<WebElement> links = driver.findElements(By.xpath("//div[@id='rso']//div[@class='r']/a[1]"));

            if (number > links.size()) {
                number = links.size();
                System.out.println("Maximal " + links.size() + " Ergebnisse auf der Seite. Die nächste Seiten werden noch nicht unterstützt");
            }

            for (int i = 0; i < number; i++) {
                System.out.println(links.get(i).getAttribute("href"));
            }

            driver.quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (driver != null) {
                driver.quit();
            }
        }
    }
}