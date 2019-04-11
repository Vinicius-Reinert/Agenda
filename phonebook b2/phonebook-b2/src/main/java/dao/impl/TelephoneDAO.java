package dao.impl;

import dao.iface.TelephoneDAOInterface;
import domain.Telephone;
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
public class TelephoneDAO implements TelephoneDAOInterface {


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

    public void insert(Telephone entity) {
        Session session = openCurrentSessionWithTransaction();
        session.save(entity);
        closeCurrentSessionWithTransaction();
    }

    public void update(Telephone entity) {
        openCurrentSessionWithTransaction().update(entity);
        closeCurrentSessionWithTransaction();    }

    public Telephone getById(Long id) {
        Telephone telephone = (Telephone) openCurrentSession().get(Telephone.class, id);
        closeCurrentSession();
        return telephone;
    }


    public List<Telephone> getAll() {
        List<Telephone> telephoneList = (List<Telephone>) openCurrentSession().createQuery("from Telephone").list();
        closeCurrentSession();
        return telephoneList;
    }

    public void delete(Telephone entity) {
        openCurrentSessionWithTransaction().delete(entity);
        closeCurrentSessionWithTransaction();
    }

    public void deleteAll() {
        List<Telephone> telephoneList = getAll();
        for(Telephone telephone : telephoneList){
            delete(telephone);
        }
    }

    @Override
    public List<Telephone> getByNumber(String number) {
        Session session = openCurrentSession();
        String hql = "FROM Telephone t WHERE t.number = :num";
        Query query = session.createQuery(hql);
        query.setParameter("num", number);
        List<Telephone> personList = query.list();
        return personList;
    }
}
