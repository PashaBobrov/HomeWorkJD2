package chat.storage.api;

import chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChatRepository extends JpaRepository<Message,String> {
}

