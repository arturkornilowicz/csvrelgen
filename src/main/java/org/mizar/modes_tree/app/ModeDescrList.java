package org.mizar.modes_tree.app;

import java.util.*;

public class ModeDescrList extends LinkedList<ModeDescr> {

    @Override
    public String toString() {
        String result = "";
        for (ModeDescr modeDescr: this) {
            result += modeDescr + "\n";
        }
        return result;
    }

    public String print_is_a() {
        String result = "";
        String p;
        for (ModeDescr modeDescr: this) {
            p = modeDescr.print_is_a();
            if (!p.equals("")) {
                result += p + "\n";
            } else {
//                System.out.println("NO ELEMENT " + modeDescr);
            }
        }
        return result;
    }

    public String print_mode_synonyms() {
        String result = "";
        String p;
        for (ModeDescr modeDescr: this) {
            p = modeDescr.print_mode_synonyms();
            if (!p.equals("")) {
                result += p + "\n";
            }
        }
        return result;
    }

    public ModeDescr find0(String absolutepatternMMLId, String typeKind) {
        int i = indexOf(new ModeDescr(absolutepatternMMLId,typeKind));
        if (i >= 0) {
            return this.get(i);
        }
        throw new RuntimeException("Mode Description " +  typeKind + " " + absolutepatternMMLId + " not found");
    }
}
