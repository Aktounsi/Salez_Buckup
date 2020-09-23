package com.prom.eazy;

public abstract class ListObject {
    public static final int TYPE_DATE = 0;
    public static final int TYPE_GENERAL_SORTIE = 1;
    public static final int TYPE_GENERAL_RETOUR = 2;

    abstract public int getType();
}
