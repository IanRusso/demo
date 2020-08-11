package com.irusso.playingdocker.endpoints;

import java.util.List;
import java.util.Objects;

public class GeneralList<T> {

    private List<T> content;

    public GeneralList(List<T> content) {
        this.content = content;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralList<?> that = (GeneralList<?>) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "GeneralList{" +
            "content=" + content +
            '}';
    }
}
