package org.mizar.modes_tree.app;

import org.dom4j.*;
import org.mizar.modes_tree.types.*;

public class ExpandableMode extends ModeDescr {

    public ExpandableMode(String fileName, Element element) {
        super(fileName, element);
        setModeIntro(ModeIntro.EXPANDABLE);
    }

    @Override
    ModeDescr computeParent() {
        Type type;
        if (getElement().element("Expandable-Mode").elements().size() != 0) {
            switch (getElement().element("Expandable-Mode").elements().get(0).getName()) {
                case "Clustered-Type":
                    switch (getElement().element("Expandable-Mode").elements().get(0).elements().get(1).getName()) {
                        case "Standard-Type":
                            type = new StandardType(getElement().element("Expandable-Mode").elements().get(0).elements().get(1));
                            return ModesTreeApp.modes.find0(type.getElement().attributeValue("absolutepatternMMLId"), TypeKind.STANDARD_TYPE);
                        case "Struct-Type":
                            type = new StructType(getElement().element("Expandable-Mode").elements().get(0).elements().get(1));
                            return ModesTreeApp.modes.find0(type.getElement().attributeValue("absolutepatternMMLId"), TypeKind.STRUCT_TYPE);
                    }
                case "Standard-Type":
                    type = new StandardType(getElement().element("Expandable-Mode").elements().get(0));
                    return ModesTreeApp.modes.find0(type.getElement().attributeValue("absolutepatternMMLId"), TypeKind.STANDARD_TYPE);
                case "Struct-Type":
                    type = new StructType(getElement().element("Expandable-Mode").elements().get(0));
                    return ModesTreeApp.modes.find0(type.getElement().attributeValue("absolutepatternMMLId"), TypeKind.STRUCT_TYPE);
            }
        }
        return null;
    }
}
