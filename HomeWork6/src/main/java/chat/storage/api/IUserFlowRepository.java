package chat.storage.api;

import chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserFlowRepository extends JpaRepository<User,String> {
}

