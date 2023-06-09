package ru.netology.product.repository;

import ru.netology.product.data.Product;
import ru.netology.product.exception.AlreadyExistException;
import ru.netology.product.exception.NotFoundException;

public class Repository {
    protected Product[] products = new Product[0];


    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistException("Element with id: " + product.getId() + " already exists");
        }
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {

        return products;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;

    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}