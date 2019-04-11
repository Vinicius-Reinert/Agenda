import dao.impl.PersonDAO;
import dao.impl.TelephoneDAO;
import domain.Person;
import domain.Telephone;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vinicius Sipoli Reinert.
 */
public class PersonDAOTest {

    PersonDAO personDAO = new PersonDAO();
    TelephoneDAO telephoneDAO = new TelephoneDAO();

    @Before
    public void before(){
        telephoneDAO.deleteAll();
        personDAO.deleteAll();
    }

    @Test
    public void insertAndRead(){
        final String NAME = "Vinicius";
        final String EMAIL = "vinicius@mail.com";
        Person person = new Person();
        person.setName(NAME);
        person.setEmail(EMAIL);

        personDAO.insert(person);


        List<Person> all = personDAO.getByName(NAME);
        Person response = all.get(0);

        assertEquals(person.getEmail(), response.getEmail());
    }

    @Test
    public void insertAndReadWithTelephone(){
        final String NAME = "Vinicius";
        Person person = new Person();
        person.setName(NAME);

        Telephone telephone1 = new Telephone();
        Telephone telephone2 = new Telephone();

        telephone1.setNumber("123456789");
        telephone2.setNumber("789456123");

        List<Telephone> telephones = new ArrayList<Telephone>();
        telephones.add(telephone1);
        telephones.add(telephone2);
        telephone1.setPerson_id(person);
        telephone2.setPerson_id(person);
        person.setTelephones(telephones);

        personDAO.insert(person);
        telephoneDAO.insert(telephone1);
        telephoneDAO.insert(telephone2);

        List<Person> all = personDAO.getByName(NAME);
        Person response = all.get(0);

        assertEquals(person.getTelephones().size(), response.getTelephones().size());
    }

    @Test
    public void insertAndUpdateWithTelephone(){
        final String NAME = "Vinicius";
        Person person = new Person();
        person.setName(NAME);

        Telephone telephone1 = new Telephone();

        telephone1.setNumber("123456789");

        telephone1.setPerson_id(person);

        personDAO.insert(person);
        telephoneDAO.insert(telephone1);

        List<Person> all = personDAO.getByName(NAME);
        Person response = all.get(0);

        assertEquals(1, response.getTelephones().size());

        Telephone telephone2 = new Telephone();
        telephone2.setNumber("789456123");
        telephone2.setPerson_id(person);
        telephoneDAO.insert(telephone2);

        all = personDAO.getByName(NAME);
        response = all.get(0);

        assertEquals(2, response.getTelephones().size());

        Telephone t = telephoneDAO.getByNumber("123456789").get(0);
    }

}
