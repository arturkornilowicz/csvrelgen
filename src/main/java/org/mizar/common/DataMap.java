package org.mizar.common;

import java.util.*;

public class DataMap extends LinkedHashMap<Character, LinkedList<XMLId>> {

    public DataMap() {
        for (int i = 65; i <= 90; i++) {
            put((char)i,new LinkedList<>());
        }
    }

    public void addEntry(XMLId xmlId, String keyAttr) {
        Character c = xmlId.getElement().attributeValue(keyAttr).charAt(0);
        get(c).add(xmlId);
    }

    public static void main(String[] args) {
        DataMap map = new DataMap();
        System.out.println(map);
    }
}
