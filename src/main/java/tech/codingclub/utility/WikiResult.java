package tech.codingclub.utility;

public class WikiResult {
    private String query;
    private String text;
    private String image_url;

    public WikiResult()
    {

    }
    public WikiResult(String query, String text, String image_url) {
        this.query = query;
        this.text = text;
        this.image_url = image_url;
    }
}
