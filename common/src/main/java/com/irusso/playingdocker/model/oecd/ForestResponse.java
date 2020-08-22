package com.irusso.playingdocker.model.oecd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ForestResponse {
    private Header header;
    @JsonProperty("dataSets")
    private DataSet dataSet;
    @JsonProperty("structure")
    private Structure structure;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForestResponse that = (ForestResponse) o;
        return Objects.equals(header, that.header) &&
                Objects.equals(dataSet, that.dataSet) &&
                Objects.equals(structure, that.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, dataSet, structure);
    }

    @Override
    public String toString() {
        return "ForestResponse{" +
                "header=" + header +
                ", dataSet=" + dataSet +
                ", structure=" + structure +
                '}';
    }
}
