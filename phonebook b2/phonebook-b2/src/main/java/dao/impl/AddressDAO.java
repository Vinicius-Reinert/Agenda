package dao.impl;

import dao.iface.AddressDAOInterface;
import domain.Address;
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
public class AddressDAO implements AddressDAOInterface {


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

    public void insert(Address entity) {
        Session session = openCurrentSessionWithTransaction();
        session.save(entity);
        closeCurrentSessionWithTransaction();
    }

    public void update(Address entity) {
        openCurrentSessionWithTransaction().update(entity);
        closeCurrentSessionWithTransaction();    }

    public Address getById(Long id) {
        Address address = (Address) openCurrentSession().get(Address.class, id);
        closeCurrentSession();
        return address;
    }

    public List<Address> getByName(String name){
        Session session = openCurrentSession();
        String hql = "FROM Address p WHERE p.name = :address_name";
        Query query = session.createQuery(hql);
        query.setParameter("address_name", name);
        List<Address> addressList = query.list();
        return addressList;
    }

    public List<Address> getAll() {
        List<Address> addressList = (List<Address>) openCurrentSession().createQuery("from Address").list();
        closeCurrentSession();
        return addressList;
    }

    public void delete(Address entity) {
        openCurrentSessionWithTransaction().delete(entity);
        closeCurrentSessionWithTransaction();
    }

    public void deleteAll() {
        List<Address> addressList = getAll();
        for(Address address : addressList){
            delete(address);
        }
    }
}
