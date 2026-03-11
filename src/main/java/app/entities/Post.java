package app.entities;

public class Post {

    private int post_id;
    private String text;
    private String image;

    public Post(int post_id, String text, String image) {
        this.post_id = post_id;
        this.text = text;
        this.image = image;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }
}
