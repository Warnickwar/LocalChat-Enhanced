package org.Warnickwar.localchatenhanced.utils;

import java.util.Comparator;

public class LocalPriorityComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof IChatModifierObject co1 && o2 instanceof IChatModifierObject co2) {
            return Integer.compare(co1.getPriority().toInt(), co2.getPriority().toInt());
        }
        return 0;
    }
}
