package iClinic.view;

import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import iClinic.storage.api.IUsersRepository;
import iClinic.view.api.IUsersView;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class UsersView implements IUsersView {

    private final IUsersRepository repository;

    public UsersView(IUsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @Override
    public User getById(int id) {
        return repository.findById(id);
    }

    public List<User> getUsersBySpecialisation(Specialisation specialisation) {
        return repository.findAllUserBySpecialisation(specialisation);
    }

    public void createUser(User user) {
        repository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByLogin(s);
    }

}
