package br.com.aceleradevsp.squad2.mapfood;

import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final String MOTOBOY_CSV = "motoboy.csv";
    private static final String CLIENT_CSV = "cliente.csv";
    private static final String RESTAURANT_CSV = "estabelecimento.csv";
    private static final String PRODUCTS_CSV = "produtos.csv";


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        populateMotoboy();
        populateClient();
        populateItem();
        populateRestaurant();
    }

    private void populateRestaurant() {
        try {
            handleCSV(RESTAURANT_CSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateItem() {
        try {
            handleCSV(PRODUCTS_CSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateClient() {
        try {
            handleCSV(CLIENT_CSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateMotoboy() {
        try {
            handleCSV(MOTOBOY_CSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> handleCSV(String resource) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader CSVScanner = null;
        String line = null;
        List<String> csvLines = Collections.emptyList();

        CSVScanner = new BufferedReader(new FileReader(classLoader.getResource(resource).getFile()));
        //Pular cabeçalho
        CSVScanner.readLine();

        while (null != (line = CSVScanner.readLine())) {
            csvLines.add(line);
        }
        return csvLines;
    }
}
