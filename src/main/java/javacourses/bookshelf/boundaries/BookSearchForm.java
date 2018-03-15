package javacourses.bookshelf.boundaries;


import javacourses.bookshelf.entities.Book;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@RequestScoped
@Named  // chtobi s jsf stranic mozhno bilo obrawatjsja sjuda
public class BookSearchForm {

    @PersistenceContext //(name = "default")// nazvanie bazi s persistence.xml
    private EntityManager em;//chtobi ne pisatj connection k baze


    private String term;
    private List<Book> searchResult;

    public String getTerm() {
        return term;
    }

    @Transactional //pozvoljaet rabotatj s bazami
    public List<Book> getAllBooks(){
        Query q = em.createQuery("SELECT b from Book b");
        return q.getResultList();

    }

    @Transactional
    public void doSearch() {
        Query q = em.createQuery("SELECT b FROM Book b WHERE " +
                "UPPER(b.author) LIKE :term " +
                "OR UPPER(b.isbn) LIKE :term " +
                "OR UPPER(b.title) LIKE :term " +
                "OR UPPER(b.description) LIKE :term");
        String term = "%" + getTerm().toUpperCase() + "%";
        q.setParameter("term", term);
        setSearchResult(q.getResultList());
    }





    public void setTerm(String term) {
        this.term = term;
    }

    public List<Book> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Book> searchResult) {
        this.searchResult = searchResult;
    }
}
