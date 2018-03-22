package javacourses.bookshelf.boundaries;

import javacourses.bookshelf.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Objects;

@RequestScoped
@Named
public class RegistrationForm {
    private final static String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @PersistenceContext
    private EntityManager em;

    private String fullName;
    private String email;
    private String password1;
    private String password2;

    @Transactional
    public String register() {
        if (!Objects.equals(password1, password2)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password should be the same"));
        return null;
        }

        User user = new User ();
        user.setEmail(email);
        user.setPassword (password1);
        user.setFullName(fullName);

        em.persist(user);
        return "/registration-complete.xhtml?faces-redirect=true"; //?faces-redirect=true ne dajot peresubmi4ivatj formu

    }

    public  String getEmailRegex() {
        return EMAIL_REGEX;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
