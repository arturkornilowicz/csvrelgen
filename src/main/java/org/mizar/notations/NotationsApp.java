package org.mizar.notations;

import org.dom4j.*;
import org.mizar.common.*;

public class NotationsApp extends CSVRELGEN
{
    @Override
    protected void action(Element element) {
        try {
            collectDataMap(element,"absolutepatternMMLId");
            printNotation(element);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printNotation(Element pattern) throws Exception {
        Character c;
        if (pattern.getParent().getName().equals(searchingFor)) {
            c = pattern.getParent().element("Pattern-Shaped-Expression").elements().get(0).attributeValue("absolutepatternMMLId").charAt(0);
            for (XMLId d : dataMap.get(c)) {
                if (pattern.getParent().element("Pattern-Shaped-Expression").elements().get(0).attributeValue("absolutepatternMMLId").equals(d.getElement().attributeValue("absolutepatternMMLId"))) {
                    outFile.write(fileName + ","
                            + pattern.attributeValue("xmlid") + ","
                            + d + ","
                            + Relations.SAMEAS + "\n");
                    break;
                }
            }
        }
    }
}
