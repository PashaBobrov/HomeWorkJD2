package iClinic.view.api;

import iClinic.model.TimeTable;
import iClinic.model.user.Specialisation;

import java.util.List;

public interface ISpecialisationView {
    List<Specialisation> getAll();
    Specialisation getById(int id);
    void saveSpecialisation(Specialisation specialisation);
}
