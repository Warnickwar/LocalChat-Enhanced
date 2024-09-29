package org.Warnickwar.localchatenhanced.utils;

public enum ChatModifierObjectPriority {
    HIGHEST (0),
    HIGH (1),
    DEFAULT (2),
    LOW (3),
    LOWEST (4);

    public final int num;

    ChatModifierObjectPriority(int i) {
        num = i;
    }

    public int toInt() { return num; }
}
