package ru.netology.product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.netology.product.data.Book;
import ru.netology.product.data.Product;
import ru.netology.product.data.Smartphone;
import ru.netology.product.exception.AlreadyExistException;
import ru.netology.product.exception.NotFoundException;
import ru.netology.product.manager.ProductManager;
import ru.netology.product.repository.Repository;

public class ExceptionTest {

    Repository repository = new Repository();
    ProductManager manager = new ProductManager(repository);

    Product book1 = new Book(1, "Sherlock Holmes 1", 200, "Arthur Conan Doyle");
    Product smartphone1 = new Smartphone(2, "Samsung s23 ", 75_000, "Samsung");
    Product book2 = new Book(3, "Sherlock Holmes 2", 250, "Arthur Conan Doyle");
    Product book3 = new Book(4, "Sherlock Holmes 3", 300, "Arthur Conan Doyle");
    Product smartphone2 = new Smartphone(5, "iPhone 14", 100_000, "Apple");

    @Test
    public void ShouldRemoveByExistingId() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);

        manager.removeById(3);

        Product[] expected = {book1, smartphone1, book3, smartphone2};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldGenNotFoundExceptionIfIdDoesNotExist() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);

        assertThrows(NotFoundException.class, () -> {
            manager.removeById(9);
        });
    }

    @Test
    public void ShouldAddNewProduct() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] expected = {book1, book2, book3};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldNotAddNewProductIfIdAlreadyExists() {
        Product smartphone3 = new Smartphone(8, "iPhone 14 Pro", 120_000, "Apple");
        Product smartphone4 = new Smartphone(8, "iPhone 14 Pro Max", 150_000, "Apple");

        manager.add(smartphone3);

        assertThrows(AlreadyExistException.class, () -> {
            manager.add(smartphone4);
        });
    }
}
