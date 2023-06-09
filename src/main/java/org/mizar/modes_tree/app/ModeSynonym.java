package org.mizar.modes_tree.app;

import org.dom4j.Element;

public class ModeSynonym extends ModeDescr {

    public ModeSynonym(String fileName, Element element) {
        super(fileName, element);
        setModeIntro(ModeIntro.SYNONYM);
    }

    @Override
    ModeDescr computeParent() {
        return ModesTreeApp.modes.find0(getElement().element("Pattern-Shaped-Expression").element("Mode-Pattern").attributeValue("absolutepatternMMLId"), TypeKind.STANDARD_TYPE);
    }
}
