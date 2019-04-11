package dao.iface;

import domain.Person;

import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
public interface PersonDAOInterface {

    public void insert(Person entity);

    public void update(Person entity);

    public Person getById(Long id);

    public List<Person> getAll();

    public void delete(Person entity);

    public void deleteAll();

    public List<Person> getByName(String name);

}
