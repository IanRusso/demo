package com.irusso.playingdocker.model.display;

import java.util.Map;
import java.util.Objects;

public class EconomicReport {
    private Map<Integer, Double> yearToOnePercentWealth;
    private Map<Integer, Double> yearToFivePercentWealth;
    private Map<Integer, Double> yearToTenPercentWealth;
    private Map<Integer, Double> yearToPercentHouseholdsIndebted;

    public EconomicReport() {

    }

    public EconomicReport(Map<Integer, Double> wealthInOnePercent,
                          Map<Integer, Double> wealthInFivePercent,
                          Map<Integer, Double> wealthInTenPercent,
                          Map<Integer, Double> indebtedHouseholds
    ) {
        this.yearToOnePercentWealth = wealthInOnePercent;
        this.yearToFivePercentWealth = wealthInFivePercent;
        this.yearToTenPercentWealth = wealthInTenPercent;
        this.yearToPercentHouseholdsIndebted = indebtedHouseholds;
    }

    public Map<Integer, Double> getYearToOnePercentWealth() {
        return yearToOnePercentWealth;
    }

    public void setYearToOnePercentWealth(Map<Integer, Double> yearToOnePercentWealth) {
        this.yearToOnePercentWealth = yearToOnePercentWealth;
    }

    public Map<Integer, Double> getYearToFivePercentWealth() {
        return yearToFivePercentWealth;
    }

    public void setYearToFivePercentWealth(Map<Integer, Double> yearToFivePercentWealth) {
        this.yearToFivePercentWealth = yearToFivePercentWealth;
    }

    public Map<Integer, Double> getYearToTenPercentWealth() {
        return yearToTenPercentWealth;
    }

    public void setYearToTenPercentWealth(Map<Integer, Double> yearToTenPercentWealth) {
        this.yearToTenPercentWealth = yearToTenPercentWealth;
    }

    public Map<Integer, Double> getYearToPercentHouseholdsIndebted() {
        return yearToPercentHouseholdsIndebted;
    }

    public void setYearToPercentHouseholdsIndebted(Map<Integer, Double> yearToPercentHouseholdsIndebted) {
        this.yearToPercentHouseholdsIndebted = yearToPercentHouseholdsIndebted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EconomicReport that = (EconomicReport) o;
        return Objects.equals(yearToOnePercentWealth, that.yearToOnePercentWealth) &&
            Objects.equals(yearToFivePercentWealth, that.yearToFivePercentWealth) &&
            Objects.equals(yearToTenPercentWealth, that.yearToTenPercentWealth) &&
            Objects.equals(yearToPercentHouseholdsIndebted, that.yearToPercentHouseholdsIndebted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearToOnePercentWealth, yearToFivePercentWealth, yearToTenPercentWealth, yearToPercentHouseholdsIndebted);
    }

    @Override
    public String toString() {
        return "EcononomicWellBeing{" +
            "yearToOnePercentWealth=" + yearToOnePercentWealth +
            ", yearToFivePercentWealth=" + yearToFivePercentWealth +
            ", yearToTenPercentWealth=" + yearToTenPercentWealth +
            ", yearToPercentHouseholdsIndebted=" + yearToPercentHouseholdsIndebted +
            '}';
    }
}
