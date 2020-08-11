package com.irusso.playingdocker.model.oecd;

import java.util.Objects;

public class Sender {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sender sender = (Sender) o;
        return Objects.equals(id, sender.id) &&
            Objects.equals(name, sender.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Sender{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
