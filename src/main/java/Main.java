import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static OrderRepo orderRepo = new OrderListRepo();
    static ProductRepo productRepo = new ProductRepo();
    static IdService idService = new IdService();
    static ShopService shopService = new ShopService(productRepo, orderRepo, idService);

    public static void main(String[] args) {
        addProducts();
        //testOrders();
        readFile();
    }

    private static void addProducts() {
        List<Product> products = List.of(new Product("2", "Birne", 10), new Product("3", "Banane", 10), new Product("4", "Kiwi", 10),
                new Product("5", "Orange", 10), new Product("6", "Mango", 10), new Product("7", "Ananas", 10));
        products.stream().forEach(product -> productRepo.addProduct(product));
    }

    private static void testOrders() {
        Order order1 = shopService.addOrder(Map.of("1", 5.0));
        Order order2 = shopService.addOrder(Map.of("2", 5.0));
        Order order3 = shopService.addOrder(Map.of("3", 15.0));
        Order order4 = shopService.addOrder(Map.of("4", 5.0));
        Order order5 = shopService.addOrder(Map.of("5", 5.0));
        Order order6 = shopService.addOrder(Map.of("6", 5.0));
        Order order7 = shopService.addOrder(Map.of("7", 5.0));

        shopService.updateOrder(order3.id(), OrderStatus.IN_DELIVERY);
        shopService.updateOrder(order4.id(), OrderStatus.COMPLETED);
        shopService.updateOrder(order5.id(), OrderStatus.IN_DELIVERY);
        shopService.updateOrder(order6.id(), OrderStatus.COMPLETED);
        shopService.updateOrder(order7.id(), OrderStatus.COMPLETED);

        shopService.getOldestOrderPerStatus().entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    private static void readFile () {
        try {
            File file = new File("transactions.txt");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            Map<String, String> order = new HashMap<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String command = parts[0];
                switch (command) {
                    case "addOrder":
                        Map<String, Double> productIds = new HashMap<>();
                        for (int i = 2; i < parts.length; i++) {
                            String[] product = parts[i].split(":");
                            productIds.put(product[0], Double.parseDouble(product[1]));
                        }
                        order.put(parts[1], shopService.addOrder(productIds).id());
                        System.out.println("Order added with id: " + order.get(parts[1]));
                        break;
                    case "setStatus":
                        shopService.updateOrder(order.get(parts[1]), OrderStatus.valueOf(parts[2]));
                        System.out.println("\nOrder [" + order.get(parts[1]) + "] status updated to: " + parts[2]);
                        break;
                    case "printOrders":
                        System.out.println("\nOrder List:");
                        order.values().stream().forEach(id -> System.out.println(shopService.getOrderById(id)));
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}