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

    public void doSearch(){
        Query q = em.createQuery("select b from Book b where  b.author = ?1");
        q.setParameter("name", bookSearchForm.getTerm());
        bookSearchForm.setSearchResult (q.getResultList());
        System.out.println(bookSearchForm.getTerm());

    }
}
