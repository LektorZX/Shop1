package root.service.product;

import root.dao.product.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.entity.Product;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    @Override
    public Product save(Product product){
        product.setId(productDao.save(product));
        return product;
    }
    @Override
    public void del(Product product){
        productDao.delete(product);
    }

    @Override
    public List<Product> findAll() {
        System.out.println();
        return productDao.findAll();
    }
    @Override
    public Product findId(Long id) {
        return productDao.findById(id);
    }
    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteById(Long primaryKey) {
        Product byId = productDao.findById(primaryKey);
        productDao.delete(byId);
    }
}
