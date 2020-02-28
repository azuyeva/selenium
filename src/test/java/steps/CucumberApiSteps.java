package steps;

import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import org.apache.http.HttpResponse;
import org.openqa.selenium.NotFoundException;
import steps.api.RestClient;
import steps.api.Utils;
import steps.api.objects.Post;
import steps.api.objects.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CucumberApiSteps {

    RestClient restClient;
    HttpResponse httpResponse;


    @Wenn("Ich nach Users suche")
    public void ichNachUsersSuche() throws IOException {
        httpResponse = restClient.sendGetRequest("users");
    }

    @Dann("der User mit id {int} hat Name {string}")
    public void derUserMitIdHatTitle(int id, String name) throws Exception {
        List<User> userList = Arrays.asList(Utils.jsonToObject(Utils.httpEntityContentToString(httpResponse.getEntity()), User[].class));

        User foundedUser = userList.stream().filter(user -> user.id == id).findFirst().orElseThrow(() -> new NotFoundException("Kein user mit id " + id  + " gefunden!"));
        if (!foundedUser.name.equals(name)) {
            throw new Exception("Der User mit Id " + id + " hat den Namen " + foundedUser.name + ". Erwartet " + name);
        }
    }

    @Angenommen("ich will JsonPlaceholderApi testen")
    public void ichWillJsonPlaceholderApiTesten() {
        restClient = new RestClient("https://jsonplaceholder.typicode.com/");
    }

    @Wenn("der User mit Id {int} den Kommentar mit Titel {string} und Inhalt {string} erfolgreich postet")
    public void derUserMitIdDenKommentarMitTitelUndInhaltErfolgreichPostet(int userId, String title, String body) throws Exception {

        Post post = new Post(userId, title, body);
        httpResponse = restClient.sendPostRequest("posts", Utils.objectToJson(post));
        if (httpResponse.getStatusLine().getStatusCode() != 201 && !httpResponse.getStatusLine().getReasonPhrase().equals("Created")) {
            throw new Exception("Post Request war nicht erfolgreich, Reason: " + httpResponse.getStatusLine().getReasonPhrase() + ", Status: " + httpResponse.getStatusLine().getStatusCode());
        }
    }

    @Dann("bekommt er im Response den KommentarId zurück")
    public void bekommtErInResponseDenKommentarinhaltZurueck() throws Exception {

        Post post = Utils.jsonToObject(Utils.httpEntityContentToString(httpResponse.getEntity()), Post.class);
        if (post.id == null || post.id == 0) {
            throw new Exception("Der Kommentar hat kein gültigen Id!");
        }
    }

    @Dann("bekommt er im Response die Statusphrase {string}")
    public void bekommtErImResponseStatus(String status) throws Exception {
        if (!httpResponse.getStatusLine().getReasonPhrase().equals("Created")) {
            throw new Exception("Response Status ist nicht wie erwartet! Gefunden: " + httpResponse.getStatusLine().getReasonPhrase() + ", erwartet: " + status);
        }
    }
}
