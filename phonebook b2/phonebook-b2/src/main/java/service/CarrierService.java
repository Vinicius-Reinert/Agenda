package service;

import dao.impl.CarrierDAO;
import dao.impl.TelephoneDAO;
import domain.Carrier;
import domain.Telephone;
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
public class CarrierService {


    @Autowired
    CarrierDAO carrierDAO;
    @Autowired
    TelephoneDAO telephoneDAO;

    public List<Carrier> get(HttpServletResponse response){
        List<Carrier> carrierList = carrierDAO.getAll();
        if(carrierList == null || carrierList.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return carrierList;
    }

    public Carrier getById(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        Carrier carrier = carrierDAO.getById(Long.parseLong(id));
        if(carrier == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return carrier;
    }

    public Carrier insert(Carrier carrier, HttpServletResponse response){
        carrierDAO.insert(carrier);

        if(carrier.getTelephones() != null){
            for(Telephone telephone : carrier.getTelephones()){
                telephone.setCarrier_id(carrier);
                telephoneDAO.insert(telephone);
            }
        }

        return carrier;
    }

    public Carrier update(String id, Carrier carrier, HttpServletResponse response){

        if(!RESTUtils.isInteger(id) || carrier == null ){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Carrier currentCarrier = carrierDAO.getById(Long.parseLong(id));
        if(currentCarrier == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if(carrier.getTelephones() != null){
            currentCarrier.setTelephones(carrier.getTelephones());
        }

        carrierDAO.update(currentCarrier);
        return currentCarrier;
    }

    public void deleteAll(){

        carrierDAO.deleteAll();
    }

    public Carrier delete(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        Carrier carrier = carrierDAO.getById(Long.parseLong(id));
        carrierDAO.delete(carrier);
        return carrier;
    }

}
