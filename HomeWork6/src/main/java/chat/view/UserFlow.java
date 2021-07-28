package chat.view;

import chat.storage.api.IUserFlowRepository;
import chat.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Optional;

public class UserFlow {
    public final IUserFlowRepository repository;

    public UserFlow(IUserFlowRepository repository) {
        this.repository = repository;
    }

    /** добавления нового пользователя в список
     * @param  user - пользователь
     */
    public void add(User user) {
        repository.save(user);
    }
    /**
     * Функция возвращает пользователя по логину
     * @param login - логин пользователя
     * @return возвращает пользователя, если есть, в противном случае null
     */
    public User getUser(String login) {

        Optional<User> result =  repository.findOne(getExampleUser(login));
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get();
        }
    }

    /**
     * Функция возвращает true, если пользователя нет в списке пользователей
     * @param login - логин пользователя
     * @return возвращает true, если есть false
     */
    public boolean isNewUser(String login) {

        Optional<User> result =  repository.findOne(getExampleUser(login));

        return result.isEmpty();
    }


    private  Example<User> getExampleUser(String login) {

        User user = new User();
        user.setLogin(login);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("login", match -> match.exact());
        return Example.of(user, matcher);
    }


}
