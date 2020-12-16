package com.company.Control;

public class OperacionsBanc {
    private static final String dniChars="TRWAGMYFPDXBNJZSQVHLCKE";

    public static boolean verifyDNI(String dni) {
        String DNI = dni.trim().replaceAll(" ", "").substring(0, 8);
        char letraDni = dni.charAt(8);
        int numDNI = Integer.parseInt(DNI) % 23;
        if ( dniChars.charAt(numDNI) == letraDni) {
            return true;
        } else {
            return false;
        }
    }

}
