package org.mizar.usages;

import java.io.*;

import org.dom4j.*;
import org.dom4j.io.*;
import lombok.*;
import org.mizar.common.*;

@Getter
public class Usages extends CSVRELGEN {

    @Override
    protected void action(Element element) {}

    private void collectDataMap(String xpath, String keyAttr) {
        for (Node node : document.selectNodes(xpath)) {
            dataMap.addEntry(new XMLId((Element) node, document.getRootElement().attributeValue("articleid").toLowerCase(),keyAttr),keyAttr);
        }
    }
    private void collectUse() {
        use = getElements(xpath2,keyAttr2);
    }

    protected void printUsages() throws Exception {
        for (XMLId u: use) {
            for (XMLId d: dataMap.get(u.firstChar())) {
                if (u.getElement().attributeValue(u.getKeyAttr()).equals(d.getElement().attributeValue(d.getKeyAttr()))) {
                    outFile.write(u + "," + d + "," + relation + "\n");
                    break;
                }
            }
        }
    }
    @Override
    protected void processArticle() throws Exception {
        SAXReader saxBuilder = new SAXReader();
        document = saxBuilder.read(new File(esxFiles + fileName + ".esx"));
        collectDataMap(xpath1,keyAttr1);
        collectUse();
        printUsages();
    }
}
