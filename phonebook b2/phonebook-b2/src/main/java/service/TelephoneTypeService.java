package service;

import dao.impl.TelephoneDAO;
import dao.impl.TelephoneTypeDAO;
import domain.Telephone;
import domain.TelephoneType;
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
public class TelephoneTypeService {


    @Autowired
    TelephoneTypeDAO telephoneTypeDAO;
    @Autowired
    TelephoneDAO telephoneDAO;

    public List<TelephoneType> get(HttpServletResponse response){
        List<TelephoneType> telephoneTypeList = telephoneTypeDAO.getAll();
        if(telephoneTypeList == null || telephoneTypeList.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return telephoneTypeList;
    }

    public TelephoneType getById(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        TelephoneType telephoneType = telephoneTypeDAO.getById(Long.parseLong(id));
        if(telephoneType == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return telephoneType;
    }

    public TelephoneType insert(TelephoneType telephoneType, HttpServletResponse response){
        telephoneTypeDAO.insert(telephoneType);

        if(telephoneType.getTelephones() != null){
            for(Telephone telephone : telephoneType.getTelephones()){
                telephone.setType_id(telephoneType);
                telephoneDAO.insert(telephone);
            }
        }
        return telephoneType;
    }

    public TelephoneType update(String id, TelephoneType telephoneType, HttpServletResponse response){

        if(!RESTUtils.isInteger(id) || telephoneType == null ){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        TelephoneType currentTelephoneType = telephoneTypeDAO.getById(Long.parseLong(id));
        if(currentTelephoneType == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if(telephoneType.getTelephones() != null){
            currentTelephoneType.setTelephones(telephoneType.getTelephones());
        }
        if(telephoneType.getType() != null){
            currentTelephoneType.setType(telephoneType.getType());
        }

        telephoneTypeDAO.update(currentTelephoneType);
        return currentTelephoneType;
    }

    public void deleteAll(){

        telephoneTypeDAO.deleteAll();
    }

    public TelephoneType delete(String id, HttpServletResponse response){
        if(!RESTUtils.isInteger(id)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        TelephoneType telephoneType = telephoneTypeDAO.getById(Long.parseLong(id));
        telephoneTypeDAO.delete(telephoneType);
        return telephoneType;
    }

}
