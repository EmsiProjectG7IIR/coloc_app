package ma.emsi.offre_service.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    T save (T o);

    void delete(T o);
    Optional<T> findById(Long id);
    List<T> findAll();
}