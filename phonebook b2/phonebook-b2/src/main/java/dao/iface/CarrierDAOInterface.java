package dao.iface;

import domain.Carrier;

import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
public interface CarrierDAOInterface {

    public void insert(Carrier entity);

    public void update(Carrier entity);

    public Carrier getById(Long id);

    public List<Carrier> getAll();

    public void delete(Carrier entity);

    public void deleteAll();

}
