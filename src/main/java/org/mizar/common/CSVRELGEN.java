package org.mizar.common;

import java.util.*;
import java.io.*;
import org.dom4j.*;
import org.dom4j.io.*;
import org.mizar.labels.LabelsApp;
import org.mizar.usages.Usages;
import org.mizar.modes_tree.app.ModesTreeApp;
import org.mizar.notations.NotationsApp;
import org.mizar.structures.StructuresGraphApp;

abstract public class CSVRELGEN {
    protected String esxFiles;
    protected String mmllar;
    protected Document document;

    protected String xpath1, xpath2;
    protected String keyAttr1, keyAttr2;
    protected String relation;

    protected String fileName;
    protected String searchingFor;
    protected FileWriter outFile;
    protected DataMap dataMap;
    protected List<XMLId> use;

    public CSVRELGEN() {
        this.dataMap = new DataMap();
        this.use = new LinkedList<>();
    }

    abstract protected void action(Element element);

    protected void collectDataMap(Element element, String keyAttr) {
        dataMap.addEntry(new XMLId(element, fileName, keyAttr),keyAttr);
    }

    protected List<XMLId> getElements(String xpath, String keyAttr) {
        List<XMLId> result = new LinkedList<>();
        for (Node node : document.selectNodes(xpath)) {
            result.add(new XMLId((Element) node, document.getRootElement().attributeValue("articleid").toLowerCase(),keyAttr));
        }
        return result;
    }
    protected void processArticle() throws Exception {
        SAXReader saxBuilder = new SAXReader();
        document = saxBuilder.read(new File(esxFiles + fileName + ".esx"));
        fileName = document.getRootElement().attributeValue("articleid").toLowerCase();
        for (Node node: document.selectNodes(xpath1)) {
            action((Element) node);
        }
    }

    protected void processArticles() throws Exception {
        Scanner scanner = new Scanner(new File(mmllar));
        int nr = 0;
        use.clear();
        while (scanner.hasNextLine()) {
            fileName = scanner.nextLine();
            System.out.println("Processing " + (++nr) + " article : " + fileName + " ...");
            processArticle();
        }
        scanner.close();
    }

    public static void main( String[] args ) throws Exception {
        CSVRELGEN app = null;
        try {
            switch (args[0]) {
                case Task.LABELS:
                    app = new LabelsApp();
                    app.keyAttr1 = "serialnr";
                    app.xpath2 = "//Straightforward-Justification | //Scheme-Justification";
                    app.relation = args[5];
                    break;
                case Task.GRAPH_OF_STRUCTURES:
                    app = new StructuresGraphApp();
                    break;
                case Task.NOTATIONS:
                    app = new NotationsApp();
                    app.searchingFor = args[5];
                    break;
                case Task.TREE_OF_MODES:
                    app = new ModesTreeApp();
                    break;
                case Task.USAGES:
                    app = new Usages();
                    app.keyAttr1 = args[5];
                    app.xpath2 = args[6];
                    app.keyAttr2 = args[7];
                    app.relation = args[8];
                    break;
                default:
                    System.out.println("Unknown Task");
            }
            app.mmllar = args[1];
            app.esxFiles = args[2];
            app.outFile = new FileWriter(args[3]);
            app.xpath1 = args[4];
            app.processArticles();
        } catch (Exception ex1) {
            ex1.printStackTrace();
        } finally {
            System.out.println("Closing file");
            app.outFile.close();
        }
    }
}
