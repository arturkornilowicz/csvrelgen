package org.mizar.modes_tree.app;

import org.dom4j.*;
import org.mizar.modes_tree.types.*;

public class StandardMode extends ModeDescr {

    public StandardMode(String fileName, Element element) {
        super(fileName, element);
        setModeIntro(ModeIntro.STANDARD);
    }

    @Override
    ModeDescr computeParent() {
        Type type;
        if (getElement().element("Standard-Mode").elements().size() != 0) {
            if (getElement().element("Standard-Mode").elements().get(0).getName().equals("Type-Specification")) {
                switch (getElement().element("Standard-Mode").elements().get(0).elements().get(0).getName()) {
                    case "Clustered-Type":
                        switch (getElement().element("Standard-Mode").elements().get(0).elements().get(0).elements().get(1).getName()) {
                            case "Standard-Type":
                                type = new StandardType(getElement().element("Standard-Mode").elements().get(0).elements().get(0).elements().get(1));
                                return ModesTreeApp.modes.find0(type.getElement().attributeValue("absolutepatternMMLId"), TypeKind.STANDARD_TYPE);
                            case "Struct-Type":
                                type = new StructType(getElement().element("Standard-Mode").elements().get(0).elements().get(0).elements().get(1));
                                return ModesTreeApp.modes.find0(type.getElement().attributeValue("absolutepatternMMLId"), TypeKind.STRUCT_TYPE);
                        }
                    case "Standard-Type":
                        type = new StandardType(getElement().element("Standard-Mode").elements().get(0).elements().get(0));
                        return ModesTreeApp.modes.find0(type.getElement().attributeValue("absolutepatternMMLId"), TypeKind.STANDARD_TYPE);
                    case "Struct-Type":
                        type = new StructType(getElement().element("Standard-Mode").elements().get(0).elements().get(0));
                        return ModesTreeApp.modes.find0(type.getElement().attributeValue("absolutepatternMMLId"), TypeKind.STRUCT_TYPE);
                }
            } else {
                if (getElement().element("Mode-Pattern").attributeValue("absoluteorigpatternMMLId") != null) {
                    return ModesTreeApp.modes.find0(getElement().element("Mode-Pattern").attributeValue("absoluteorigpatternMMLId"), TypeKind.STANDARD_TYPE);
                } else {
                    return ModesTreeApp.modes.find0("HIDDEN:1", TypeKind.STANDARD_TYPE);
                }
            }
        }
        return null;
    }
}
