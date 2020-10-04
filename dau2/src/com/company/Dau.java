package com.company;

public class Dau {
    private int valor;

    public Dau() {
    }

    public int getValor() {return this.valor;}

    public int lanamiento() {
        this.valor = (int) Math.floor(Math.random()*6+1);
        return this.valor;
    }

    public String toString() {return "Dado{valor=" + this.valor + '}';}
}
