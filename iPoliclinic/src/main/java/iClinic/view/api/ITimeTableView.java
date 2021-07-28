package iClinic.view.api;

import iClinic.model.TimeTable;
import iClinic.model.user.Specialisation;

import java.util.Date;
import java.util.List;

public interface ITimeTableView {
    List<TimeTable> getAll();
    TimeTable getById(int id);
    List<TimeTable> getBySpecialisation(Specialisation specialisation);
    void saveTimeTable(TimeTable timeTable);
    void deleteTimeTable(TimeTable timeTable);
}
