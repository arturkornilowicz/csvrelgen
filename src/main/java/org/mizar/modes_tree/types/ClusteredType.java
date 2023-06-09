package org.mizar.modes_tree.types;

import org.dom4j.Element;
import org.mizar.modes_tree.app.*;

public class ClusteredType extends Type {

    public ClusteredType(Element element) {
        super(element);
    }

    @Override
    public ModeDescr find() {
        return ModesTreeApp.modes.find0(getElement().elements().get(0).attributeValue("absolutepatternMMLId"), "XXXX");
    }
}
