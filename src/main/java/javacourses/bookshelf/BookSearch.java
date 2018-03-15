package javacourses.bookshelf;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

@Stateless //tut neljzja dannie xranitj, naprimer first name, chtobi dannie ne putalisj
@Named // chtobi s jsf stranic mozhno bilo obrawatjsja sjuda
public class BookSearch {
    @PersistenceContext //(name = "default")// nazvanie bazi s persistence.xml
    private EntityManager em;//chtobi ne pisatj connection k baze

    @Inject
    private BookSearchForm bookSearchForm;
    public List<Book> getAllBooks(){
        Query q = em.createQuery("SELECT b from Book b");
        return q.getResultList();

    }

    public void doSearch() {
        Query q = em.createQuery("SELECT b FROM Book b WHERE " +
                "UPPER(b.author) LIKE :term " +
                "OR UPPER(b.isbn) LIKE :term " +
                "OR UPPER(b.title) LIKE :term " +
                "OR UPPER(b.description) LIKE :term");
        String term = "%" + bookSearchForm.getTerm().toUpperCase() + "%";
        q.setParameter("term", term);
        bookSearchForm.setSearchResult(q.getResultList());
    }
}
