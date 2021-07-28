package chat.config;

import chat.storage.api.IChatRepository;
import chat.storage.api.IUserFlowRepository;
import chat.view.Chat;
import chat.view.UserFlow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("chat.config")
public class RootConfig {
    @Bean
    public Chat getСhat(IChatRepository repository){
        return new Chat(repository);
    }

    @Bean
    public UserFlow getUserFlow(IUserFlowRepository repository){
        return new UserFlow(repository);
    }
}
