package com.irusso.playingdocker.model.oecd;

import java.util.Objects;

public class Annotation {
    private String title;
    private String uri;
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Annotation that = (Annotation) o;
        return Objects.equals(title, that.title) &&
            Objects.equals(uri, that.uri) &&
            Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, uri, text);
    }

    @Override
    public String toString() {
        return "Annotation{" +
            "title='" + title + '\'' +
            ", uri='" + uri + '\'' +
            ", text='" + text + '\'' +
            '}';
    }
}
