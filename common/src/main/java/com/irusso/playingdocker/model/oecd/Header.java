package com.irusso.playingdocker.model.oecd;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Header {
    private String id;
    private boolean test;
    private Date prepared;
    private Sender sender;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public Date getPrepared() {
        return prepared;
    }

    public void setPrepared(Date prepared) {
        this.prepared = prepared;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Header header = (Header) o;
        return test == header.test &&
            Objects.equals(id, header.id) &&
            Objects.equals(prepared, header.prepared) &&
            Objects.equals(sender, header.sender) &&
            Objects.equals(links, header.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, test, prepared, sender, links);
    }

    @Override
    public String toString() {
        return "Header{" +
            "id='" + id + '\'' +
            ", test=" + test +
            ", prepared=" + prepared +
            ", sender=" + sender +
            ", links=" + links +
            '}';
    }
}
