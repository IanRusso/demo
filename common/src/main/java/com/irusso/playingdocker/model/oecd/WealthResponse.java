package com.irusso.playingdocker.model.oecd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class WealthResponse {
    @JsonProperty("header")
    private Header header;
    @JsonProperty("dataSets")
    private List<DataSet> dataSets;
    @JsonProperty("structure")
    private Structure structure;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<DataSet> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<DataSet> dataSets) {
        this.dataSets = dataSets;
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
        WealthResponse that = (WealthResponse) o;
        return Objects.equals(header, that.header) &&
            Objects.equals(dataSets, that.dataSets) &&
            Objects.equals(structure, that.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, dataSets, structure);
    }

    @Override
    public String toString() {
        return "WealthResponse{" +
            "header=" + header +
            ", dataSets=" + dataSets +
            ", structure=" + structure +
            '}';
    }
}
