package iClinic.storage.api;


import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsersRepository extends JpaRepository<User,Integer> {
    List<User> findAllUserBySpecialisation(Specialisation specialisation);
    User findByLogin(String login);
    User findById(int id);
}
