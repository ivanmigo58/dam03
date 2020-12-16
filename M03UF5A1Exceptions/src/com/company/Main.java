package com.company;

import com.company.Exceptions.BankAccountException;
import com.company.Exceptions.ClientAccountException;
import com.company.Model.Client;
import com.company.Model.CompteEstalvi;

public class Main {

    public static void main(String[] args) {
        Client juanmi = null;
        Client raul = null;
        try {
            juanmi = new Client("Juanmi", "Segura", "54177991L");
            raul = new Client("Raul", "Vazquez", "26439661B");
        } catch (ClientAccountException e) {
            System.out.println(e.getMessage());
        }

        CompteEstalvi compteEstalvi = new CompteEstalvi("123456789");
        compteEstalvi.addUser(juanmi);
        compteEstalvi.addUser(raul);
        try {
            compteEstalvi.removeUser(juanmi.getDNI());
        } catch (BankAccountException e) {
            System.out.println(e.getMessage());
        }

    }
}
