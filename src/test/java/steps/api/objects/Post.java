package steps.api.objects;


public class Post {

    public Integer userId;
    public Integer id;
    public String title;
    public String body;

    public Post() {

    }

    public Post (int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
