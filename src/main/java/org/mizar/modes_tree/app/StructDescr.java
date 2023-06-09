package org.mizar.modes_tree.app;

import lombok.*;
import org.dom4j.*;

@AllArgsConstructor
@Getter
@Setter
public class StructDescr extends ModeDescr {

    public StructDescr(String fileName, Element element) {
        this.fileName = fileName;
        this.element = element;
        this.xmlid = element.element("Structure-Pattern").attributeValue("xmlid");
        this.absolutepatternMMLId = element.element("Structure-Pattern").attributeValue("absolutepatternMMLId");
        this.spelling = element.element("Structure-Pattern").attributeValue("spelling");
        this.typeKind = TypeKind.STRUCT_TYPE;
    }
}
