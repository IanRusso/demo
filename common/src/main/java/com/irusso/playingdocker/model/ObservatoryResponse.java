package com.irusso.playingdocker.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "ObservatoryResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ObservatoryResponse {

    @XmlElement(name = "Observatory")
    private List<Observatory> observatories = new ArrayList<>();

    public List<Observatory> getObservatories() {
        return observatories;
    }

    public void setObservatories(List<Observatory> observatories) {
        this.observatories = observatories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObservatoryResponse that = (ObservatoryResponse) o;
        return Objects.equals(observatories, that.observatories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(observatories);
    }

    @Override
    public String toString() {
        return "ObservatoryResponse{" +
            "observatories=" + observatories +
            '}';
    }
}
