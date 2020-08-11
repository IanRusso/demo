package com.irusso.playingdocker.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Observatory {

    @XmlElement(name = "Id")
    private String id;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Resolution")
    private int resolution;
    @XmlElement(name = "StartTime")
    private Date startTime;
    @XmlElement(name = "EndTime")
    private Date endTime;
    @XmlElement(name = "ResourceId")
    private String resourceId;
    @XmlElement(name = "GroupId")
    private String groupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Observatory that = (Observatory) o;
        return resolution == that.resolution &&
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(startTime, that.startTime) &&
            Objects.equals(endTime, that.endTime) &&
            Objects.equals(resourceId, that.resourceId) &&
            Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, resolution, startTime, endTime, resourceId, groupId);
    }

    @Override
    public String toString() {
        return "Observatory{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", resolution=" + resolution +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", resourceId='" + resourceId + '\'' +
            ", groupId='" + groupId + '\'' +
            '}';
    }
}
