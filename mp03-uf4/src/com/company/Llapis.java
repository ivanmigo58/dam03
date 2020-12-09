package com.company;

import java.util.Objects;

public class Llapis implements Comparable<Llapis> {
    public int color;
    public float gruix;

    public int getColor() {return color;}

    public float getGruix() {return gruix;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Llapis llapis = (Llapis) o;
        return color == llapis.color &&
                Float.compare(llapis.gruix, gruix) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, gruix);
    }

    @Override
    public String toString() {
        return "Llapis{" +
                "color=" + color +
                ", gruix=" + gruix +
                '}';
    }

    public void setGruix(float g) {g = gruix;}

    public Llapis() {
        color = 0;
        gruix = 0;
    }

    public Llapis(int color) {
        this.color = color;
        gruix = 0;
    }

    public Llapis(int color, float gruix) {
        this.color = color;
        this.gruix = gruix;
    }

    public int compareTo(Llapis o) {
        if (this.color < o.getColor()) return 1;
        else if (this.color > o.getColor()) return -1;
        else return 0;
    }
}
