package dao.iface;

import domain.TelephoneType;

import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
public interface TelephoneTypeDAOInterface {

    public void insert(TelephoneType entity);

    public void update(TelephoneType entity);

    public TelephoneType getById(Long id);

    public List<TelephoneType> getAll();

    public void delete(TelephoneType entity);

    public void deleteAll();

}
