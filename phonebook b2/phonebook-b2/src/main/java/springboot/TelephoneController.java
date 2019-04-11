package springboot;

import domain.Telephone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TelephoneService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@RestController
public class TelephoneController {


    @Autowired
    TelephoneService telephoneService;

    @RequestMapping(method=GET, path="/telephone")
    public List<Telephone> get(HttpServletResponse response){
        return telephoneService.get(response);
    }

    @RequestMapping(method=GET, path="/telephone/{telephoneId}")
    public Telephone getById(@PathVariable(value="telephoneId") String id, HttpServletResponse response){
        return telephoneService.getById(id, response);
    }

    @RequestMapping(method=POST, path="/telephone")
    public Telephone insert(@RequestBody Telephone telephone, HttpServletResponse response){
        return telephoneService.insert(telephone, response);
    }

    @RequestMapping(method=PUT, path="/telephone/{telephoneId}")
    public Telephone update(@PathVariable(value="telephoneId") String id, @RequestBody Telephone telephone, HttpServletResponse response){
        return telephoneService.update(id, telephone, response);
    }

    @RequestMapping(method=DELETE, path="/telephone")
    public void deleteAll(){
        telephoneService.deleteAll();
    }

    @RequestMapping(method=DELETE, path="/telephone/{telephoneId}")
    public Telephone delete(@PathVariable(value="telephoneId") String id, HttpServletResponse response){
        return telephoneService.delete(id, response);
    }
}
