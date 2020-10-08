package com.company;

import java.util.Arrays;

public class JocDaus {
    Dau[] dau = new Dau[3];

    public JocDaus() {
        this.dau[0] = new Dau();
        this.dau[1] = new Dau();
        this.dau[2] = new Dau();
    }

    public boolean jugar() {
        int a = this.dau[0].lanamiento();
        int b = this.dau[1].lanamiento();
        int c = this.dau[2].lanamiento();
        return a == b && b == c;
    }

    public String toString() {
        return "Juego Dado= " + Arrays.toString(this.dau) ;
    }
}
