package com.company;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Menu(new ReadCSV().read()).menuPrincipal();
    }


}
