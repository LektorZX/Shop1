package root.dao.order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import root.entity.Order;
import java.util.List;
import java.util.Optional;
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public Optional<Order> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {}

    @Override
    public void save(Long order,Long id_product) {
        NativeQuery nativeQuery = sessionFactory.getCurrentSession()
        .createNativeQuery("INSERT INTO online_store.order(order_id, id_product) values (?,?)");
        nativeQuery.setParameter(1, order);
        nativeQuery.setParameter(2, id_product).executeUpdate();
    }

    @Override
    public void delete(Long orderId) {
        NativeQuery nativeQuery = sessionFactory.getCurrentSession()
        .createNativeQuery("delete  from online_store.order where order_id=?");
        nativeQuery.setParameter(1, orderId).executeUpdate();
    }

    @Override
    public List<Long> customFindAllByBasket(Long along) {
        NativeQuery nativeQuery = sessionFactory.getCurrentSession()
        .createNativeQuery("select m.id_product from online_store.order m where m.order_id=?");
        List<Long> queryReturns = nativeQuery.setParameter(1, along).list();
        return queryReturns;
    }
}
