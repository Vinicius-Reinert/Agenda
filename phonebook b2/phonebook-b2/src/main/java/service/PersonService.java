package service;

import dao.impl.AddressDAO;
import dao.impl.PersonDAO;
import dao.impl.TelephoneDAO;
import domain.Person;
import domain.Telephone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.RESTUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@Service
public class PersonService {

    @Autowired
    PersonDAO personDAO;
    @Autowired
    TelephoneDAO telephoneDAO;
    @Autowired
    AddressDAO addressDAO;


    public List<Person> get(HttpServletResponse response){
        List<Person> personList = personDAO.getAll();
        if(personList == null || personList.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return personList;
    }

    public Person getById(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        Person person = personDAO.getById(Long.parseLong(id));
        if(person == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return person;
    }

    public Person insert(Person person, HttpServletResponse response){

        if(person == null || person.getTelephones() == null || person.getTelephones().isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        if(person.getAnniversaryDate() != null && !isAnniversaryDateValid(person.getAnniversaryDate())){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        if(person.getAddress() != null){
            addressDAO.insert(person.getAddress());
        }

        personDAO.insert(person);

        if(person.getTelephones() != null){
            for(Telephone telephone : person.getTelephones()){
                telephone.setPerson_id(person);
                telephoneDAO.insert(telephone);
            }
        }


        return person;
    }

    public Person update(Person person, String id, HttpServletResponse response){

        if(!RESTUtils.isInteger(id) || person == null || person.getTelephones() == null || person.getTelephones().isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Person currentPerson = personDAO.getById(Long.parseLong(id));
        if(currentPerson == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        if(person.getName() != null) {
            currentPerson.setName(person.getName());
        }
        if(person.getEmail() != null) {
            currentPerson.setEmail(person.getEmail());
        }
        if(person.getAddress() != null) {
            currentPerson.setAddress(person.getAddress());
        }
        if(person.getAnniversaryDate() != null) {
            if(!isAnniversaryDateValid(person.getAnniversaryDate())){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
            currentPerson.setAnniversaryDate(person.getAnniversaryDate());
        }
        if(person.getTelephones() != null){
            currentPerson.setTelephones(person.getTelephones());
        }

        personDAO.update(currentPerson);
        return currentPerson;
    }

    public void deleteAll(){
        personDAO.deleteAll();
    }

    public Person delete(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        Person person = personDAO.getById(Long.parseLong(id));
        personDAO.delete(person);
        return person;
    }

    private boolean isAnniversaryDateValid(Date anniversaryDate){
        Calendar calendar = Calendar.getInstance();
        int maxInDays = -1;
        int minInDays = -1 * 365 * 100;
        calendar.add(Calendar.DATE, maxInDays);
        Date maxDate = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, minInDays);
        Date minDate = calendar.getTime();

        return anniversaryDate.after(minDate) && anniversaryDate.before(maxDate);
    }

}
