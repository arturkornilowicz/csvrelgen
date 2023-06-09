package org.mizar.modes_tree.app;

import lombok.*;
import org.dom4j.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModeDescr {

    Element element;
    String fileName;
    String xmlid;
    String absolutepatternMMLId;
    String modeIntro;
    String spelling;
    String typeKind;
    ModeDescr parent;

    @Override
    public String toString() {
        String result = fileName + " " + xmlid + " " + typeKind + " " + absolutepatternMMLId + " " + modeIntro + " " + spelling + " -> ";
        if (parent != null) {
            result += parent.fileName + " " + parent.xmlid + " " + typeKind + " " + parent.absolutepatternMMLId + " " + parent.modeIntro + " " + parent.spelling;
        }
        return result;
    }

    public String print_is_a() {
        String result = "";
        String sep = ",";
        if (typeKind.equals(TypeKind.STANDARD_TYPE))// && !modeIntro.equals(ModeIntro.SYNONYM))
        {
            if (parent != null) {
                result += fileName + sep + xmlid;
                result += sep + parent.fileName + sep + parent.xmlid + sep + "BROADER";
            }
        }
        return result;
    }

    public String print_mode_synonyms() {
        String result = "";
        String sep = ",";
        if (typeKind.equals(TypeKind.STANDARD_TYPE) && modeIntro.equals(ModeIntro.SYNONYM)) {
            result += fileName + sep + xmlid;
            if (parent != null) {
                result += sep + parent.fileName + sep + parent.xmlid + sep + "SAMEAS";
            }
        }
        return result;
    }

    public ModeDescr(String fileName, Element element) {
        this.fileName = fileName;
        this.element = element;
        this.xmlid = element.element("Mode-Pattern").attributeValue("xmlid");
        this.absolutepatternMMLId = element.element("Mode-Pattern").attributeValue("absolutepatternMMLId");
        this.spelling = element.element("Mode-Pattern").attributeValue("spelling");
        this.typeKind = TypeKind.STANDARD_TYPE;
        this.parent = computeParent();
    }

    public ModeDescr(String absolutepatternMMLId, String typeKind) {
        this.absolutepatternMMLId = absolutepatternMMLId;
        this.typeKind = typeKind;
    }

    ModeDescr computeParent() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ModeDescr)) {
            return false;
        }
        ModeDescr modeDescr = (ModeDescr) obj;
        return this.absolutepatternMMLId.equals(modeDescr.absolutepatternMMLId)
                && this.typeKind.equals(modeDescr.typeKind);
    }
}
