package br.com.aceleradevsp.squad2.mapfood;

import br.com.aceleradevsp.squad2.mapfood.logistic.LogisticService;
import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyModel;
import br.com.aceleradevsp.squad2.mapfood.order.ClientModel;
import br.com.aceleradevsp.squad2.mapfood.order.ItemModel;
import br.com.aceleradevsp.squad2.mapfood.order.OrderService;
import br.com.aceleradevsp.squad2.mapfood.order.RestaurantModel;

import com.mongodb.client.model.geojson.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final String MOTOBOY_CSV = "motoboys.csv";
    private static final String CLIENT_CSV = "cliente.csv";
    private static final String RESTAURANT_CSV = "estabelecimento.csv";
    private static final String PRODUCTS_CSV = "produtos.csv";
    private static final String DELIMITER = ",";


    private LogisticService logisticService;
    private OrderService orderService;

    @Autowired
    public Application(LogisticService logisticService,  OrderService orderService) {
        this.logisticService = logisticService;
        this.orderService = orderService;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        populateMotoboy();
        populateClient();
        populateRestaurant();
    }

    private void populateRestaurant() {
        try {
            handleCSV(RESTAURANT_CSV).forEach(line -> {
                int index = 0;
                RestaurantModel.RestaurantModelBuilder builder = RestaurantModel.builder();
                List<Double> position = new ArrayList<>();
                Scanner CSVContentScanner = new Scanner(line);

                CSVContentScanner.useDelimiter(DELIMITER);

                while(CSVContentScanner.hasNext()){
                    String content = CSVContentScanner.next();
                    switch(index){
                        case 0 :
                            builder.withRestaurantId(content);
                            break;
                        case 1 :
                            builder.withRestaurant(content);
                            break;
                        case 2 :
                            builder.withAdressCity(content);
                            break;
                        case 3 :
                        case 4 :
                            position.add(Double.parseDouble(content));
                            break;
                        case 5 :
                            builder.withDishdescription(content);
                            break;
                    }
                    index++;
                }

                orderService.createRestaurante(builder
                        .withPosition(new Position(position))
                        .build());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateItem() {
        try {
            List<String> lines = handleCSV(PRODUCTS_CSV);
            for (String line : lines) {
                Scanner CSVContentScanner = new Scanner(line);
                CSVContentScanner.useDelimiter(DELIMITER);
                int index =0;
                while(CSVContentScanner.hasNext()){
                    String restaurantId;
                    ItemModel.ItemModelBuilder builder = ItemModel.builder();
                    //item_description,item_id,restaurant_id,restaurant,classification,unit_price,address_city
                    String content = CSVContentScanner.next();
                    switch(index){
                        case 0 :
                            builder.withItemDescription(content);
                            break;
                        case 1 :
                            builder.withItemId(content);
                            break;
                        case 2 :
                            restaurantId = content;
                            break;
                        case 4 :
                            builder.withClassification(content);
                            break;
                        case 5 :
                            builder.withUnitPrice(Double.parseDouble(content));
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateClient() {
        try {
            handleCSV(CLIENT_CSV).forEach(line -> {
                int index = 0;
                int id = 0;
                List<Double> position = new ArrayList<>();
                Scanner CSVContentScanner = new Scanner(line);

                CSVContentScanner.useDelimiter(DELIMITER);

                while(CSVContentScanner.hasNext()){
                    String content = CSVContentScanner.next();
                    switch(index){
                        case 0 :
                            id = Integer.parseInt(content);
                            break;
                        case 1 :
                        case 2 :
                            position.add(Double.parseDouble(content));
                            break;
                    }
                    index++;
                }

                orderService.createClient(ClientModel.builder()
                        .withIdClient(id)
                        .withPosition(new Position(position))
                        .build());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateMotoboy() {
        try {
            handleCSV(MOTOBOY_CSV).forEach(line -> {
                int index = 0;
                int id = 0;
                List<Double> position = new ArrayList<>();
                Scanner CSVContentScanner = new Scanner(line);

                CSVContentScanner.useDelimiter(DELIMITER);

                while(CSVContentScanner.hasNext()){
                    String content = CSVContentScanner.next();
                    switch(index){
                        case 0 :
                            id = Integer.parseInt(content);
                            break;
                        case 1 :
                        case 2 :
                            position.add(Double.parseDouble(content));
                            break;
                    }
                    index++;
                }

                logisticService.createMotoboy(MotoboyModel.builder()
                        .withIdMotoBoy(id)
                        .withPosition(new Position(position))
                        .withDelivery(new ArrayList<>())
                        .build());

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> handleCSV(String resource) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader CSVScanner = null;
        String line = null;
        List<String> csvLines = new ArrayList<>();

        CSVScanner = new BufferedReader(new FileReader(classLoader.getResource(resource).getFile()));
        //Ignore first line
        CSVScanner.readLine();

        while ((line = CSVScanner.readLine()) != null) {
            csvLines.add(line);
        }
        return csvLines;
    }
}
