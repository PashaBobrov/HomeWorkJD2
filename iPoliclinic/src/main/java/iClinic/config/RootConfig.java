package iClinic.config;


import iClinic.storage.api.ISpecialisationRepository;
import iClinic.storage.api.ITimeTableRepository;
import iClinic.storage.api.IUsersRepository;
import iClinic.view.SpecialisationView;
import iClinic.view.TimeTableView;
import iClinic.view.UsersView;
import iClinic.view.api.ISpecialisationView;
import iClinic.view.api.ITimeTableView;
import iClinic.view.api.IUsersView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("iClinic.config")
public class RootConfig {

    @Bean
    public IUsersView getUserView(IUsersRepository repository){
        return new UsersView(repository);
    }

    @Bean
    public ITimeTableView getTimeTable(ITimeTableRepository repository,IUsersView usersView){
        return new TimeTableView(repository,usersView);
    }

    @Bean
    public ISpecialisationView getSpecialisation(ISpecialisationRepository repository){
        return new SpecialisationView(repository);
    }

}
