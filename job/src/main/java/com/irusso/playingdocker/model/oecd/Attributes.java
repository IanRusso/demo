package com.irusso.playingdocker.model.oecd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Attributes {
    private List<Object> dataSet;
    @JsonProperty("series")
    private List<Series> seriesList;
    @JsonProperty("observation")
    private List<Observation> observations;

    public List<Object> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Object> dataSet) {
        this.dataSet = dataSet;
    }

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
        Attributes that = (Attributes) o;
        return Objects.equals(dataSet, that.dataSet) &&
            Objects.equals(seriesList, that.seriesList) &&
            Objects.equals(observations, that.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataSet, seriesList, observations);
    }

    @Override
    public String toString() {
        return "Attributes{" +
            "dataSet=" + dataSet +
            ", seriesList=" + seriesList +
            ", observations=" + observations +
            '}';
    }
}
