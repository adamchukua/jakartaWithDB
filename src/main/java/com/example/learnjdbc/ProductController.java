package com.example.learnjdbc;

import dao.ProductDao;
import entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductController implements Serializable {
    private Product product = new Product();
    private final ProductDao productDao = new ProductDao();

    @PostConstruct
    public void init() {
        String id = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        if (id != null) {
            product = productDao.getProduct(Long.valueOf(id));
        }
    }

    public void saveProduct() {
        productDao.saveProduct(product);
        product = new Product();
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public void updateProduct() {
        productDao.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
