package dao.impl;

import dao.iface.PersonDAOInterface;
import domain.Person;
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
public class PersonDAO implements PersonDAOInterface {


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

    public void insert(Person entity) {
        Session session = openCurrentSessionWithTransaction();
        session.save(entity);
        closeCurrentSessionWithTransaction();
    }

    public void update(Person entity) {
        openCurrentSessionWithTransaction().update(entity);
        closeCurrentSessionWithTransaction();    }

    public Person getById(Long id) {
        Person person = (Person) openCurrentSession().get(Person.class, id);
        closeCurrentSession();
        return person;
    }

    public List<Person> getByName(String name){
        Session session = openCurrentSession();
        String hql = "FROM Person p WHERE p.name = :person_name";
        Query query = session.createQuery(hql);
        query.setParameter("person_name", name);
        List<Person> personList = query.list();
        return personList;
    }

    public List<Person> getAll() {
        List<Person> personList = (List<Person>) openCurrentSession().createQuery("from Person").list();
        closeCurrentSession();
        return personList;
    }

    public void delete(Person entity) {
        openCurrentSessionWithTransaction().delete(entity);
        closeCurrentSessionWithTransaction();
    }

    public void deleteAll() {
        List<Person> personList = getAll();
        for(Person person : personList){
            delete(person);
        }
    }
}
