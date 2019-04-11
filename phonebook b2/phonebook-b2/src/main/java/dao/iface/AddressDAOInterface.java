package dao.iface;

import domain.Address;

import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
public interface AddressDAOInterface {

    public void insert(Address entity);

    public void update(Address entity);

    public Address getById(Long id);

    public List<Address> getAll();

    public void delete(Address entity);

    public void deleteAll();

}
