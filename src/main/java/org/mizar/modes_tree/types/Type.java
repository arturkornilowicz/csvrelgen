package org.mizar.modes_tree.types;

import lombok.*;
import org.dom4j.Element;
import org.mizar.modes_tree.app.*;

@AllArgsConstructor
@Getter
abstract public class Type {

    private Element element;

    abstract public ModeDescr find();

    @Override
    public String toString() {
        return element.getName();
    }
}
