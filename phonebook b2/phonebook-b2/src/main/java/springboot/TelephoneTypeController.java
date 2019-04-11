package springboot;

import domain.TelephoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TelephoneTypeService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@RestController
public class TelephoneTypeController {


    @Autowired
    TelephoneTypeService telephoneTypeService;

    @RequestMapping(method=GET, path="/telephoneType")
    public List<TelephoneType> get(HttpServletResponse response){
        return telephoneTypeService.get(response);
    }

    @RequestMapping(method=GET, path="/telephoneType/{telephoneTypeId}")
    public TelephoneType getById(@PathVariable(value="telephoneTypeId") String id, HttpServletResponse response){
        return telephoneTypeService.getById(id, response);
    }

    @RequestMapping(method=POST, path="/telephoneType")
    public TelephoneType insert(@RequestBody TelephoneType telephoneType, HttpServletResponse response){
        return telephoneTypeService.insert(telephoneType, response);
    }

    @RequestMapping(method=PUT, path="/telephoneType/{telephoneTypeId}")
    public TelephoneType update(@PathVariable(value="telephoneTypeId") String id, @RequestBody TelephoneType telephoneType, HttpServletResponse response){
        return telephoneTypeService.update(id, telephoneType, response);
    }

    @RequestMapping(method=DELETE, path="/telephoneType")
    public void deleteAll(){
        telephoneTypeService.deleteAll();
    }

    @RequestMapping(method=DELETE, path="/telephoneType/{telephoneTypeId}")
    public TelephoneType delete(@PathVariable(value="telephoneTypeId") String id, HttpServletResponse response){
        return telephoneTypeService.delete(id, response);
    }
}
