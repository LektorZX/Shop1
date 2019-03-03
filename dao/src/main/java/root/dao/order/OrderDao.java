package root.dao.order;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import root.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao {

    Optional<Order> findById(Long aLong);
    void deleteById(Long aLong);
    void save(Long order, Long user);
    void delete( Long orderId);
    List<Long> customFindAllByBasket(Long along);
}


