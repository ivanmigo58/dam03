package com.company;

import java.util.Comparator;

public class ComparatorLlapis implements Comparator<Llapis> {

    @Override
    public int compare(Llapis ll1, Llapis ll2) {
        if (ll1.getGruix() < ll2.getGruix()) return 1;
        else if (ll1.getGruix() > ll2.getGruix()) return -1;
        else return 0;
    }
}
