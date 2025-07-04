package ru.bull.pastebin.dto;

public class PostResponse {
    private String link;

    public PostResponse(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
