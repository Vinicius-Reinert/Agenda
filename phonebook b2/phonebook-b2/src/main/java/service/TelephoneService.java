package service;

import dao.impl.TelephoneDAO;
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
public class TelephoneService {


    @Autowired
    TelephoneDAO telephoneDAO;

    public List<Telephone> get(HttpServletResponse response){
        List<Telephone> telephoneList = telephoneDAO.getAll();
        if(telephoneList == null || telephoneList.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return telephoneList;
    }

    public Telephone getById(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        Telephone telephone = telephoneDAO.getById(Long.parseLong(id));
        if(telephone == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return telephone;
    }

    public Telephone insert(Telephone telephone, HttpServletResponse response){

        if(telephone.getNumber() == null || !isTelephoneNumberUnique(telephone.getNumber())){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        telephoneDAO.insert(telephone);
        return telephone;
    }

    public Telephone update(String id, Telephone telephone, HttpServletResponse response){

        if(!RESTUtils.isInteger(id) || telephone == null || telephone.getNumber() == null || !isTelephoneNumberUnique(telephone.getNumber())){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Telephone currentTelephone = telephoneDAO.getById(Long.parseLong(id));
        if(currentTelephone == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if(telephone.getNumber() != null){
            currentTelephone.setNumber(telephone.getNumber());
        }
        if(telephone.getCarrier_id() != null){
            currentTelephone.setCarrier_id(telephone.getCarrier_id());
        }
        if(telephone.getPerson_id() != null){
            currentTelephone.setPerson_id(telephone.getPerson_id());
        }
        if(telephone.getType_id() != null){
            currentTelephone.setType_id(telephone.getType_id());
        }

        telephoneDAO.update(currentTelephone);
        return currentTelephone;
    }

    public void deleteAll(){

        telephoneDAO.deleteAll();
    }

    public Telephone delete(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        Telephone telephone = telephoneDAO.getById(Long.parseLong(id));
        telephoneDAO.delete(telephone);
        return telephone;
    }

    private boolean isTelephoneNumberUnique(String number){
        List<Telephone> telephoneList = telephoneDAO.getByNumber(number);
        if(telephoneList == null || telephoneList.isEmpty()){
            return true;
        }
        return false;
    }
}
