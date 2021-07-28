package iClinic.view;

import iClinic.model.TimeTable;
import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import iClinic.storage.api.ISpecialisationRepository;
import iClinic.storage.api.ITimeTableRepository;
import iClinic.view.api.ISpecialisationView;
import iClinic.view.api.ITimeTableView;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;
import java.util.Optional;

public class SpecialisationView implements ISpecialisationView {

    private final ISpecialisationRepository repository;

    public SpecialisationView(ISpecialisationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Specialisation> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Specialisation getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void saveSpecialisation(Specialisation specialisation) {
        repository.save(specialisation);
    }


}
