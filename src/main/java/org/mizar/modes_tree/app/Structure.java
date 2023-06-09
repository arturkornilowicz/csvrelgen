package org.mizar.modes_tree.app;

import org.dom4j.Element;

public class Structure extends StructDescr {

    public Structure(String fileName, Element element) {
        super(fileName, element);
        setModeIntro(ModeIntro.STRUCTURE);
    }

    @Override
    ModeDescr computeParent() {
        return null;
    }
}
