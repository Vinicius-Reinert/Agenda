package springboot;

import dao.impl.AddressDAO;
import dao.impl.PersonDAO;
import dao.impl.TelephoneDAO;
import domain.Person;
import domain.Telephone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PersonService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@RestController
public class PersonController {


    @Autowired
    PersonService personService;

    @RequestMapping(method=GET, path="/person")
    public List<Person> get(HttpServletResponse response){
        return personService.get(response);
    }

    @RequestMapping(method=GET, path="/person/{personId}")
    public Person getById(@PathVariable(value="personId") String id, HttpServletResponse response){
        return personService.getById(id, response);
    }

    @RequestMapping(method=POST, path="/person")
    public Person insert(@RequestBody Person person, HttpServletResponse response){
        return personService.insert(person, response);
    }

    @RequestMapping(method=PUT, path="/person/{personId}")
    public Person update(@RequestBody Person person, @PathVariable(value="personId") String id, HttpServletResponse response){
        return personService.update(person, id, response);
    }

    @RequestMapping(method=DELETE, path="/person")
    public void deleteAll(){
        personService.deleteAll();
    }

    @RequestMapping(method=DELETE, path="/person/{personId}")
    public Person delete(@PathVariable(value="personId") String id, HttpServletResponse response){
        return personService.delete(id, response);
    }
}
