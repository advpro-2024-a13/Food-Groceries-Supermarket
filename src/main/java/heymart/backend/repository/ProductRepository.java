package heymart.backend.repository;
import heymart.backend.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product createProduct(Product product) {
        productData.add(product);
        return product;
    }

    public void deleteProduct(UUID id) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(id)) {
                productData.remove(i);
                return;
            }
        }
    }

    public Product findByProductId(UUID id) {
        if (productData != null) {
            for (Product product : productData) {
                if (product.getProductId().equals(id)) {
                    return product;
                }
            }
        }
        return null;
    }

    public void editProduct(Product editedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(editedProduct.getProductId())) {
                productData.set(i, editedProduct);
                return;
            }
        }
    }

    public List<Product> findBySupermarketOwnerId(Long ownerId) {
        List<Product> productsOwnedByOwner = new ArrayList<>();
        if (productData != null) {
            for (Product product : productData) {
                if (product.getSupermarketOwnerId().equals(ownerId)) {
                    productsOwnedByOwner.add(product);
                }
            }
        }
        return productsOwnedByOwner;
    }



    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
