package root.dao.base;

import root.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.util.List;

public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }

    @Override
    public T findById(Long id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    @Override
    public Long save(T entity) {
        return (Long) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from " + clazz.getSimpleName(), clazz)
                .getResultList();
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }



    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
