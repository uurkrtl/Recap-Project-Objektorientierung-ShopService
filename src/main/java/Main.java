import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderRepo orderRepo = new OrderListRepo();
        ProductRepo productRepo = new ProductRepo();
        IdService idService = new IdService();
        ShopService shopService = new ShopService(productRepo, orderRepo, idService);

        List<Product> products = List.of(new Product("2", "Birne"), new Product("3", "Banane"), new Product("4", "Kiwi"),
                new Product("5", "Orange"), new Product("6", "Mango"), new Product("7", "Ananas"));
        products.stream().forEach(product -> productRepo.addProduct(product));

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
}
