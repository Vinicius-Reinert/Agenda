package dao.iface;

import domain.Telephone;

import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
public interface TelephoneDAOInterface {

    public void insert(Telephone entity);

    public void update(Telephone entity);

    public Telephone getById(Long id);

    public List<Telephone> getAll();

    public void delete(Telephone entity);

    public void deleteAll();

    public List<Telephone> getByNumber(String number);

}
