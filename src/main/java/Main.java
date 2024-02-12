import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderRepo orderRepo = new OrderListRepo();
        ProductRepo productRepo = new ProductRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        List<Product> products = List.of(new Product("2", "Birne"), new Product("3", "Banane"), new Product("4", "Kiwi"));
        products.stream().forEach(product -> productRepo.addProduct(product));

        System.out.println(shopService.addOrder(List.of("1", "2", "3")));

    }
}
