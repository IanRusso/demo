package com.irusso.playingdocker.model.oecd;

import java.util.List;
import java.util.Objects;

public class Structure {
    private List<Link> links;
    private String name;
    private String description;
    private Dimension dimensions;
    private Attributes attributes;
    private List<Annotation> annotations;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Structure structure = (Structure) o;
        return Objects.equals(links, structure.links) &&
            Objects.equals(name, structure.name) &&
            Objects.equals(description, structure.description) &&
            Objects.equals(dimensions, structure.dimensions) &&
            Objects.equals(attributes, structure.attributes) &&
            Objects.equals(annotations, structure.annotations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(links, name, description, dimensions, attributes, annotations);
    }

    @Override
    public String toString() {
        return "Structure{" +
            "links=" + links +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", dimension=" + dimensions +
            ", attributes=" + attributes +
            ", annotations=" + annotations +
            '}';
    }
}
