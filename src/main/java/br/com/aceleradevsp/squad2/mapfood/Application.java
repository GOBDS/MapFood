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
import org.springframework.data.mongodb.core.geo.GeoJson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final String MOTOBOY_CSV = "motoboys.csv";
    private static final String CLIENT_CSV = "clientes.csv";
    private static final String RESTAURANT_CSV = "estabelecimentos.csv";
    private static final String PRODUCTS_CSV = "produtos.csv";
    private static final Pattern DELIMITER = Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");


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
            Map<String, List<ItemModel>> items = readItems();
            handleCSV(RESTAURANT_CSV).forEach(line -> {
                int index = 0;
                String id = "";
                RestaurantModel.RestaurantModelBuilder builder = RestaurantModel.builder();
                List<Double> position = new ArrayList<>();
                Scanner CSVContentScanner = new Scanner(line);

                CSVContentScanner.useDelimiter(DELIMITER);

                while(CSVContentScanner.hasNext()){
                    String content = CSVContentScanner.next();
                    switch(index){
                        case 0 :
                            id = content;
                            break;
                        case 1 :
                            builder.withRestaurant(content);
                            break;
                        case 2 :
                            builder.withAdressCity(content);
                            break;
                        case 3 :
                        case 4 :
                            try {
                                position.add(Double.parseDouble(content));
                            }catch (NumberFormatException e){
                                System.out.println("Error:" + e.getMessage() + "For restaurant ID: " + id);
                                position.add(0.0);
                            }
                            break;
                        case 5 :
                            builder.withDishdescription(content);
                            break;
                    }
                    index++;
                }

                orderService.createRestaurante(builder
                        .withRestaurantId(id)
                        .withPosition(new Position(position.get(1),position.get(0)))
                        .withMenu(items.get(id))
                        .build());
                CSVContentScanner.close();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, List<ItemModel>> readItems() {
        Map<String, List<ItemModel>> items = new HashMap<>();
        try {
            List<String> lines = handleCSV(PRODUCTS_CSV);
            for (String line : lines) {
                Scanner CSVContentScanner = new Scanner(line);
                CSVContentScanner.useDelimiter(DELIMITER);
                String restaurantId = "";
                ItemModel.ItemModelBuilder builder = ItemModel.builder();
                int index =0;
                while(CSVContentScanner.hasNext()){
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
                            break;
                    }
                    index++;
                }
                items.computeIfPresent(restaurantId, (k, v) -> {
                    v.add(builder.build());
                    return v;
                });
                items.computeIfAbsent(restaurantId, list -> {
                 List<ItemModel> itemsList = new ArrayList<>();
                 itemsList.add(builder.build());
                 return itemsList;
                });

                CSVContentScanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
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
                        .withPosition(new Position(position.get(1),position.get(0)))
                        .build());

                CSVContentScanner.close();
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
                        .withPosition(new Position(position.get(1),position.get(0)))
                        .withDelivery(new ArrayList<>())
                        .build());

                CSVContentScanner.close();

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

        CSVScanner.close();

        return csvLines;
    }
}
