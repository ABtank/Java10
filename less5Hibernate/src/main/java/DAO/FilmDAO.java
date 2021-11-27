package DAO;

import entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class FilmDAO {
    private Session currentSession;

    private Transaction currentTransaction;


    public FilmDAO() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        return MySessionFactory.getSessionFactory();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Film entity) {
        getCurrentSession().save(entity);
    }

    public void update(Film entity) {
        getCurrentSession().update(entity);
    }

    public Film saveOrUpdate(Film entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public Film findById(Long id) {
        return getCurrentSession().get(Film.class, id);

    }

    public void delete(Film entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteAll() {
        getCurrentSession().createSQLQuery("DELETE FROM film").executeUpdate();
    }

    public List<Film> findAll() {
        return getCurrentSession().createQuery("SELECT f FROM Film f", Film.class).list();
    }

}
