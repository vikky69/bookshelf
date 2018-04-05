package javacourses.bookshelf.boundaries;

import javacourses.bookshelf.entities.Book;
import javacourses.bookshelf.entities.Reservation;
import javacourses.bookshelf.entities.Status;
import javacourses.bookshelf.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class BookDetailsForm {
    private static Logger logger = Logger.getLogger("BookDetailsForm");

    @PersistenceContext //(name = "default")// nazvanie bazi s persistence.xml
    private EntityManager em;//chtobi ne pisatj connection k baze

    @Inject
    private CurrentUser currentUser;

    private  Long bookId;
    private Book book;

    @Transactional
    public void  findBook(){
      book =  em.find(Book.class, bookId);

    }

    @Transactional
    public void reserve(){
        logger.info ("Reservation started");
        User user = currentUser.getSignedIUser();
        findBook();
        logger. info("User" + user.getId());
        logger.info("Book" + book.getId());

        // proveritj nado, 4to etot poljzovatelj i eta kniga ne v rezervacii
        //Select r from Reservation r where r.user = :user and r.book = :book and r.status <> 'RELEASED'


        List<Reservation> reservations = em.createQuery("SELECT r FROM Reservation r  WHERE " +
                "UPPER(r.user) = :user " +
                "AND UPPER(r.book) = :book " +
                "AND UPPER(r.status)  <> 'RELEASED' ")
                .setParameter("book", book)
                .setParameter("user", user)
                .getResultList();
        if (!reservations.isEmpty()) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Already reserved"));
        }

                ;

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setStatus(Status.WAIT);

        em.persist(reservation); //tipa insert

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
