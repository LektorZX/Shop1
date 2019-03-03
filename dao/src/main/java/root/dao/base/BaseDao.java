package root.dao.base;

import root.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    T findById(Long id);

    Long save(T entity);

    List<T> findAll();

    void delete(T entity);

    void update(T entity);

}
