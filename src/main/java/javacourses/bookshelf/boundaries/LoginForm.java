package javacourses.bookshelf.boundaries;

import javacourses.bookshelf.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequestScoped
@Named
public class LoginForm {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private CurrentUser currentUser;


    private String email;
    private  String password;

    @Transactional
    public String login() {
        TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);

        try {
            User u = query.getSingleResult();
            if (Objects.equals(u.getPassword(),password)) {
                currentUser.setSignedIUser(u);
                currentUser.setUserId(u.getId());
                return "/index.xhtml";

            } else {
                FacesContext.getCurrentInstance().addMessage("login:password", new FacesMessage("Incorrect password"));
            }


        } catch (NoResultException e) {
            FacesContext.getCurrentInstance().addMessage(null, //login:email //togda soobwenie budet otnositsja toljko k konkretnomu polju
                    new FacesMessage());
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
