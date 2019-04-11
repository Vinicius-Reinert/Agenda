package springboot;

import domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AddressService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@RestController
public class AddressController {


    @Autowired
    AddressService addressService;

    @RequestMapping(method=GET, path="/address")
    public List<Address> get(HttpServletResponse response){
        return addressService.get(response);
    }

    @RequestMapping(method=GET, path="/address/{addressId}")
    public Address getById(@PathVariable(value="addressId") String id, HttpServletResponse response){
        return addressService.getById(id, response);
    }

    @RequestMapping(method=POST, path="/address")
    public Address insert(@RequestBody Address address, HttpServletResponse response){
        return addressService.insert(address, response);
    }

    @RequestMapping(method=PUT, path="/address/{addressId}")
    public Address update(@PathVariable(value="addressId") String id, @RequestBody Address address, HttpServletResponse response){
        return addressService.update(id, address, response);
    }

    @RequestMapping(method=DELETE, path="/address")
    public void deleteAll(){
        addressService.deleteAll();
    }

    @RequestMapping(method=DELETE, path="/address/{addressId}")
    public Address delete(@PathVariable(value="addressId") String id, HttpServletResponse response){
        return addressService.delete(id, response);
    }
}
