package com.irusso.playingdocker.model.oecd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Link {

    @JsonProperty("href")
    private String url;
    @JsonProperty("rel")
    private String rel;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(url, link.url) &&
            Objects.equals(rel, link.rel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, rel);
    }

    @Override
    public String toString() {
        return "Link{" +
            "url='" + url + '\'' +
            ", rel='" + rel + '\'' +
            '}';
    }
}
