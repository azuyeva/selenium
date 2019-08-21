import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class SearchPage {

    private String url;
    WebDriver webDriver;
    WebElement currentWebElement;

    SearchPage(String pageURL, WebDriver webDriver) {
        this.url = pageURL;
        this.webDriver = webDriver;
    }

    void open() {
        webDriver.get(url);
        webDriver.manage().window().maximize();
    }

    public abstract void search(String request);

    void close() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    public abstract void displayResults(int number);
}
