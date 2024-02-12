import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel", 10.0));
        assertEquals(actual, expected);
    }

    @Test
    void getProductById() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        Optional<Product> actual = repo.getProductById("1");

        //THEN
        Product expected = new Product("1", "Apfel", 10.0);
        assertEquals(actual.get(), expected);
    }

    @Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane", 10.0);

        //WHEN
        Product actual = repo.addProduct(newProduct);

        //THEN
        Optional<Product> expected = Optional.of(new Product("2", "Banane", 10.0)) ;
        assertEquals(actual, expected.get());
        assertEquals(repo.getProductById("2").get(), expected.get());
    }

    @Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        repo.removeProduct("1");

        //THEN
        assertTrue(repo.getProductById("1").isEmpty());
    }
}
