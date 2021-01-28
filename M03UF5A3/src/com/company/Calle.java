package com.company;


import lombok.Data;
import com.opencsv.bean.CsvBindByName;

@Data
public class Calle {
    @CsvBindByName
    String codi_via;
    @CsvBindByName
    String codi_carrer_ine;
    @CsvBindByName
    String tipus_via;
    @CsvBindByName
    String nom_oficial;
    @CsvBindByName
    String nom_curt;
    @CsvBindByName
    String nre_min;
    @CsvBindByName
    String nre_max;


}
