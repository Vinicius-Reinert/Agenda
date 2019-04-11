package springboot;

import domain.Carrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CarrierService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@RestController
public class CarrierController {


    @Autowired
    CarrierService carrierService;

    @RequestMapping(method=GET, path="/carrier")
    public List<Carrier> get(HttpServletResponse response){
        return carrierService.get(response);
    }

    @RequestMapping(method=GET, path="/carrier/{carrierId}")
    public Carrier getById(@PathVariable(value="carrierId") String id, HttpServletResponse response){
        return carrierService.getById(id, response);
    }

    @RequestMapping(method=POST, path="/carrier")
    public Carrier insert(@RequestBody Carrier carrier, HttpServletResponse response){
        return carrierService.insert(carrier, response);
    }

    @RequestMapping(method=PUT, path="/carrier/{carrierId}")
    public Carrier update(@PathVariable(value="carrierId") String id, @RequestBody Carrier carrier, HttpServletResponse response){
        return carrierService.update(id, carrier, response);
    }

    @RequestMapping(method=DELETE, path="/carrier")
    public void deleteAll(){
        carrierService.deleteAll();
    }

    @RequestMapping(method=DELETE, path="/carrier/{carrierId}")
    public Carrier delete(@PathVariable(value="carrierId") String id, HttpServletResponse response){
        return carrierService.delete(id, response);
    }
}
