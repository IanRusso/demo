package com.irusso.playingdocker.model.oecd;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Data {
    private List<Integer> attributes;
    private Map<String, List<Object>> observations;

    public List<Integer> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Integer> attributes) {
        this.attributes = attributes;
    }

    public Map<String, List<Object>> getObservations() {
        return observations;
    }

    public void setObservations(Map<String, List<Object>> observations) {
        this.observations = observations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(attributes, data.attributes) &&
            Objects.equals(observations, data.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes, observations);
    }

    @Override
    public String toString() {
        return "Data{" +
            "attributes=" + attributes +
            ", observation=" + observations +
            '}';
    }
}
