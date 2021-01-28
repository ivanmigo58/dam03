package com.company;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadCSV {
    public List<Calle> read() {
        List<Calle> calleList = new ArrayList<>();
        System.out.println("Leyendo CSV . . .");
        String pathCSV = "https://opendata-ajuntament.barcelona.cat/data/dataset/d7802fd1-cdfb-4562-9148-d18722d7e2d8/resource/2b010e59-6952-4b27-9c4e-47fcaf64c916/download";
        URL url = null;
        BufferedReader in = null;
        CSVReader reader = null;
        try {
            url = new URL(pathCSV);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            // Leo el fichero entero y lo leo
            reader = new CSVReader(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Lo parseo con el formato de la clase Mercados
        CsvToBean<Calle> activitatCsvToBean = new CsvToBeanBuilder(reader).withType(Calle.class).withIgnoreLeadingWhiteSpace(true).build();

        Iterator<Calle> csvIterator = activitatCsvToBean.iterator();
        // Por cada linea, la guardo
        while(csvIterator.hasNext()) {
            Calle calle = csvIterator.next();
            calleList.add(calle);
        }
        return calleList;
    }
}
