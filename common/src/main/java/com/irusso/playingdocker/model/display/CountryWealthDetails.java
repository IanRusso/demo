package com.irusso.playingdocker.model.display;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CountryWealthDetails {
    private String countryName;
    private List<CountryWealthDataset> datasets = new ArrayList<>();

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<CountryWealthDataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<CountryWealthDataset> datasets) {
        this.datasets = datasets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryWealthDetails that = (CountryWealthDetails) o;
        return Objects.equals(countryName, that.countryName) &&
            Objects.equals(datasets, that.datasets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, datasets);
    }

    @Override
    public String toString() {
        return "CountryWealthDetails{" +
            "countryName='" + countryName + '\'' +
            ", datasets=" + datasets +
            '}';
    }
}
