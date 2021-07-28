package iClinic.view;

import iClinic.model.TimeTable;
import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import iClinic.storage.api.ITimeTableRepository;
import iClinic.view.api.ITimeTableView;
import iClinic.view.api.IUsersView;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;


import java.util.*;
import java.util.stream.Collectors;

public class TimeTableView implements ITimeTableView {

    private final ITimeTableRepository repository;
    private final IUsersView usersView;

    public TimeTableView(ITimeTableRepository repository, IUsersView usersView) {
        this.repository = repository;
        this.usersView = usersView;
    }

    @Override
    public TimeTable getById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<TimeTable> getAll() {
        return this.repository.findAll().stream()
                .sorted(Comparator.comparing(TimeTable::getDatetime))
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeTable> getBySpecialisation(Specialisation specialisation) {
        List<User> listUser = usersView.getUsersBySpecialisation(specialisation);
        List<TimeTable> result = repository.findAllTimeTableByDoctorIn(listUser).stream()
                .sorted(Comparator.comparing(TimeTable::getDatetime))
                .collect(Collectors.toList());
        return result;
    }



    public void saveTimeTable(TimeTable timeTable) {
        repository.save(timeTable);
    }


    public void deleteTimeTable(TimeTable timeTable) {
        repository.delete(timeTable);
    }

}
