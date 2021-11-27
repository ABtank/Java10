import DAO.FilmDAO;
import DAO.MySessionFactory;
import entity.Film;
import entity.Seance;
import entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Random;


public class Main {


    public static void main(String[] args) {
        SessionFactory factory = MySessionFactory.getSessionFactory();
        Session session = null;
        FilmDAO filmDAO = new FilmDAO();
        try {
//          READ
            filmDAO.openCurrentSessionWithTransaction();
            Film film = filmDAO.findById(1L);
            filmDAO.closeCurrentSessionWithTransaction();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Seance seance = session.get(Seance.class, 1L);
            Ticket ticket = session.get(Ticket.class, 1L);
            session.getTransaction().commit();

            System.out.println(film);
            System.out.println(seance);
            System.out.println(ticket);

//          CREATE
            Random random = new Random();
            ArrayList<Film> filmArrayList = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                filmArrayList.add(new Film(createFilmName(), 30 + random.nextInt(100)));
            }
            filmDAO.openCurrentSessionWithTransaction();
            Film film1 = filmDAO.saveOrUpdate(new Film(createFilmName(), 30 + random.nextInt(100)));
            for (int i = 0; i < filmArrayList.size() - 1; i++) {
                filmDAO.persist(filmArrayList.get(i));
            }
            filmDAO.closeCurrentSessionWithTransaction();

//            UPDATE
            filmDAO.openCurrentSessionWithTransaction();
            film1.setName("FEAR2");
            filmDAO.saveOrUpdate(film1);
            filmDAO.closeCurrentSessionWithTransaction();
//            DELETE
            filmDAO.openCurrentSessionWithTransaction();
//            List<Film> resultList = filmDAO.findAll();
//            for (int i = 0; i < resultList.size() - 1; i++) {
//                filmDAO.delete(resultList.get(i));
//            }
            filmDAO.deleteAll();
            filmDAO.closeCurrentSessionWithTransaction();

            filmDAO.openCurrentSessionWithTransaction();
            filmDAO.persist(new Film(createFilmName(), 30 + random.nextInt(100)));
            filmDAO.closeCurrentSessionWithTransaction();
        } finally {
            factory.close();
            if (filmDAO.getCurrentSession() != null) {
                filmDAO.closeCurrentSession();
            }
            if (session != null) {
                session.close();
            }
        }

    }

    private static String createFilmName() {
        StringBuilder sb = new StringBuilder("");
        Random random = new Random();
        int lengthName = 5 + random.nextInt(10);
        for (int i = 0; i < lengthName; i++) {
            if (i == 0) sb.append((char) (65 + random.nextInt(25)));
            if (i > 0) sb.append((char) (97 + random.nextInt(25)));
        }
        return sb.toString();
    }
}
