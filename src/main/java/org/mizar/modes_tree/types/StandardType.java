package org.mizar.modes_tree.types;

import org.dom4j.Element;
import org.mizar.modes_tree.app.*;

public class StandardType extends Type {

    public StandardType(Element element) {
        super(element);
    }

    @Override
    public ModeDescr find() {
        return ModesTreeApp.modes.find0(getElement().attributeValue("absolutepatternMMLId"), TypeKind.STANDARD_TYPE);
    }
}
