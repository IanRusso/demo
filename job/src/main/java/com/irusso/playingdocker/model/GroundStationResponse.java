package com.irusso.playingdocker.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "GroundStationResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroundStationResponse {

    @XmlElement(name = "GroundStation")
    private List<GroundStation> groundStations;

    public List<GroundStation> getGroundStations() {
        return groundStations;
    }

    public void setGroundStations(List<GroundStation> groundStations) {
        this.groundStations = groundStations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroundStationResponse that = (GroundStationResponse) o;
        return Objects.equals(groundStations, that.groundStations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groundStations);
    }

    @Override
    public String toString() {
        return "GroundStationResponse{" +
            "groundStations=" + groundStations +
            '}';
    }
}
