package org.app.william;

public class Entry {
    private String uri;
    private String title;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n" + "Link: " + getUri();
    }
}
