package org.mizar.labels;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.mizar.common.CSVRELGEN;
import org.mizar.common.XMLId;

import java.io.File;
import java.util.*;

public class LabelsApp extends CSVRELGEN {

    List<XMLId> data;

    @Override
    protected void action(Element element) {}

    private void collectData() { data = getElements(xpath1,keyAttr1); }
    private void collectUse() {
        use = getElements(xpath2,keyAttr2);
    }

    protected void printUsages() throws Exception {
        List<Node> references;
        Element ref;
        int nr;
        for (XMLId u: use) {
            nr = 0;
            references = u.getElement().selectNodes("Local-Reference");
            for (Node node: references) {
                ref = (Element) node;
                nr++;
                for (XMLId d : data) {
                    if (ref.attributeValue("serialnr").equals(d.getElement().attributeValue("serialnr"))) {
                        outFile.write(fileName + "," + ref.attributeValue("xmlid") + "," + d + "," + relation);
//                        if (u.getElement().elements().size() > 0 && u.getElement().elements().get(0).getName().equals("Link")) {
//                            if (nr == 1) {
//                                outFile.write(",LINK");
//                            } else {
//                                outFile.write(",LABEL");
//                            }
//                        } else {
//                            outFile.write(",LABEL");
//                        }
                        outFile.write("\n");
                        break;
                    }
                }
            }
        }
    }
    @Override
    protected void processArticle() throws Exception {
        SAXReader saxBuilder = new SAXReader();
        document = saxBuilder.read(new File(esxFiles + fileName + ".esx"));
        collectData();
        collectUse();
        printUsages();
    }
}