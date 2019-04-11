package service;

import dao.impl.AddressDAO;
import dao.impl.PersonDAO;
import domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import util.RESTUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@RestController
@Service
public class AddressService {


    @Autowired
    AddressDAO addressDAO;
    @Autowired
    PersonDAO personDAO;

    public List<Address> get(HttpServletResponse response){
        List<Address> addressList = addressDAO.getAll();
        if(addressList == null || addressList.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return addressList;
    }

    public Address getById(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        Address address = addressDAO.getById(Long.parseLong(id));
        if(address == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return address;
    }

    public Address insert(Address address, HttpServletResponse response){
        if(address.getPerson() != null){
            personDAO.insert(address.getPerson());
        }
        addressDAO.insert(address);
        return address;
    }

    public Address update(String id, Address address, HttpServletResponse response){

        if(!RESTUtils.isInteger(id) || address == null ){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Address currentAddress = addressDAO.getById(Long.parseLong(id));
        if(currentAddress == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if(address.getNumber() != null){
            currentAddress.setNumber(address.getNumber());
        }
        if(address.getNeighborhood() != null){
            currentAddress.setNeighborhood(address.getNeighborhood());
        }
        if(address.getPerson() != null){
            currentAddress.setPerson(address.getPerson());
            personDAO.update(address.getPerson());
        }
        if(address.getState() != null){
            currentAddress.setState(address.getState());
        }
        if(address.getStreet() != null){
            currentAddress.setStreet(address.getStreet());
        }
        if(address.getTown() != null){
            currentAddress.setTown(address.getTown());
        }

        addressDAO.update(currentAddress);
        return currentAddress;
    }

    public void deleteAll(){

        addressDAO.deleteAll();
    }

    public Address delete(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        Address address = addressDAO.getById(Long.parseLong(id));
        addressDAO.delete(address);
        return address;
    }

}
