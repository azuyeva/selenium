package steps;

import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import org.apache.http.HttpEntity;
import org.openqa.selenium.NotFoundException;
import steps.api.RestClient;
import steps.api.Utils;
import steps.api.objects.User;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class CucumberApiSteps {

    RestClient restClient;
    HttpEntity responseEntity;


    @Wenn("Ich nach Users suche")
    public void ichNachUsersSuche() throws IOException {
        responseEntity = restClient.sendGetRequest("users");
    }

    @Dann("der User mit id {int} hat Name {string}")
    public void derUserMitIdHatTitle(int id, String name) throws Exception {
        List<User> userList = Arrays.asList(Utils.jsonToObject(Utils.httpEntityToString(responseEntity), User[].class));

        User foundedUser = userList.stream().filter(user -> user.id == id).findFirst().orElseThrow(() -> new NotFoundException("Kein user mit id " + id  + " gefunden!"));
        if (!foundedUser.name.equals(name)) {
            throw new Exception("Der User mit Id " + id + " hat die Name " + foundedUser.name + ". Erwartet " + name);
        }
    }

    @Angenommen("ich will JsonPlaceholderApi testen")
    public void ichWillJsonPlaceholderApiTesten() {
        restClient = new RestClient("https://jsonplaceholder.typicode.com/");
    }
}
