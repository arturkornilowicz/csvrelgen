package org.mizar.structures;

import org.dom4j.*;
import org.mizar.common.*;

public class StructuresGraphApp extends CSVRELGEN {
    @Override
    protected void action(Element element) {
        try {
            collectDataMap(element.element("Structure-Pattern"),"absolutepatternMMLId");
            printAncestors(element);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printAncestors(Element structure) throws Exception {
        Character c;
        for (Element ancestor: structure.element("Ancestors").elements()) {
            c = ancestor.attributeValue("absolutepatternMMLId").charAt(0);
            for (XMLId d: dataMap.get(c)) {
                if (ancestor.attributeValue("absolutepatternMMLId").equals(d.getElement().attributeValue("absolutepatternMMLId"))) {
                    outFile.write(fileName + ","
                            + structure.element("Structure-Pattern").attributeValue("xmlid") + ","
                            + d + ","
                            + Relations.BROADER + "\n");
                    break;
                }
            }
        }
    }
}
