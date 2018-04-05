package javacourses.bookshelf.entities;


import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne //mnogo rezervacij na odnu knigu
    private Book book;

    @ManyToOne //mnogo rezercanij u odnogo usera
    private User user;

    @Enumerated (EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
