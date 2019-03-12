package br.com.aceleradevsp.squad2.mapfood;

import br.com.aceleradevsp.squad2.mapfood.logistic.LogisticService;
import br.com.aceleradevsp.squad2.mapfood.logistic.MotoboyModel;
import br.com.aceleradevsp.squad2.mapfood.order.ClientModel;
import br.com.aceleradevsp.squad2.mapfood.order.ItemModel;
import br.com.aceleradevsp.squad2.mapfood.order.OrderService;
import br.com.aceleradevsp.squad2.mapfood.order.RestaurantModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Application.class);

    private static final String MOTOBOY_CSV = "motoboys.csv";
    private static final String CLIENT_CSV = "clientes.csv";
    private static final String RESTAURANT_CSV = "estabelecimentos.csv";
    private static final String PRODUCTS_CSV = "produtos.csv";
    private static final Pattern DELIMITER = Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");


    private LogisticService logisticService;
    private OrderService orderService;

    @Autowired
    public Application(LogisticService logisticService, OrderService orderService) {
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
        Map<String, List<ItemModel>> items = readItems();
        handleCSV(RESTAURANT_CSV).forEach(line -> {
            try(Scanner csvContentScanner = new Scanner(line)){
                int index = 0;
                String id = "";
                RestaurantModel.RestaurantModelBuilder builder = RestaurantModel.builder();
                double[] position = new double[2];

                csvContentScanner.useDelimiter(DELIMITER);

                while(csvContentScanner.hasNext()){
                    String content = csvContentScanner.next();
                    switch(index){
                        case 0 :
                            id = content;
                            break;
                        case 1:
                            builder.withRestaurant(content);
                            break;
                        case 2:
                            builder.withAdressCity(content);
                            break;
                        case 3 :
                            position[1] = parseDouble(content);
                            break;
                        case 4 :
                            position[0] = parseDouble(content);
                            break;
                        case 5:
                            builder.withDishdescription(content);
                            break;
                        default:
                            break;
                    }
                    index++;
                }

                orderService.createRestaurant(builder
                        .withRestaurantId(id)
                        .withPosition(position)
                        .withMenu(items.get(id))
                        .build());
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        });
    }

    private Map<String, List<ItemModel>> readItems() {
        Map<String, List<ItemModel>> items = new HashMap<>();
        List<String> lines = handleCSV(PRODUCTS_CSV);
        for (String line : lines) {
            try (Scanner csvContentScanner = new Scanner(line)) {
                csvContentScanner.useDelimiter(DELIMITER);
                String restaurantId = "";
                ItemModel.ItemModelBuilder builder = ItemModel.builder();
                int index = 0;
                while (csvContentScanner.hasNext()) {
                    String content = csvContentScanner.next();
                    switch (index) {
                        case 0:
                            builder.withItemDescription(content);
                            break;
                        case 1:
                            builder.withItemId(content);
                            break;
                        case 2:
                            restaurantId = content;
                            break;
                        case 4:
                            builder.withClassification(content);
                            break;
                        case 5:
                            builder.withUnitPrice(Double.parseDouble(content));
                            break;
                        default:
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
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return items;
    }

    private void populateClient() {
        handleCSV(CLIENT_CSV).forEach(line -> {
            try(Scanner csvContentScanner = new Scanner(line)) {
                int index = 0;
                String id = "";
                double[] position = new double[2];

                csvContentScanner.useDelimiter(DELIMITER);

                while(csvContentScanner.hasNext()){
                    String content = csvContentScanner.next();
                    switch(index){
                        case 0 :
                            id = content;
                            break;
                        case 1 :
                            position[1] = Double.parseDouble(content);
                            break;
                        case 2 :
                            position[0] = Double.parseDouble(content);
                            break;
                        default:
                            break;
                    }
                    index++;
                }

                orderService.createClient(ClientModel.builder()
                        .withIdClient(id)
                        .withPosition(position)
                        .build());
            }catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
    }

    private void populateMotoboy() {
        handleCSV(MOTOBOY_CSV).forEach(line -> {
            try(Scanner csvContentScanner = new Scanner(line)) {
                int index = 0;
                int id = 0;
                double[] position = new double[2];

                csvContentScanner.useDelimiter(DELIMITER);

                while(csvContentScanner.hasNext()){
                    String content = csvContentScanner.next();
                    switch(index){
                        case 0 :
                            id = Integer.parseInt(content);
                            break;
                        case 1 :
                            position[1] = Double.parseDouble(content);
                            break;
                        case 2 :
                            position[0] = Double.parseDouble(content);
                            break;
                        default:
                            break;
                    }
                    index++;
                }
                logisticService.createMotoboy(MotoboyModel.builder()
                        .withIdMotoBoy(id)
                        .withPosition(position)
                        .withDelivery(new ArrayList<>())
                        .build());
            }catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
    }

    private List<String> handleCSV(String resource) {
        ClassLoader classLoader = getClass().getClassLoader();
        String line = null;
        List<String> csvLines = new ArrayList<>();

        try(BufferedReader csvScanner = new BufferedReader(new FileReader(classLoader.getResource(resource).getFile()))){
            String header = csvScanner.readLine();
            logger.debug(header);

            while ((line = csvScanner.readLine()) != null) {
                csvLines.add(line);
            }
        }catch (IOException e){
            logger.error(e.getMessage());
        }
        return csvLines;
    }

    private double parseDouble(String value){
        try{
            return Double.parseDouble(value);
        }catch (NumberFormatException e) {
            logger.error(e.getMessage());
            return 0.0;
        }
    }
}
