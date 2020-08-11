package com.irusso.playingdocker.model.oecd;

import java.util.List;
import java.util.Objects;

public class Observation {
    private String id;
    private String name;
    private List<IdNamePair> values;
    private String role;

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

    public List<IdNamePair> getValues() {
        return values;
    }

    public void setValues(List<IdNamePair> values) {
        this.values = values;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Observation that = (Observation) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(values, that.values) &&
            Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, values, role);
    }

    @Override
    public String toString() {
        return "Observation{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", values=" + values +
            ", role='" + role + '\'' +
            '}';
    }
}
