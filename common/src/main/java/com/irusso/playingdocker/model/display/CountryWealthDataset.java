package com.irusso.playingdocker.model.display;

import java.util.Map;
import java.util.Objects;

public class CountryWealthDataset <T> {
    private String variableName;
    private String population;
    private Map<String, T> data;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Map<String, T> getData() {
        return data;
    }

    public void setData(Map<String, T> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryWealthDataset that = (CountryWealthDataset) o;
        return Objects.equals(variableName, that.variableName) &&
            Objects.equals(population, that.population) &&
            Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName, population, data);
    }

    @Override
    public String toString() {
        return "CountryWealthDataset{" +
            "variableName='" + variableName + '\'' +
            ", population='" + population + '\'' +
            ", data=" + data +
            '}';
    }
}
