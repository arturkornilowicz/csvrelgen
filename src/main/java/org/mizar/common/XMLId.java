package org.mizar.common;

import lombok.*;
import org.dom4j.Element;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class XMLId implements Comparable<XMLId> {

    private Element element;
    private String fileName;
    private String keyAttr;

    @Override
    public String toString() {
        return fileName + "," + element.attributeValue("xmlid");
    }


    @Override
    public int hashCode() {
        return Objects.hash(element);
    }

    public Character firstChar() {
        return getElement().attributeValue(keyAttr).charAt(0);
    }

    @Override
    public int compareTo(XMLId o) {
        return element.attributeValue(keyAttr).compareTo(o.getElement().attributeValue(getKeyAttr()));
    }
}
