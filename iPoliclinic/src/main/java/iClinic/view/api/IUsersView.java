package iClinic.view.api;

import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUsersView extends UserDetailsService {
    List<User> getAll();
    User getById(int id);
    List<User> getUsersBySpecialisation(Specialisation specialisation);
    void createUser(User user);
}
