package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Llapis llapis = new Llapis();

        List<Llapis> caixa1 = new ArrayList<>();
        List<Llapis> caixa2 = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            // 10 lapices con colores diferentes
            caixa1.add(new Llapis((int) (Math.random() * 20)));
            // 10 lapices con diferentes colores y diferentes anchos
            caixa2.add(new Llapis((int) (Math.random() * 10), (float) (Math.random() * 10)));
        }

        // Imprimo el contenido de las cajas
        System.out.println("Caixa1: ");
        caixa1.forEach(System.out ::println);
        System.out.println("\n\nCaixa2: ");
        for (Llapis llapis1 : caixa2) {
            System.out.println(llapis1);
        }

        // Ordenado por colores
        Collections.sort(caixa1);
        caixa1.forEach(System.out ::println);

        // Ordenar por gruix
        System.out.println("\n\nOrdenando por gruix:");
        ComparatorLlapis comparatorLlapis = new ComparatorLlapis();
        Collections.sort(caixa2,comparatorLlapis);

        for (Llapis ll : caixa2) {
            System.out.println(ll);
        }

        System.out.println("\n\nCreamos el LinkedList:");
        // Metemos lo de la caixa2 en el LinkedList
        List<Llapis> linkedList = new LinkedList<>(caixa2);
        // Metemos tambien todos los colores a la caixa1
        linkedList.addAll(caixa1);
        // Imprimo la lista
        imprimir(linkedList);



        // Creo la tercera caja
        System.out.println("\n\nMuestra caja 3:");
        LinkedHashSet<Llapis> hashSet = new LinkedHashSet<>();

        for (int i = 0; i < linkedList.size(); i++) {
            if ( hashSet.add(linkedList.get(i)) ) {
                hashSet.add(linkedList.get(i));
            }
        }
        hashSet.forEach(System.out :: println);

        // Creammos el map
        System.out.println("\n\nEste es el comportamiento del map:");
        Map<Integer,String> map_colors = new HashMap<>();
        // Afegim entrades al map
        map_colors.put(0,"Negre");
        map_colors.put(1,"Vermell");
        map_colors.put(2,"Groc");
        map_colors.put(3,"Verd");
        map_colors.put(4,"Verd");
        map_colors.put(3,"Blanc");
        // Mostramos el contenido del map
        for (Map.Entry<Integer, String> k : map_colors.entrySet()) {
            System.out.println(k.getKey() + "-->" + k.getValue());
        }
        System.out.println("\n");


        // Creamos los 2 dados
        int dado1;
        int dado2;

        System.out.println("Cuantas tiradas quieres hacer? ");
        int tiradas = scanner.nextInt();

        // Creo el ArrayList
        ArrayList<Integer> list = new ArrayList<Integer>();

        int i;
        for ( i = 0; i < tiradas; i++) {
            // Metemos numero random del 1-6 por cada dado y los sumamos
            dado1 = (int) Math.floor(Math.random()*6+1);
            dado2 = (int) Math.floor(Math.random()*6+1);
            int sumaDados = dado1 + dado2;
            // Añadimos el resultado de la suma al ArrayList
            list.add((sumaDados));
        }
        System.out.println("Resultado: ");

        // Creamos el for para imprimir por pantalla con el Collections.frequency
        for(i = 2; i <= 12; ++i) {
            System.out.println(i + " ---> " + Collections.frequency(list, i));
        }


        // Hacemos lo mismo que antes pero con Map
        Map<Integer, Integer> map_daus = new HashMap<>();

        for(i = 2; i <= 12; ++i) {
            map_daus.put(i,0);
        }
        for ( i = 0; i < tiradas; i++) {
            // Metemos numero random del 1-6 por cada dado y los sumamos
            dado1 = (int) Math.floor(Math.random()*6+1);
            dado2 = (int) Math.floor(Math.random()*6+1);
            int sumaDados = dado1 + dado2;

            for (Integer key : map_daus.keySet()) {
                if (key == sumaDados) {
                    map_daus.put(key, map_daus.get(key) + 1);

                }
            }
        }

        for (Integer key : map_daus.keySet()){
            System.out.println("key: " + key.toString() + " value: " + map_daus.get(key));
        }




    }

    // Creo funcion para imprimir la lista
    public static void imprimir(List list) {
        list.forEach(System.out ::println);
    }
}
