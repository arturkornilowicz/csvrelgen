package org.mizar.modes_tree.app;

import org.dom4j.*;
import org.mizar.common.CSVRELGEN;

public class ModesTreeApp extends CSVRELGEN {
    public static ModeDescrList modes = new ModeDescrList();

    @Override
    public void action(Element element) {
        collectModes(element);
    }

    private void collectModes(Element element) {
        switch (element.getName()) {
            case "Mode-Definition":
                if (element.elements().get(2).getName().equals("Standard-Mode")) {
                    modes.add(new StandardMode(fileName, element));
                } else if (element.elements().get(2).getName().equals("Expandable-Mode")) {
                    modes.add(new ExpandableMode(fileName, element));
                } else {
                    throw new RuntimeException("Unknown Mode Pattern");
                }
                break;
            case "Mode-Synonym":
                modes.add(new ModeSynonym(fileName, element));
                break;
            case "Structure-Definition":
                modes.add(new Structure(fileName, element));
                break;
            default:
                throw new RuntimeException("UNKNOWN CASE");
        }
    }

    protected void processArticles() throws Exception {
        super.processArticles();
        outFile.write(modes.print_is_a());
    }
}
