package com.irusso.playingdocker.model.oecd;

import java.util.Map;
import java.util.Objects;

public class DataSet {
    private String action;
    private Map<String, Data> series;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Data> getSeries() {
        return series;
    }

    public void setSeries(Map<String, Data> series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSet dataSet = (DataSet) o;
        return Objects.equals(action, dataSet.action) &&
            Objects.equals(series, dataSet.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, series);
    }

    @Override
    public String toString() {
        return "DataSet{" +
            "action='" + action + '\'' +
            ", series=" + series +
            '}';
    }
}
