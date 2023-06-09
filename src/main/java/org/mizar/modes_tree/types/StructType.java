package org.mizar.modes_tree.types;

import org.dom4j.Element;
import org.mizar.modes_tree.app.*;

public class StructType extends Type {

    public StructType(Element element) {
        super(element);
    }

    @Override
    public ModeDescr find() {
        return ModesTreeApp.modes.find0(getElement().attributeValue("absolutepatternMMLId"), TypeKind.STRUCT_TYPE);
    }
}
