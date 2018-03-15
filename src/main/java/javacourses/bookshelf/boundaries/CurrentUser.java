package javacourses.bookshelf.boundaries;


import javacourses.bookshelf.entities.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class CurrentUser implements Serializable { //serializable dlja soxranerija sessij, pri slu4ae smeni serverov v klastere
    private Long userId;
    private User signedInUser;

    public void signIn(){
        userId =1L;
        signedInUser = new User();
        signedInUser.setId(1L);
        signedInUser.setFullName("John Smith");
    }
public void  signOut(){
        userId = null;
        signedInUser = null;
}


    public boolean isSignedIn() {
        return userId!=null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getSignedIUser() {
        return signedInUser;
    }

    public void setSignedIUser(User signedIUser) {
        this.signedInUser = signedIUser;
    }
}
