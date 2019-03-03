package root.dao.basket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import root.entity.Basket;

import java.util.List;

@Repository
public interface BasketDao {

    void deleteById(Long aLong);
    void delete(Basket basket);
    void save(Basket basket);
    void findListProductByBasketId(Long idBasket);
    List<Basket> findBasketsByUserId(Long userId);

}


