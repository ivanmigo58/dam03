package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void imprimir(Persona p) {
        System.out.println("------------------------");
        System.out.println(p);
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        List<Persona> listPersona = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyy");
        Persona p1 = new Persona("Arya", Persona.Genere.DONA, LocalDate.parse("25/12/2002",format) );
        Persona p2 = new Persona("Tyrion", Persona.Genere.HOME, LocalDate.parse("12/10/1980",format));
        Persona p3 = new Persona("Cersei", Persona.Genere.DONA, LocalDate.parse("10/01/1984",format));
        Persona p4 = new Persona("Eddard", Persona.Genere.HOME, LocalDate.parse("24/04/1974",format));
        Persona p5 = new Persona("Sansa", Persona.Genere.DONA, LocalDate.parse("24/04/1992",format));
        Persona p6 = new Persona("Jaime", Persona.Genere.HOME, LocalDate.parse("24/04/1979",format));
        Persona p7 = new Persona("Khal", Persona.Genere.HOME, LocalDate.parse("10/08/1979",format));
        Persona p8 = new Persona("Daenerys", Persona.Genere.DONA, LocalDate.parse("12/11/1992",format));
        Persona p9 = new Persona("Davos", Persona.Genere.HOME, LocalDate.parse("12/11/1965",format));
        Persona p10 = new Persona("Jon Neu", Persona.Genere.HOME, LocalDate.parse("12/11/1986",format));
        Persona p11 = new Persona("Brienne", Persona.Genere.DONA, LocalDate.parse("12/11/1989",format));
        Persona p12 = new Persona("Elvis", Persona.Genere.HOME,LocalDate.of(2000,12,21));

        listPersona.add(p1);
        listPersona.add(p2);
        listPersona.add(p3);
        listPersona.add(p4);
        listPersona.add(p5);
        listPersona.add(p6);
        listPersona.add(p7);
        listPersona.add(p8);
        listPersona.add(p9);
        listPersona.add(p10);
        listPersona.add(p11);
        listPersona.add(p12);

        Collections.sort(listPersona, new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                if(o1.getNom().charAt(0) >= o2.getNom().charAt(0)) return 1;
                else return -1;
            }
        });

        Collections.sort(listPersona, (o1, o2) -> {
            if (o1.getNom().charAt(0) >= o2.getNom().charAt(0)) return 1;
            else return -1;
        });

        Collections.sort(listPersona, new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                if ( o1.getNom().charAt(0) >= o2.getNom().compareTo(o1.getNom()) ) return 1;
                else return -1;
            }
        });

        listPersona.forEach(persona -> System.out.println(persona));

        // 7
        listPersona.forEach(Main::imprimir);

        listPersona.sort(Comparator.comparing(Persona::getAge));

        // 8
        Map<Integer, Integer> mapPerson = new HashMap<>();
        listPersona.forEach(persona -> {
            // Agrego los datos a la lista, siempre que no existan
            mapPerson.putIfAbsent(persona.getAge(), 0);
            // Si existen los datos, le cambio el value a value + 1
            mapPerson.computeIfPresent(persona.getAge(),(key ,value) -> value + 1);
        });

        // 9
        mapPerson.forEach((key,value) -> System.out.println("Age: " + key + " - Veces: " + value));

        // 10
        List<Persona> donasStream = listPersona.stream().filter(persona -> persona.getGenere().equals(Persona.Genere.DONA)).collect(Collectors.toList());

        // 11
        List<Persona> menors25 = listPersona.stream().filter(persona -> persona.getAge() < 25).collect(Collectors.toList());

        // 12
        List<Persona> aA = listPersona.stream().filter(persona -> persona.getNom().equals("A") || persona.getNom().equals("a")).collect(Collectors.toList());

        // 13
        List<Persona> masJovenes = listPersona.stream().sorted(((o1, o2) -> {
            if (o1.getAge() > o2.getAge()) return 1;
            else if (o1.getAge() < o2.getAge()) return -1;
            else return 0;
        })).limit(2).collect(Collectors.toList());

        // 14
        listPersona.removeIf(persona -> (persona.getAge() > 29 && persona.getAge() < 41) );

        // 15
        listPersona.stream().map(persona -> persona.getDataNaixament().plusDays(2)).forEach(System.out::println);
        System.out.println("------------------------------");

        // 16
        List<LocalDate> personaRejovenecidas = listPersona.stream().map(persona -> persona.getDataNaixament().minusYears(2)).collect(Collectors.toList());
        for (int i = 0; i < listPersona.size(); i++) {
            listPersona.get(i).setDataNaixament(personaRejovenecidas.get(i));
        }
    }
}
