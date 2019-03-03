package root.service.product;

import root.entity.Product;
import root.entity.User;

import java.util.List;
import java.util.Optional;

public interface ProductService {
     Product save(Product product);
    void del(Product product);
    List<Product> findAll() ;
     Product findId(Long id);
     void update(Product product) ;
    void deleteById(Long primaryKey);
}
