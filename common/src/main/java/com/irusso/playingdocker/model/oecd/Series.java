package com.irusso.playingdocker.model.oecd;

import java.util.List;
import java.util.Objects;

public class Series {
    private long keyPosition;
    private String id;
    private String name;
    private List<IdNamePair> values;

    public long getKeyPosition() {
        return keyPosition;
    }

    public void setKeyPosition(long keyPosition) {
        this.keyPosition = keyPosition;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return keyPosition == series.keyPosition &&
            Objects.equals(id, series.id) &&
            Objects.equals(name, series.name) &&
            Objects.equals(values, series.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyPosition, id, name, values);
    }

    @Override
    public String toString() {
        return "Series{" +
            "keyPosition=" + keyPosition +
            ", id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", values=" + values +
            '}';
    }
}
