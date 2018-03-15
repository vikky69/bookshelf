package javacourses.bookshelf.boundaries;

import javacourses.bookshelf.entities.Book;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
@Named
public class BookDetailsForm {

    @PersistenceContext //(name = "default")// nazvanie bazi s persistence.xml
    private EntityManager em;//chtobi ne pisatj connection k baze


    private  Long bookId;
    private Book book;

    @Transactional
    public void  findBook(){
      book =  em.find(Book.class, bookId);

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
