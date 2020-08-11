package com.irusso.playingdocker.model.oecd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Dimension {
    @JsonProperty("series")
    private List<Series> seriesList;
    @JsonProperty("observation")
    private List<Observation> observations;

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension = (Dimension) o;
        return Objects.equals(seriesList, dimension.seriesList) &&
            Objects.equals(observations, dimension.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesList, observations);
    }

    @Override
    public String toString() {
        return "Dimension{" +
            "seriesList=" + seriesList +
            ", observation=" + observations +
            '}';
    }
}
