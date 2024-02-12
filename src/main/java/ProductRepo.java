import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {
    private List<Product> products;

    public ProductRepo() {
        products = new ArrayList<>();
        products.add(new Product("1", "Apfel", 10));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Optional<Product> getProductById(String id) {
        for (Product product : products) {
            if (product.id().equals(id)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public Product addProduct(Product newProduct) {
        products.add(newProduct);
        return newProduct;
    }

    public void removeProduct(String id) {
        for (Product product : products) {
           if (product.id().equals(id)) {
               products.remove(product);
               return;
           }
        }
    }

    public void updateProductStock(String id, double stock) {
        Product updatedProduct;
        for (Product product : products) {
            if (product.id().equals(id)) {
                updatedProduct = product.withStock(stock);
                products.set(products.indexOf(product), updatedProduct);
                return;
            }
        }
    }
}
