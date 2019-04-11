package dao.impl;

import dao.iface.CarrierDAOInterface;
import domain.Carrier;
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
public class CarrierDAO implements CarrierDAOInterface {


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

    public void insert(Carrier entity) {
        Session session = openCurrentSessionWithTransaction();
        session.save(entity);
        closeCurrentSessionWithTransaction();
    }

    public void update(Carrier entity) {
        openCurrentSessionWithTransaction().update(entity);
        closeCurrentSessionWithTransaction();    }

    public Carrier getById(Long id) {
        Carrier carrier = (Carrier) openCurrentSession().get(Carrier.class, id);
        closeCurrentSession();
        return carrier;
    }

    public List<Carrier> getByName(String name){
        Session session = openCurrentSession();
        String hql = "FROM Carrier p WHERE p.name = :carrier_name";
        Query query = session.createQuery(hql);
        query.setParameter("carrier_name", name);
        List<Carrier> carrierList = query.list();
        return carrierList;
    }

    public List<Carrier> getAll() {
        List<Carrier> carrierList = (List<Carrier>) openCurrentSession().createQuery("from Carrier").list();
        closeCurrentSession();
        return carrierList;
    }

    public void delete(Carrier entity) {
        openCurrentSessionWithTransaction().delete(entity);
        closeCurrentSessionWithTransaction();
    }

    public void deleteAll() {
        List<Carrier> carrierList = getAll();
        for(Carrier carrier : carrierList){
            delete(carrier);
        }
    }
}
