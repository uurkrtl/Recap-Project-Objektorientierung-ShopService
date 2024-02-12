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
        List<Product> products = List.of(new Product("1", "Apfel"), new Product("2", "Birne"), new Product("3", "Banane"), new Product("4", "Kiwi"),
                new Product("5", "Orange"), new Product("6", "Mango"), new Product("7", "Ananas"));
        products.stream().forEach(product -> productRepo.addProduct(product));
    }

    private static void testOrders() {
        Order order1 = shopService.addOrder(List.of("1"));
        Order order2 = shopService.addOrder(List.of("2"));
        Order order3 = shopService.addOrder(List.of("3"));
        Order order4 = shopService.addOrder(List.of("4"));
        Order order5 = shopService.addOrder(List.of("5"));
        Order order6 = shopService.addOrder(List.of("6"));
        Order order7 = shopService.addOrder(List.of("7"));

        shopService.updateOrder(order3.id(), OrderStatus.IN_DELIVERY);
        shopService.updateOrder(order4.id(), OrderStatus.COMPLETED);
        shopService.updateOrder(order5.id(), OrderStatus.IN_DELIVERY);
        shopService.updateOrder(order6.id(), OrderStatus.COMPLETED);
        shopService.updateOrder(order7.id(), OrderStatus.COMPLETED);

        shopService.getOldestOrderPerStatus().entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    private static void readFile () {
        Map<String, String> order = new HashMap<>();
        try {
            File file = new File("transactions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String command = parts[0];
                switch (command) {
                    case "addOrder":
                        List<String> products = new ArrayList<>();
                        for (int i = 2; i < parts.length; i++) {
                            products.add(parts[i]);
                        }
                        order.put(parts[1], shopService.addOrder(products).id());
                        System.out.println("Order added with id: " + order.get(parts[1]));
                        break;
                    case "setStatus":
                        shopService.updateOrder(order.get(parts[1]), OrderStatus.valueOf(parts[2]));
                        System.out.println("Order [" + order.get(parts[1]) + "] status updated to: " + parts[2]);
                        break;
                    case "printOrders":
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