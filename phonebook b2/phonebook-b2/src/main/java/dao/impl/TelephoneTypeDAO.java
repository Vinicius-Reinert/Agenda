package dao.impl;

import dao.iface.TelephoneTypeDAOInterface;
import domain.TelephoneType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@Service
public class TelephoneTypeDAO implements TelephoneTypeDAOInterface {


    private Session currentSession;

    private Transaction currentTransaction;

    private SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    private Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    private Session openCurrentSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    private void closeCurrentSession(){
        currentSession.close();
    }

    private void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }

    private Session getCurrentSession(){
        return currentSession;
    }

    private void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    private Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    private void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void insert(TelephoneType entity) {
        Session session = openCurrentSessionWithTransaction();
        session.save(entity);
        closeCurrentSessionWithTransaction();
    }

    public void update(TelephoneType entity) {
        openCurrentSessionWithTransaction().update(entity);
        closeCurrentSessionWithTransaction();    }

    public TelephoneType getById(Long id) {
        TelephoneType telephoneType = (TelephoneType) openCurrentSession().get(TelephoneType.class, id);
        closeCurrentSession();
        return telephoneType;
    }

    public List<TelephoneType> getByName(String name){
        Session session = openCurrentSession();
        String hql = "FROM TelephoneType p WHERE p.name = :telephoneType_name";
        Query query = session.createQuery(hql);
        query.setParameter("telephoneType_name", name);
        List<TelephoneType> telephoneTypeList = query.list();
        return telephoneTypeList;
    }

    public List<TelephoneType> getAll() {
        List<TelephoneType> telephoneTypeList = (List<TelephoneType>) openCurrentSession().createQuery("from TelephoneType").list();
        closeCurrentSession();
        return telephoneTypeList;
    }

    public void delete(TelephoneType entity) {
        openCurrentSessionWithTransaction().delete(entity);
        closeCurrentSessionWithTransaction();
    }

    public void deleteAll() {
        List<TelephoneType> telephoneTypeList = getAll();
        for(TelephoneType telephoneType : telephoneTypeList){
            delete(telephoneType);
        }
    }
}
