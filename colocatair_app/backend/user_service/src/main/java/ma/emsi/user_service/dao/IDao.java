package ma.emsi.user_service.dao;


import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    T save (T o);
    void update(T o);
    void delete(T id);
    Optional<T> findById(Long id);
    List<T> findAll();
}
